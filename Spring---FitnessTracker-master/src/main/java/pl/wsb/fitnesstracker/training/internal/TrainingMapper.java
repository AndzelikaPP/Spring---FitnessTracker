package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingForm;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
@RequiredArgsConstructor
class TrainingMapper {

    private final UserMapper userMapper;
    private final UserProvider userProvider;

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    Training toEntity(TrainingForm training){
        return new Training(
                userProvider.getUser(training.userId()).orElseThrow(),
                training.startTime(),
                training.endTime(),
                training.activityType(),
                training.distance(),
                training.averageSpeed()
        );
    }

}
