package ru.surin.amfootmanager.screen.roster;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.surin.amfootmanager.entity.Position;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.Roster;
import ru.surin.amfootmanager.entity.User;

import java.util.stream.Collectors;

@UiController("afm_Roster.edit")
@UiDescriptor("roster-edit.xml")
@EditedEntityContainer("rosterDc")
public class RosterEdit extends StandardEditor<Roster> {
    @Autowired
    private CollectionLoader<Profile> profilesDl;
    @Autowired
    private CollectionContainer<Profile> profilesDc;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private EntityComboBox<Profile> centerCb;
    @Autowired
    private EntityComboBox<Profile> quarterbackCb;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Roster> event) {
        profilesDl.setParameter("team", getCurrentUser().getProfile().getTeam());
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        centerCb.setOptionsList(profilesDc.getItems().stream()
                .filter(profile ->
                        isThisPosition(profile, "Center")
                ).collect(Collectors.toList()));
        quarterbackCb.setOptionsList(profilesDc.getItems().stream()
                .filter(profile ->
                        isThisPosition(profile, "Quarterback")
                ).collect(Collectors.toList()));
    }

    private boolean isThisPosition(Profile profile, String s) {
        return profile.getPosition().stream()
                .map(Position::getName)
                .toList().contains(s);
    }

    private User getCurrentUser() {
        UserDetails userDetails = currentAuthentication.getUser();
        if (userDetails instanceof User) {
            return (User) userDetails;
        }
        throw new RuntimeException("Something went wrong while getting current user");
    }
}