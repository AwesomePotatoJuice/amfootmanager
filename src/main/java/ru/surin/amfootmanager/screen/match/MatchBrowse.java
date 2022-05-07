package ru.surin.amfootmanager.screen.match;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Match;

@UiController("afm_Match.browse")
@UiDescriptor("match-browse.xml")
@LookupComponent("matchesTable")
public class MatchBrowse extends StandardLookup<Match> {
}