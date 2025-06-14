package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;

/**
 *
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Zwraca wszystkie treningi zakończone po podanej dacie.
     *
     * @param date data odniesienia, po której zakończyły się treningi
     * @return lista treningów zakończonych po podanej dacie
     */
    default List<Training> findTrainingsFinishedAfter(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(date))
                .toList();
    }

    /**
     * Zwraca wszystkie treningi o określonym typie aktywności.
     *
     * @param activityType typ aktywności, według którego filtrowane są treningi
     * @return lista treningów o podanym typie aktywności
     */
    default List<Training> findTrainingsByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> training.getActivityType().equals(activityType))
                .toList();
    }

    /**
     * Zwraca wszystkie treningi powiązane z użytkownikiem o podanym identyfikatorze.
     *
     * @param userId identyfikator użytkownika, dla którego mają zostać pobrane treningi
     * @return lista treningów przypisanych do użytkownika o wskazanym ID
     */
    default List<Training> findTrainingsByUserId(Long userId) {
        return findAll().stream()
                .filter(training ->
                        training.getUser() != null && training.getUser().getId() != null && training.getUser().getId().equals(userId)
                )
                .toList();
    }

}
