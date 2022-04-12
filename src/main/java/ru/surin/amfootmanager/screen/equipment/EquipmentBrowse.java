package ru.surin.amfootmanager.screen.equipment;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Equipment;

@UiController("afm_Equipment.browse")
@UiDescriptor("equipment-browse.xml")
@LookupComponent("table")
public class EquipmentBrowse extends MasterDetailScreen<Equipment> {
}