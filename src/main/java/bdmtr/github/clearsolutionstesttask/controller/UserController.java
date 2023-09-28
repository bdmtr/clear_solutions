package bdmtr.github.clearsolutionstesttask.controller;

import bdmtr.github.clearsolutionstesttask.model.entity.User;
import bdmtr.github.clearsolutionstesttask.model.mapper.UserMapper;
import bdmtr.github.clearsolutionstesttask.model.response.UserResponse;
import bdmtr.github.clearsolutionstesttask.model.request.UserRequest;
import bdmtr.github.clearsolutionstesttask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "UserController", description = "Controller class responsible for managing users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @Operation(description = "Add a new user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User successfully created")})
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.save(userMapper.toModel(userRequest));
        UserResponse userResponse = userMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PatchMapping(path = "/{id}")
    @Operation(description = "Update one and more user properties by id")
    public ResponseEntity<UserResponse> patch(@PathVariable Long id,
                                              @RequestBody @Valid UserRequest userRequest) {
        User user = userService.update(id, userMapper.toModel(userRequest));
        UserResponse userResponse = userMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Update all user properties by id")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UserRequest userRequest) {
        User user = userService.update(id, userMapper.toModel(userRequest));
        UserResponse userResponse = userMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Delete user by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "User successfully deleted")})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/dates")
    @Operation(description = "Search for users by birthdate range")
    public ResponseEntity<List<UserResponse>> getUsersByBirthDateBetween(
            @RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        List<User> users = userService.findByBirthDateBetween(fromDate, toDate);
        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }
}
