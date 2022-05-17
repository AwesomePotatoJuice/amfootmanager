package ru.surin.amfootmanager.screen.team;

import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.TabSheet;
import io.jmix.ui.model.CollectionChangeType;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.surin.amfootmanager.entity.Profile;
import ru.surin.amfootmanager.entity.ProfileType;
import ru.surin.amfootmanager.entity.Team;

import java.util.Collection;
import java.util.Collections;

@UiController("afm_Team.edit")
@UiDescriptor("team-edit.xml")
@EditedEntityContainer("teamDc")
public class TeamEdit extends StandardEditor<Team> {

    @Autowired
    private CollectionLoader<Profile> profilesDl;


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        profilesDl.setParameter("role", ProfileType.PLAYER);
        profilesDl.setParameter("team", getEditedEntity());
    }


}




