package ru.surin.amfootmanager.screen.team;

import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.ScreenBuilders;
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
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInit(InitEvent event) {
        User user = getCurrentUser();
        if (user.getProfile() != null && user.getProfile().getTeam() != null) {
            final TeamEdit teamEdit = screenBuilders.editor(Team.class, this)
                    .withScreenClass(TeamEdit.class)
                    .editEntity(user.getProfile().getTeam())
                    .build();
            teamEdit.show();
        } else {
            final Team team = dataManager.create(Team.class);
            final TeamCreate teamCreate = screenBuilders.editor(Team.class, this)
                    .withScreenClass(TeamCreate.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .editEntity(team)
                    .build();
            teamCreate.show();
            teamCreate.addAfterCloseListener(afterCloseEvent -> {
                final TeamEdit teamEdit = screenBuilders.editor(Team.class, this)
                        .withScreenClass(TeamEdit.class)
                        .editEntity(user.getProfile().getTeam())
                        .build();
                teamEdit.show();
            });
        }
    }

    @Subscribe
    public void onAfterShow(BeforeShowEvent event) {
        try {
            this.closeWithDiscard();
        } catch (Exception ignore) {
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