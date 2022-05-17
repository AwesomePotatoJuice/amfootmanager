package ru.surin.amfootmanager.screen.training;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.ProfileType;
import ru.surin.amfootmanager.entity.Training;
import ru.surin.amfootmanager.entity.User;

@UiController("afm_Training.edit")
@UiDescriptor("training-edit.xml")
@EditedEntityContainer("trainingDc")
public class TrainingEdit extends StandardEditor<Training> {

    @Autowired
    private CollectionLoader<Profile> profilesDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;


    @Subscribe
    public void onInitEntity(InitEntityEvent<Training> event) {
        profilesDl.setParameter("team", getCurrentUser().getProfile().getTeam());
    }

    
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event){
//        profilesDl.setParameter("role", ProfileType.PLAYER);
//        profilesDl.setParameter("team", getEditedEntity());
    }// TODO add refresh handler, which fires after team was picked https://demo.jmix.io/sampler/#main/29/sample?id=simplepagination-afterrefresh // https://docs.jmix.io/jmix/ui/actions/standard-actions/refresh-action.html
    private User getCurrentUser() {
        UserDetails userDetails = currentAuthentication.getUser();
        if (userDetails instanceof User) {
            return (User) userDetails;
        }
        throw new RuntimeException("Something went wrong while getting current user");
    }
}