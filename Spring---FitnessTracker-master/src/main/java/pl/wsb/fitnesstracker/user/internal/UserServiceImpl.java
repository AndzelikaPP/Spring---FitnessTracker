package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja serwisu użytkowników.
 * Odpowiada za operacje tworzenia, aktualizacji, usuwania oraz pobierania danych użytkowników.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Tworzy nowego użytkownika.
     *
     * @param user użytkownik do zapisania
     * @return utworzony użytkownik
     * @throws IllegalArgumentException jeśli użytkownik posiada już ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Aktualizuje dane użytkownika o podanym ID.
     *
     * @param id   identyfikator użytkownika
     * @param user dane do zaktualizowania
     * @return zaktualizowany użytkownik
     * @throws IllegalArgumentException jeśli ID jest puste
     */
    @Override
    public User updateUser(final Long id, final User user) {
        log.info("Updating User id: {} with data: {}", id, user);
        if (id == null) {
            throw new IllegalArgumentException("User does not exist, update is not permitted!");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    /**
     * Usuwa użytkownika na podstawie ID.
     *
     * @param id identyfikator użytkownika do usunięcia
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Pobiera użytkownika po jego ID.
     *
     * @param userId identyfikator użytkownika
     * @return obiekt Optional z użytkownikiem lub pusty, jeśli nie znaleziono
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Pobiera użytkownika na podstawie adresu e-mail.
     *
     * @param email adres e-mail
     * @return obiekt Optional z użytkownikiem lub pusty, jeśli nie znaleziono
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Wyszukuje użytkowników, których e-mail zawiera podany ciąg znaków.
     *
     * @param email fragment adresu e-mail
     * @return lista pasujących użytkowników
     */
    @Override
    public List<User> findUsersByEmailLike(final String email) {
        return userRepository.findByEmailLike(email);
    }

    /**
     * Zwraca użytkowników, którzy są starsi niż określona data (na podstawie daty urodzenia).
     *
     * @param date data graniczna
     * @return lista użytkowników starszych niż podana data
     */
    @Override
    public List<User> findUsersOlderThan(LocalDate date) {
        return userRepository.findBirthdateBefore(date);
    }

    /**
     * Zwraca listę wszystkich użytkowników.
     *
     * @return lista użytkowników
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}