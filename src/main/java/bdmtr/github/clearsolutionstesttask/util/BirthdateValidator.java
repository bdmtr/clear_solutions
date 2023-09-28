package bdmtr.github.clearsolutionstesttask.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Tag(name = "BirthdateValidator", description = "Validates the birthdate - users must be older than 18 years.")
public class BirthdateValidator implements ConstraintValidator<ValidateBirthdate, LocalDate> {
    @Value("${age.restriction}")
    private int minAge;

    @Override
    @Operation(description = "Validate user date depending on current date and his birthdate.")
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return date == null || LocalDate.now().minusYears(minAge).isAfter(date);
    }
}
