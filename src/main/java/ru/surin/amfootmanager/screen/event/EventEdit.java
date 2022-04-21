package ru.surin.amfootmanager.screen.event;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Event;

@UiController("afm_Event.edit")
@UiDescriptor("event-edit.xml")
@EditedEntityContainer("eventDc")
public class EventEdit extends StandardEditor<Event> {
}