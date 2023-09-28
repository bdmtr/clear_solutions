package bdmtr.github.clearsolutionstesttask.model.mapper;

import bdmtr.github.clearsolutionstesttask.model.entity.User;

import bdmtr.github.clearsolutionstesttask.model.response.UserResponse;
import bdmtr.github.clearsolutionstesttask.model.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setBirthdate(LocalDate.of(2000, 10, 10));

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setBirthdate(LocalDate.of(2000, 10, 10));
    }

    @Test
    public void testToModelMapping() {
        User mappedUser = userMapper.toModel(userRequest);
        assertEquals(user.getEmail(), mappedUser.getEmail());
        assertEquals(user.getFirstName(), mappedUser.getFirstName());
        assertEquals(user.getLastName(), mappedUser.getLastName());
        assertEquals(user.getBirthdate(), mappedUser.getBirthdate());
    }

    @Test
    public void testToDtoMapping() {
        UserResponse mappedUserResponse = userMapper.toDto(user);

        assertEquals(user.getId(), mappedUserResponse.getId());
        assertEquals(user.getEmail(), mappedUserResponse.getEmail());
        assertEquals(user.getFirstName(), mappedUserResponse.getFirstName());
        assertEquals(user.getLastName(), mappedUserResponse.getLastName());
        assertEquals(user.getBirthdate(), mappedUserResponse.getBirthdate());
    }
}