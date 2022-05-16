package ru.surin.amfootmanager.screen.match;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Match;

@UiController("afm_Match.edit")
@UiDescriptor("match-edit.xml")
@EditedEntityContainer("matchDc")
public class MatchEdit extends StandardEditor<Match> {
}