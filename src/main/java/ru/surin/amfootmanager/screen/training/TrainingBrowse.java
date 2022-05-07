package ru.surin.amfootmanager.screen.training;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Training;

@UiController("afm_Training.browse")
@UiDescriptor("training-browse.xml")
@LookupComponent("trainingsTable")
public class TrainingBrowse extends StandardLookup<Training> {
}