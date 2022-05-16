package ru.surin.amfootmanager.screen.event;

import com.vaadin.v7.shared.ui.calendar.CalendarState;
import io.jmix.core.Metadata;
import io.jmix.core.TimeSource;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;

import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.surin.amfootmanager.entity.Event;
import ru.surin.amfootmanager.entity.Match;
import ru.surin.amfootmanager.entity.Training;
import ru.surin.amfootmanager.screen.event.calendar.CalendarMode;
import ru.surin.amfootmanager.screen.event.calendar.CalendarNavigationMode;
import ru.surin.amfootmanager.screen.event.calendar.CalendarNavigators;
import ru.surin.amfootmanager.screen.event.calendar.CalendarScreenAdjustment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;

import static ru.surin.amfootmanager.screen.event.calendar.CalendarNavigationMode.*;
import static ru.surin.amfootmanager.screen.event.calendar.RelativeDates.startOfWeek;

@UiController("afm_Event.browse")
@UiDescriptor("event-browse.xml")
@LookupComponent("eventsTable")
public class EventBrowse extends StandardLookup<Event> {

    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private Metadata metadata;


    @Autowired
    protected Calendar<LocalDateTime> calendar;
    @Autowired
    protected CollectionLoader<Event> eventsDl;
    @Autowired
    protected CollectionLoader<Event> eventsCalendarDl;
    @Autowired
    protected CollectionContainer<Event> eventsCalendarDc;
    @Autowired
    protected CalendarNavigators calendarNavigators;
    @Autowired
    protected DatePicker<LocalDate> calendarNavigator;
    @Autowired
    protected RadioButtonGroup<CalendarMode> calendarMode;
    @Autowired
    protected TimeSource timeSource;
    @Autowired
    protected Label<String> calendarTitle;
    @Autowired
    protected DatatypeFormatter datatypeFormatter;
    @Autowired
    private CurrentAuthentication currentAuthentication;


    @Subscribe("calendar")
    protected void onCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<Event> event) {
        Screen screen = screenBuilders.editor(Event.class, this)
                .editEntity((Event) event.getEntity()).withOpenMode(OpenMode.DIALOG).build();
        screen.addAfterCloseListener(AfterCloseEvent ->{
            getScreenData().loadAll();
                }
                );
        screen.show();
    }

    @Subscribe("trainingCreate")
    public void onTrainingCreate(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Training.class, this).newEntity().build().show();
   }

    @Subscribe("matchCreate")
    public void onMatchCreate(Action.ActionPerformedEvent event) {
        screenBuilders.editor(Match.class, this).newEntity().build().show();
    }








    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        current(CalendarMode.WEEK);
    }
    @Subscribe("calendar")
    protected void onCalendarCalendarDayClick(Calendar.CalendarDateClickEvent<LocalDateTime> event) {
        atDate(
                CalendarMode.DAY,
                event.getDate().toLocalDate()
        );
    }
    @Subscribe("calendar")
    protected void onCalendarCalendarWeekClick(Calendar.CalendarWeekClickEvent<LocalDateTime> event) {
        atDate(
                CalendarMode.WEEK,
                startOfWeek(
                        event.getYear(),
                        event.getWeek(),
                        currentAuthentication.getLocale()
                )
        );
    }
    @Subscribe("navigatorPrevious")
    protected void onNavigatorPreviousClick(Button.ClickEvent event) {
        previous(calendarMode.getValue());
    }
    @Subscribe("navigatorNext")
    protected void onNavigatorNextClick(Button.ClickEvent event) {
        next(calendarMode.getValue());
    }
    @Subscribe("navigatorCurrent")
    protected void onNavigatorCurrentClick(Button.ClickEvent event) {
        current(calendarMode.getValue());
    }
    @Subscribe("calendarNavigator")
    protected void onCalendarRangePickerValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        if (event.isUserOriginated()) {
            atDate(calendarMode.getValue(), event.getValue());
        }
    }

    private void current(CalendarMode calendarMode) {
        change(calendarMode, AT_DATE, timeSource.now().toLocalDate());
    }
    private void next(CalendarMode calendarMode) {
        change(calendarMode, NEXT, calendarNavigator.getValue());
    }
    private void previous(CalendarMode calendarMode) {
        change(calendarMode, PREVIOUS, calendarNavigator.getValue());
    }
    private void atDate(CalendarMode calendarMode, LocalDate date) {
        change(calendarMode, AT_DATE, date);
    }
    private void change(CalendarMode calendarMode, CalendarNavigationMode navigationMode, LocalDate referenceDate) {
        this.calendarMode.setValue(calendarMode);

        calendarNavigators
                .forMode(
                        CalendarScreenAdjustment.of(calendar, calendarNavigator, calendarTitle),
                        datatypeFormatter,
                        calendarMode
                )
                .navigate(navigationMode, referenceDate);

        loadEvents();
    }
    @Subscribe("calendarMode")
    protected void onCalendarRangeValueChange(HasValue.ValueChangeEvent event) {
        if (event.isUserOriginated()) {
            atDate((CalendarMode) event.getValue(), calendarNavigator.getValue());
        }
    }
    private void loadEvents() {
        eventsCalendarDl.setParameter("startDate", calendar.getStartDate());
        eventsCalendarDl.setParameter("endDate", calendar.getEndDate());
        eventsCalendarDl.load();
    }


}