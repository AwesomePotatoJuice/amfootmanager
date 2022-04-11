package ru.surin.amfootmanager.screen.team;

import io.jmix.core.DataManager;
import io.jmix.core.FetchPlans;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.OpenMode;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.surin.amfootmanager.entity.Team;
import ru.surin.amfootmanager.entity.User;

@UiController("afm_Team.init")
@UiDescriptor("team-init.xml")
@EditedEntityContainer("teamDc")
public class TeamInit extends StandardEditor<Team> {

    @Autowired
    private InstanceContainer<Team> teamDc;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private FetchPlans fetchPlans;

    @Subscribe
    public void onInit(InitEvent event) {
        User user = getCurrentUser();
        if (user.getProfile() != null && user.getProfile().getTeam() != null) {
            setEntityToEdit(user.getProfile().getTeam());
        } else {
            final Team team = dataManager.create(Team.class);
            final TeamCreate build = screenBuilders.editor(Team.class, this)
                    .withScreenClass(TeamCreate.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .editEntity(team)
                    .build();
            build.addAfterCloseListener(afterCloseEvent -> {
                this.closeWithDiscard();
//                if (afterCloseEvent.getCloseAction().equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
//                    setEntityToEdit(dataManager.load(Team.class)
//                            .id(team.getId())
//                            .fetchPlan(fetchPlans.builder(Team.class)
//                                    .addFetchPlan("_base")
//                                    .build())
//                            .one());
//                } else {
//                    this.closeWithDiscard();
//                }
            });
            build.show();
        }
    }

    private User getCurrentUser() {
        UserDetails userDetails = currentAuthentication.getUser();
        if (userDetails instanceof User) {
            return (User) userDetails;
        }
        throw new RuntimeException("Something went wrong while getting current user");
    }

}