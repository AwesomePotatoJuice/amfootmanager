package ru.surin.amfootmanager.screen.profile;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Profile;

@UiController("afm_Profile.browse")
@UiDescriptor("profile-browse.xml")
@LookupComponent("profilesTable")
public class ProfileBrowse extends StandardLookup<Profile> {
}