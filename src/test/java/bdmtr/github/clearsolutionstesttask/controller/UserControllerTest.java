package bdmtr.github.clearsolutionstesttask.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import bdmtr.github.clearsolutionstesttask.model.entity.User;
import bdmtr.github.clearsolutionstesttask.model.mapper.UserMapper;
import bdmtr.github.clearsolutionstesttask.model.request.UserRequest;
import bdmtr.github.clearsolutionstesttask.model.response.UserResponse;
import bdmtr.github.clearsolutionstesttask.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private UserRequest userRequest;
    private User user;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setEmail("user@gmail.com");
        userRequest.setFirstName("John");
        userRequest.setLastName("Userman");
        userRequest.setBirthdate(LocalDate.of(1998, 1, 15));

        user = new User();
        user.setId(1L);
        user.setEmail("user@gmail.com");
        user.setFirstName("John");
        user.setLastName("Userman");
        user.setBirthdate(LocalDate.of(1998, 1, 15));

        userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setEmail("user@gmail.com");
        userResponse.setFirstName("John");
        userResponse.setLastName("Userman");
        userResponse.setBirthdate(LocalDate.of(1998, 1, 15));
    }

    @Test
    public void testRegister() {
        when(userMapper.toModel(any(UserRequest.class))).thenReturn(user);
        when(userService.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userResponse);
        ResponseEntity<UserResponse> responseEntity = userController.register(userRequest);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
        verify(userMapper).toModel(any(UserRequest.class));
        verify(userService).save(any(User.class));
        verify(userMapper).toDto(any(User.class));
    }


    @Test
    public void testPatch() {
        when(userMapper.toModel(userRequest)).thenReturn(user);
        when(userService.update(anyLong(), any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponse);
        ResponseEntity<UserResponse> responseEntity = userController.patch(1L, userRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
        verify(userMapper).toModel(userRequest);
        verify(userService).update(1L, user);
        verify(userMapper).toDto(user);
    }

    @Test
    public void testUpdate() {
        when(userMapper.toModel(userRequest)).thenReturn(user);
        when(userService.update(anyLong(), any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponse);
        ResponseEntity<UserResponse> responseEntity = userController.update(1L, userRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
        verify(userMapper).toModel(userRequest);
        verify(userService).update(1L, user);
        verify(userMapper).toDto(user);
    }

    @Test
    public void testDelete() {
        ResponseEntity<Void> responseEntity = userController.delete(user.getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService).delete(user.getId());
    }

    @Test
    public void testGetUsersByBirthDateBetween() {
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("user1@gmail.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setBirthdate(LocalDate.of(1995, 5, 15));

        List<UserResponse> userResponses = new ArrayList<>();
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setId(1L);
        userResponse1.setEmail("user1@gmail.com");
        userResponse1.setFirstName("John");
        userResponse1.setLastName("Doe");
        userResponse1.setBirthdate(LocalDate.of(1995, 5, 15));

        when(userService.findByBirthDateBetween(fromDate, toDate)).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(userResponse1);

        ResponseEntity<List<UserResponse>> responseEntity = userController.getUsersByBirthDateBetween(fromDate, toDate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponses, responseEntity.getBody());
        verify(userService).findByBirthDateBetween(fromDate, toDate);
        verify(userMapper, times(users.size())).toDto(any(User.class));
    }
}
