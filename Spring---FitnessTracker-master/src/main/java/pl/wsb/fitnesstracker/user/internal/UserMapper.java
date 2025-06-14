package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Komponent odpowiedzialny za mapowanie danych użytkownika między encjami a DTO.
 * Ułatwia konwersję między formatami używanymi wewnątrz systemu (User) a formatami wykorzystywanymi na zewnątrz (UserDto, SimpleUserDto, EmailUserDto).
 */
@Component
public class UserMapper {

    /**
     * Konwertuje encję użytkownika na pełny DTO użytkownika.
     *
     * @param user encja użytkownika z bazy danych
     * @return obiekt DTO zawierający pełne dane użytkownika
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Konwertuje encję użytkownika na uproszczony DTO (zawiera tylko ID, imię i nazwisko).
     *
     * @param user encja użytkownika
     * @return uproszczony DTO użytkownika
     */
    SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    /**
     * Konwertuje encję użytkownika na DTO zawierający tylko ID i adres e-mail.
     *
     * @param user encja użytkownika
     * @return DTO z ID i e-mailem
     */
    EmailUserDto toEmailDto(User user) {
        return new EmailUserDto(user.getId(), user.getEmail());
    }

    /**
     * Konwertuje pełny DTO użytkownika na encję (obiekt User).
     * Używane przy tworzeniu lub aktualizacji danych w bazie.
     *
     * @param userDto obiekt DTO użytkownika
     * @return encja użytkownika do zapisu w bazie
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

}
