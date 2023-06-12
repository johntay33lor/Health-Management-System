package org.health;
import java.time.LocalTime;

public class SleepRecord {
    private LocalTime startTime;
    private LocalTime endTime;

    public SleepRecord(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return endTime.getHour() * 60 + endTime.getMinute() - startTime.getHour() * 60 - startTime.getMinute();
    }
}
