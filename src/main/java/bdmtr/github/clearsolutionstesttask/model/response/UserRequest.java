package bdmtr.github.clearsolutionstesttask.model.response;

import bdmtr.github.clearsolutionstesttask.util.ValidateBirthdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Email can`t be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Incorrect email address")
    @Schema(name = "User email" , example = "user@gmail.com")
    public String email;
    @NotBlank(message = "FirstName can`t be blank")
    @Schema(name = "User firstname", example = "John")
    public String firstName;
    @NotBlank(message = "LastName can`t be blank")
    @Schema(name = "User lastname", example = "Userman")
    public String lastName;
    @NotNull(message = "Birthdate can`t be blank")
    @ValidateBirthdate
    @Schema(name = "User birthdate", example = "1998-01-15")
    public LocalDate birthdate;
    public String address;
    public String phoneNumber;
}
