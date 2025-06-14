package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Zwraca listę wszystkich treningów.
     *
     * @return lista wszystkich treningów
     */
    List<Training> findAllTraining();

    /**
     * Zwraca wszystkie treningi, które zakończyły się po podanej dacie.
     *
     * @param date data, po której mają być wyszukiwane zakończone treningi
     * @return lista treningów zakończonych po wskazanej dacie
     */
    List<Training> findAllTrainingFinishedAfterTime(Date date);

    /**
     * Zwraca wszystkie treningi o określonym typie aktywności.
     *
     * @param activityType typ aktywności, według którego mają być filtrowane treningi
     * @return lista treningów o danym typie aktywności
     */
    List<Training> findAllTrainingByActivityType(ActivityType activityType);

    /**
     * Zwraca wszystkie treningi przypisane do danego użytkownika.
     *
     * @param userId identyfikator użytkownika, którego treningi mają zostać zwrócone
     * @return lista treningów użytkownika
     */
    List<Training> findAllTrainingByUserId(Long userId);

    /**
     * Tworzy nowy trening.
     *
     * @param training dane treningu do zapisania
     * @return zapisany obiekt treningu
     */
    Training createTraining(Training training);

    /**
     * Aktualizuje istniejący trening na podstawie podanego ID.
     *
     * @param id identyfikator treningu do aktualizacji
     * @param training nowe dane treningu
     * @return zaktualizowany obiekt treningu
     */
    Training updateTraining(final Long id, final Training training);

}
