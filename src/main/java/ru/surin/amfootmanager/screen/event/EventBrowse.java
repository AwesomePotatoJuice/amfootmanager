package ru.surin.amfootmanager.screen.event;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.Calendar;
import io.jmix.ui.component.Component;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.surin.amfootmanager.entity.Event;

@UiController("afm_Event.browse")
@UiDescriptor("event-browse.xml")
@LookupComponent("eventsTable")
public class EventBrowse extends StandardLookup<Event> {
    @Autowired
    private ScreenBuilders screenBuilders;

    @Subscribe("calendar")
    public void onCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<Event> event) {
        Screen screen = screenBuilders.editor(Event.class, this)
                .editEntity((Event) event.getEntity()).withOpenMode(OpenMode.DIALOG).build();
        screen.addAfterCloseListener(AfterCloseEvent ->{
            getScreenData().loadAll();
                }
                );
        screen.show();
    }

}