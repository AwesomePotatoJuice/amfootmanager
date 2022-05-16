package ru.surin.amfootmanager.screen.training;

import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.ProfileType;
import ru.surin.amfootmanager.entity.Training;

@UiController("afm_Training.edit")
@UiDescriptor("training-edit.xml")
@EditedEntityContainer("trainingDc")
public class TrainingEdit extends StandardEditor<Training> {

    @Autowired
    private CollectionLoader<Profile> profilesDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event){
        profilesDl.setParameter("role", ProfileType.PLAYER);
        profilesDl.setParameter("team", getEditedEntity());
    }// TODO add refresh handler, which fires after team was picked https://demo.jmix.io/sampler/#main/29/sample?id=simplepagination-afterrefresh // https://docs.jmix.io/jmix/ui/actions/standard-actions/refresh-action.html

}