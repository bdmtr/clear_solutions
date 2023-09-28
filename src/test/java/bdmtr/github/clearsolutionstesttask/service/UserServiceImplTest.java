package bdmtr.github.clearsolutionstesttask.service;

import bdmtr.github.clearsolutionstesttask.exception.UserNotFoundException;
import bdmtr.github.clearsolutionstesttask.model.entity.User;
import bdmtr.github.clearsolutionstesttask.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private User testUser2;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@example.com");
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setBirthdate(LocalDate.of(1999, 12, 12));

        testUser2 = new User();
        testUser2.setId(2L);
        testUser2.setEmail("test2@example.com");
        testUser2.setFirstName("John2");
        testUser2.setLastName("Doe");
        testUser2.setBirthdate(LocalDate.of(1998, 12, 12));
    }

    @Test
    public void testSaveUser() {
        Mockito.when(userRepository.save(any(User.class))).thenReturn(testUser);
        User savedUser = userService.save(testUser);
        assertNotNull(savedUser);
        assertEquals(testUser, savedUser);
    }

    @Test
    public void testFindUserById() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        User foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals(testUser, foundUser);
    }

    @Test
    public void testFindUserByIdNotFound() {
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.findById(2L));
    }

    @Test
    public void testDeleteUser() {
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        userService.delete(1L);
        Mockito.verify(userRepository).deleteById(1L);
    }

    @Test
    public void testDeleteUserNotFound() {
        Mockito.when(userRepository.existsById(2L)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.delete(2L));
    }

    @Test
    public void testUpdateUser() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        Mockito.when(userRepository.save(any(User.class))).thenReturn(testUser);
        User updatedUser = new User();
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        User resultUser = userService.update(1L, updatedUser);
        assertNotNull(resultUser);
        assertEquals("UpdatedFirstName", resultUser.getFirstName());
        assertEquals("UpdatedLastName", resultUser.getLastName());
    }

    @Test
    public void testUpdateUserNotFound() {
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        User updatedUser = new User();
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        assertThrows(UserNotFoundException.class, () -> userService.update(2L, updatedUser));
    }

    @Test
    public void testFindByBirthDateBetween() {
        List<User> allUsers = Arrays.asList(testUser, testUser2);
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);
        Mockito.when(userRepository.findByBirthdateBetween(fromDate, toDate)).thenReturn(allUsers);
        List<User> result = userService.findByBirthDateBetween(fromDate, toDate);
        assertEquals(2, result.size());
        assertTrue(result.contains(testUser));
        assertTrue(result.contains(testUser2));
    }
}
