package com.commercehub.etl.domain.entity.schduler;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.common.TimedTaskUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.repository.TimedTaskRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TimedTaskProducer {

    @Inject
    Logger log;

    private final int MINUTE_PER_TASK = 30;

    public List<TimedTask> produce(Linking linking) {
        if ( !shallRun(linking) ) { return new ArrayList<>(); }

        final String collectionName = collectionName();
        // Prefer immutable object ...
        TimedTask latestTask = repository().getLatest(collectionName, linking.getPlatform(), linking.getShopId());
        TimedTask finalLatestTask = latestTask == null ? getInitialTask(linking) : new TimedTask(latestTask);
        log.debug("Final latest task: " + finalLatestTask);

        List<TimedTask> newTasks = generateTask(finalLatestTask);
        log.debug("=== Tasks Prepared ===");
        log.debug(newTasks);

        if ( !repository().verifyLatestAndCreate(latestTask, collectionName, newTasks.toArray(new TimedTask[0])) ) {
            throw new RuntimeException("Failed to save task into database");
        }
        return newTasks;
    }

    private String collectionName() {
        return TimedTaskUtils.COLLECTION_PREFIX + taskName();
    }

    // Last slot from the day before business start
    private TimedTask getInitialTask(Linking linking) {
        LocalDate startDate = TimeZoneUtils.getDate(linking.getBusinessStartDate());

        LocalDateTime dateTimeFrom = startDate.atStartOfDay().minusMinutes(MINUTE_PER_TASK);
        Date dateFrom = TimeZoneUtils.getDate(dateTimeFrom);

        LocalDateTime dateTimeTo = startDate.atStartOfDay().minusSeconds(1);
        Date dateTo = TimeZoneUtils.getDate(dateTimeTo);

        return new TimedTask(
                null,
                linking.getPlatform(),
                linking.getShopId(),
                TimedTask.STATUS_PENDING,
                dateFrom,
                dateTo,
                null, null
        );
    }

    private List<TimedTask> generateTask(TimedTask latestTask) {
        List<TimedTask> result = new ArrayList<>();

        Date lastDateTo = latestTask.getParamTimeTo();
        LocalDateTime lastDateTimeTo = TimeZoneUtils.getLocalDateTime(lastDateTo);

        LocalDate targetDate = getTargetDate(lastDateTimeTo.toLocalDate());
        log.debug("Target Date: " + targetDate);

        LocalDateTime dateTimeFrom = lastDateTimeTo.plusSeconds(1);
        LocalDateTime dateTimeTo = lastDateTimeTo.plusMinutes(MINUTE_PER_TASK);

        while ( !dateTimeFrom.toLocalDate().isAfter(targetDate) ) {
            Date dateFrom = TimeZoneUtils.getDate(dateTimeFrom);
            Date dateTo = TimeZoneUtils.getDate(dateTimeTo);

            result.add(
                    new TimedTask(
                            null,
                            latestTask.getPlatform(),
                            latestTask.getShopId(),
                            TimedTask.STATUS_PENDING,
                            dateFrom,
                            dateTo,
                            null, null
                    )
            );

            dateTimeFrom = dateTimeFrom.plusMinutes(MINUTE_PER_TASK);
            dateTimeTo = dateTimeTo.plusMinutes(MINUTE_PER_TASK);
        }

        return result;
    }

    // Max target date = today + 2
    private LocalDate getTargetDate(LocalDate lastDateTo) {
        final int MINUS_TWO_DAY = -2;
        LocalDate today = LocalDate.now();
        return lastDateTo.until(today).getDays() < MINUS_TWO_DAY ? lastDateTo : lastDateTo.plusDays(1);
    }

    public abstract boolean shallRun(Linking linking);
    public abstract String taskName();
    public abstract TimedTaskRepository repository();

}
