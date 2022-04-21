package ru.surin.amfootmanager.screen.event;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.Event;

@UiController("afm_Event.browse")
@UiDescriptor("event-browse.xml")
@LookupComponent("eventsTable")
public class EventBrowse extends StandardLookup<Event> {
}