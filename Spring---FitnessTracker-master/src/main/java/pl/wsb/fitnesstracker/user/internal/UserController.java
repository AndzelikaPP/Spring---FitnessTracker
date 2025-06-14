package pl.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;
/**
 * Kontroler REST do zarządzania użytkownikami w systemie.
 * Oferuje operacje pobierania, tworzenia, aktualizacji i usuwania użytkowników.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Zwraca listę wszystkich użytkowników w formacie UserDto.
     *
     * @return lista wszystkich użytkowników
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Zwraca uproszczoną listę użytkowników z podstawowymi danymi.
     *
     * @return lista użytkowników w formacie SimpleUserDto
     */
    @GetMapping("/simple")
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Zwraca szczegóły użytkownika o podanym identyfikatorze.
     *
     * @param id identyfikator użytkownika
     * @return obiekt UserDto lub null, jeśli użytkownik nie istnieje
     */
    @GetMapping("/{id}")
    public UserDto getUserDetailsById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    /**
     * Wyszukuje użytkowników, których adres e-mail zawiera podany fragment.
     *
     * @param email fragment adresu e-mail do wyszukania
     * @return lista użytkowników pasujących do wzorca
     */
    @GetMapping("/email")
    public List<EmailUserDto> findUserByEmailLike(@RequestParam String email) {
        return userService.findUsersByEmailLike(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Zwraca listę użytkowników starszych niż określona data.
     *
     * @param time data, względem której sprawdzany jest wiek użytkowników
     * @return lista użytkowników starszych niż podana data
     */
    @GetMapping("/older/{time}")
    public List<UserDto> findUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Tworzy nowego użytkownika na podstawie przekazanego obiektu UserDto.
     *
     * @param userDto dane użytkownika do utworzenia
     * @return utworzony użytkownik w formacie UserDto
     * @throws InterruptedException w przypadku problemów z przetwarzaniem
     */
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userMapper.toDto(

                    userService.createUser(userMapper.toEntity(userDto))
                )
        );
    }

    /**
     * Aktualizuje dane użytkownika o wskazanym ID.
     *
     * @param userDto zaktualizowane dane użytkownika
     * @param id identyfikator użytkownika do aktualizacji
     * @return zaktualizowany obiekt UserDto
     * @throws InterruptedException w przypadku problemów z przetwarzaniem
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Long id) throws InterruptedException {
        return userMapper.toDto(
                userService.updateUser(id, userMapper.toEntity(userDto))
        );
    }

    /**
     * Usuwa użytkownika o podanym identyfikatorze.
     *
     * @param id identyfikator użytkownika do usunięcia
     * @return odpowiedź z kodem 204 No Content po pomyślnym usunięciu
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}

