package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Training} was not found.
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {

    /**
     * Prywatny konstruktor pomocniczy przyjmujący komunikat błędu.
     *
     * @param message treść komunikatu błędu
     */
    private TrainingNotFoundException(String message) {
        super(message);
    }

    /**
     * Konstruktor tworzący wyjątek na podstawie ID nieodnalezionego treningu.
     *
     * @param id identyfikator treningu, który nie został znaleziony
     */
    public TrainingNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }

}
