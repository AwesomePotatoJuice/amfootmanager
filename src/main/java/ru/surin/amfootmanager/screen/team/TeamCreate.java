package ru.surin.amfootmanager.screen.team;

import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.ProfileType;
import ru.surin.amfootmanager.entity.Team;
import ru.surin.amfootmanager.entity.User;

@UiController("afm_Team.create")
@UiDescriptor("team-create.xml")
@EditedEntityContainer("teamDc")
public class TeamCreate extends StandardEditor<Team> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeCommitChanges(AfterCommitChangesEvent event) {
        final Profile profile;
        final User currentUser = getCurrentUser();
        if (currentUser.getProfile() != null) {
            profile = currentUser.getProfile();
        } else {
            profile = dataManager.create(Profile.class);
        }
        profile.setTeam(getEditedEntity());
        profile.setRole("Coach");
        profile.setUser(currentUser);
        profile.setName(currentUser.getDisplayName());
        currentUser.setProfile(profile);
        dataManager.save(currentUser);
        notifications.create().withCaption(messages.getMessage(getClass(), "teamToProfileAssignment")).show();
    }

    private User getCurrentUser() {
        UserDetails userDetails = currentAuthentication.getUser();
        if (userDetails instanceof User) {
            return (User) userDetails;
        }
        throw new RuntimeException("Something went wrong while getting current user");
    }
}