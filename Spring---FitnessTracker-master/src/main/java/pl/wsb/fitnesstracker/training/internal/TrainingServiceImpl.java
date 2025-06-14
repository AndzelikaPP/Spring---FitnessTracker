package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja serwisu treningowego {@link TrainingProvider}.
 * Odpowiada za logikę biznesową oraz operacje na danych treningowych.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    /**
     * Implementacja serwisu treningowego {@link TrainingProvider}.
     * Odpowiada za logikę biznesową oraz operacje na danych treningowych.
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Zwraca wszystkie treningi zapisane w systemie.
     *
     * @return lista wszystkich treningów
     */
    @Override
    public List<Training> findAllTraining() {
        return trainingRepository.findAll();
    }

    /**
     * Zwraca wszystkie treningi, które zakończyły się po podanym czasie.
     *
     * @param date data odniesienia
     * @return lista treningów zakończonych po wskazanej dacie
     */
    @Override
    public List<Training> findAllTrainingFinishedAfterTime(Date date) {
        return trainingRepository.findTrainingsFinishedAfter(date);
    }

    /**
     * Zwraca wszystkie treningi o określonym typie aktywności.
     *
     * @param activityType typ aktywności (np. BIEGANIE, JAZDA_NA_ROWERE)
     * @return lista treningów pasujących do typu aktywności
     */
    @Override
    public List<Training> findAllTrainingByActivityType(ActivityType activityType) {
        return trainingRepository.findTrainingsByActivityType(activityType);
    }

    /**
     * Zwraca wszystkie treningi przypisane do użytkownika o podanym ID.
     *
     * @param userId identyfikator użytkownika
     * @return lista treningów użytkownika
     */
    @Override
    public List<Training> findAllTrainingByUserId(Long userId) {
        return trainingRepository.findTrainingsByUserId(userId);
    }

    /**
     * Tworzy nowy trening.
     * Rzuca wyjątek, jeśli trening posiada już ID (czyli istnieje w bazie).
     *
     * @param training trening do utworzenia
     * @return utworzony trening zapisany w bazie danych
     * @throws IllegalArgumentException jeśli trening ma już przypisane ID
     */
    @Override
    public Training createTraining(final Training training) {
        log.info("Creating training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, create is not permitted!");
        }
        return trainingRepository.save(training);
    }

    /**
     * Aktualizuje istniejący trening na podstawie ID.
     * Jeśli ID jest null, rzucany jest wyjątek.
     *
     * @param id identyfikator treningu do aktualizacji
     * @param training dane do zaktualizowania
     * @return zaktualizowany trening
     * @throws IllegalArgumentException jeśli ID jest null
     */
    @Override
    public Training updateTraining(final Long id, final Training training) {
        log.info("Updating Training id: {} with data: {}", id, training);
        if (id == null) {
            throw new IllegalArgumentException("Training does not exist, update is not permitted!");
        }
        training.setId(id);
        return trainingRepository.save(training);
    }
}
