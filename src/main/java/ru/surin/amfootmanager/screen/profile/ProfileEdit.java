package ru.surin.amfootmanager.screen.profile;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Profile;

@UiController("afm_Profile.edit")
@UiDescriptor("profile-edit.xml")
@EditedEntityContainer("profileDc")
public class ProfileEdit extends StandardEditor<Profile> {
}