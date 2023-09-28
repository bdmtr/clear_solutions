package bdmtr.github.clearsolutionstesttask.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "User id", example = "1")
    private Long id;
    @Column(name = "email", nullable = false)
    @Schema(name = "User email", example = "user@gmail.com")
    private String email;
    @Column(name = "first_name", nullable = false)
    @Schema(name = "User firstname", example = "John")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Schema(name = "User lastname", example = "Userman")
    private String lastName;
    @Column(name = "birthdate", nullable = false)
    @Schema(name = "User birthdate", example = "1998-01-15")
    private LocalDate birthdate;
    @Column(name = "address")
    @Schema(name = "User address", example = "Gondor, Halfling street")
    private String address;
    @Column(name = "phone_number")
    @Schema(name = "User phone number", example = "+332333332")
    private String phoneNumber;
}
