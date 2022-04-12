package ru.surin.amfootmanager.screen.team;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Team;

@UiController("afm_Team.browse")
@UiDescriptor("team-browse.xml")
@LookupComponent("teamsTable")
public class TeamBrowse extends StandardLookup<Team> {
}