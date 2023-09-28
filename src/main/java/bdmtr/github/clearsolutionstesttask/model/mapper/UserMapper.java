package bdmtr.github.clearsolutionstesttask.model.mapper;

import bdmtr.github.clearsolutionstesttask.model.entity.User;
import bdmtr.github.clearsolutionstesttask.model.response.UserResponse;
import bdmtr.github.clearsolutionstesttask.model.request.UserRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
@Tag(name = "UserMapper", description = "Class is responsible for mapping between User entities and DTO")
public class UserMapper {

    public User toModel(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        return user;
    }

    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
