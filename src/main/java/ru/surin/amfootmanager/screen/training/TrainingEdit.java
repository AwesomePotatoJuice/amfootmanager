package ru.surin.amfootmanager.screen.training;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Training;

@UiController("afm_Training.edit")
@UiDescriptor("training-edit.xml")
@EditedEntityContainer("trainingDc")
public class TrainingEdit extends StandardEditor<Training> {
}