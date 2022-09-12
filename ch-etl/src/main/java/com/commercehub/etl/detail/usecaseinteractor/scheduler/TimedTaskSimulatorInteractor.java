package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.entitybuilder.scheduler.TimedTaskBuilder;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskSimulator;
import com.commercehub.etl.core.usecase.shop.TimeZoneProvider;
import com.commercehub.etl.core.usecase.shop.TimeZoneProviderFactory;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Dependent
public class TimedTaskSimulatorInteractor implements TimedTaskSimulator {

    private final TimeZoneProviderFactory factory;

    public TimedTaskSimulatorInteractor(TimeZoneProviderFactory factory) {
        this.factory = factory;
    }

    @Override
    public Uni<TimedTask> simulateLatestTask(Linking linking, int minutePerTask) {
        return Uni.createFrom().item(() -> {
            TimeZoneProvider provider = factory.dispatch(linking.platform);
            String timeZoneId = provider.provideTimeZoneId(linking.shopRegion);
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

            LocalDate startDate = LocalDate.ofInstant(linking.businessStartDate, timeZone.toZoneId());
            LocalDateTime dateTimeFrom = startDate.atStartOfDay().minusMinutes(minutePerTask);
            LocalDateTime dateTimeTo = startDate.atStartOfDay().minusSeconds(1);

            return new TimedTaskBuilder()
                    .setPlatform(linking.platform)
                    .setShopId(linking.shopId)
                    .setStatus(TimedTask.STATUS_PENDING)
                    .setParamTimeFrom(dateTimeFrom.atZone(timeZone.toZoneId()).toInstant())
                    .setParamTimeTo(dateTimeTo.atZone(timeZone.toZoneId()).toInstant())
                    .createTimedTask();
        });
    }

    @Override
    public Uni<List<TimedTask>> simulateSubsequentTask(Linking linking, int minutePerTask, TimedTask latestTask, int noOfTaskToSimulate, int noOfDayInAdvance) {
        return Uni.createFrom().item(() -> {
            TimeZoneProvider provider = factory.dispatch(linking.platform);
            String timeZoneId = provider.provideTimeZoneId(linking.shopRegion);
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
            ZoneId zoneId = timeZone.toZoneId();

            LocalDateTime dateTimeFrom = LocalDateTime.ofInstant(latestTask.paramTimeTo, zoneId).plusSeconds(1);
            LocalDateTime dateTimeTo = LocalDateTime.ofInstant(latestTask.paramTimeTo, zoneId).plusMinutes(minutePerTask);
            LocalDate furthestDate = getFurthestDate(zoneId, noOfDayInAdvance);

            List<TimedTask> result = new ArrayList<>();
            while ( !dateTimeFrom.toLocalDate().isAfter(furthestDate) && result.size() < noOfTaskToSimulate ) {
                Instant paramTimeFrom = dateTimeFrom.atZone(zoneId).toInstant();
                Instant paramTimeTo = dateTimeTo.atZone(zoneId).toInstant();

                result.add(
                        new TimedTaskBuilder()
                                .setPlatform(linking.platform)
                                .setShopId(linking.shopId)
                                .setStatus(TimedTask.STATUS_PENDING)
                                .setParamTimeFrom(paramTimeFrom)
                                .setParamTimeTo(paramTimeTo)
                                .createTimedTask()
                );

                dateTimeFrom = dateTimeFrom.plusMinutes(minutePerTask);
                dateTimeTo = dateTimeTo.plusMinutes(minutePerTask);
            }

            return result;
        });
    }

    private LocalDate getFurthestDate(ZoneId zoneId, int noOfDayInAdvance) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), zoneId);
        return today.plusDays(noOfDayInAdvance);
    }

}
