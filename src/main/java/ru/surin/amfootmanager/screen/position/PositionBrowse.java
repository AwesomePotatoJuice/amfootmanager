package ru.surin.amfootmanager.screen.position;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Position;

@UiController("afm_Position.browse")
@UiDescriptor("position-browse.xml")
@LookupComponent("table")
public class PositionBrowse extends MasterDetailScreen<Position> {
}