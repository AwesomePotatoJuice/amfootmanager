package ru.surin.amfootmanager.screen.profile;

import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.ProfileType;
import ru.surin.amfootmanager.entity.Team;

@UiController("afm_Profile.edit")
@UiDescriptor("profile-edit.xml")
@EditedEntityContainer("profileDc")
public class ProfileEdit extends StandardEditor<Profile> {
    @Autowired
    private EntityPicker<Team> teamField;

    @Autowired
    private InstanceContainer<Profile> profileDc;
    @Autowired
    private DataManager dataManager;



    public Profile loadByCondition() {
        return (Profile) dataManager.load(Profile.class)
                .condition(PropertyCondition.contains("role", ProfileType.PLAYER))
                .list();
    }


    @Subscribe("roleField")
    public void onRoleFieldValueChange(HasValue.ValueChangeEvent<ProfileType> event) {
        if (event.getValue().equals(ProfileType.PLAYER)) {
            teamField.setEditable(true);
        } else {
            teamField.setEditable(false);

        }
    }


}