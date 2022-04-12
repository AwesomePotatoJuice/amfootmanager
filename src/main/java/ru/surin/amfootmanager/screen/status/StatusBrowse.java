package ru.surin.amfootmanager.screen.status;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Status;

@UiController("afm_Status.browse")
@UiDescriptor("status-browse.xml")
@LookupComponent("table")
public class StatusBrowse extends MasterDetailScreen<Status> {
}