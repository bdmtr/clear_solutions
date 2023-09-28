package bdmtr.github.clearsolutionstesttask.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BirthdateValidatorTest {

    private BirthdateValidator birthdateValidator;
    private int minAge;

    @BeforeEach
    void setUp() {
        minAge = 18;
        birthdateValidator = new BirthdateValidator();
        ValidateBirthdate validateBirthdate = mock(ValidateBirthdate.class);
        birthdateValidator.initialize(validateBirthdate);
    }

    @Test
    public void testValidBirthdate() {
        LocalDate validBirthdate = LocalDate.now().minusYears(minAge);
        assertTrue(birthdateValidator.isValid(validBirthdate, mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void testInvalidBirthdate() {
        assertFalse(birthdateValidator.isValid(LocalDate.now(), mock(ConstraintValidatorContext.class)));
    }
}





