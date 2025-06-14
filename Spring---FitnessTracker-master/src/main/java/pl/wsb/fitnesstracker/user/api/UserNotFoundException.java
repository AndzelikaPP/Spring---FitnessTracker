package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    /**
     * Prywatny konstruktor pomocniczy z komunikatem błędu.
     *
     * @param message treść komunikatu o błędzie
     */
    private UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Tworzy wyjątek z domyślnym komunikatem wskazującym brak użytkownika o podanym ID.
     *
     * @param id identyfikator użytkownika, którego nie znaleziono
     */
    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
