package ru.surin.amfootmanager.screen.team;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Team;

@UiController("afm_Team.edit")
@UiDescriptor("team-edit.xml")
@EditedEntityContainer("teamDc")
public class TeamEdit extends StandardEditor<Team> {

}