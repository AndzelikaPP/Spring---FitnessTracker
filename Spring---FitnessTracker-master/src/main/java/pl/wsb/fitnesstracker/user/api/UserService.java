package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Tworzy nowego użytkownika i zapisuje go w bazie danych.
     *
     * @param user obiekt użytkownika do utworzenia
     * @return utworzony użytkownik
     */
    User createUser(User user);

    /**
     * Aktualizuje dane istniejącego użytkownika na podstawie jego ID.
     *
     * @param id identyfikator użytkownika do zaktualizowania
     * @param user obiekt zawierający nowe dane użytkownika
     * @return zaktualizowany użytkownik
     */
    User updateUser(Long id, User user);

    /**
     * Usuwa użytkownika z bazy danych na podstawie jego ID.
     *
     * @param id identyfikator użytkownika do usunięcia
     */
    void deleteUser(Long id);

}
