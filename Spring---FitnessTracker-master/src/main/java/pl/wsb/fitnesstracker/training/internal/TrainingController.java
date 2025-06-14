package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingForm;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    /**
     * Zwraca listę wszystkich treningów.
     *
     * @return lista treningów w formacie TrainingDto
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTraining()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Zwraca wszystkie treningi zakończone po podanej dacie.
     *
     * @param afterTime data, po której zakończone treningi mają zostać zwrócone
     * @return lista treningów zakończonych po podanym czasie
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getAllTrainingsFinishedAfterTime(@PathVariable LocalDate afterTime) {
        return trainingService.findAllTrainingFinishedAfterTime(Date.from(afterTime.atStartOfDay().toInstant(ZoneOffset.UTC)))
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Zwraca wszystkie treningi danego typu aktywności.
     *
     * @param activityType typ aktywności (np. BIEGANIE, JAZDA_ROWEREK)
     * @return lista treningów pasujących do danego typu aktywności
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getAllTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findAllTrainingByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Zwraca wszystkie treningi użytkownika o podanym ID.
     *
     * @param userId identyfikator użytkownika
     * @return lista treningów przypisanych do danego użytkownika
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getAllTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findAllTrainingByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Dodaje nowy trening na podstawie formularza treningowego.
     *
     * @param training dane treningu przesłane w formularzu
     * @return utworzony trening w formacie TrainingDto
     */
    @PostMapping
    public ResponseEntity<TrainingDto> addTraining(@RequestBody TrainingForm training) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                trainingMapper.toDto(
                        trainingService.createTraining(trainingMapper.toEntity(training))
                )
        );
    }

    /**
     * Aktualizuje istniejący trening o podanym ID na podstawie formularza treningowego.
     *
     * @param training   nowe dane treningu
     * @param trainingId identyfikator treningu do aktualizacji
     * @return zaktualizowany trening w formacie TrainingDto
     */
    @PutMapping("{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining(@RequestBody TrainingForm training, @PathVariable Long trainingId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                trainingMapper.toDto(
                        trainingService.updateTraining(trainingId, trainingMapper.toEntity(training))
                )
        );
    }


}