package bdmtr.github.clearsolutionstesttask.model.response;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Schema(name = "User id", example = "1")
    private Long id;
    @Schema(name = "User email", example = "user@gmail.com")
    private String email;
    @Schema(name = "User firstname", example = "John")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Schema(name = "User address", example = "Gondor, Halfling street")
    private String address;
    @Schema(name = "User phone number", example = "+332333332")
    private String phoneNumber;
}
