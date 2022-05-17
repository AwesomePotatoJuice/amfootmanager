package ru.surin.amfootmanager.screen.roster;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Roster;

@UiController("afm_Roster.browse")
@UiDescriptor("roster-browse.xml")
@LookupComponent("rostersTable")
public class RosterBrowse extends StandardLookup<Roster> {
}