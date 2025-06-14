package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

public record TrainingForm(Long userId, Date startTime, Date endTime,
                           ActivityType activityType, double distance, double averageSpeed){
}
