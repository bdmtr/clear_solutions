package bdmtr.github.clearsolutionstesttask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class IncorrectDateException  extends RuntimeException{
    public IncorrectDateException(String message) {
        super(message);
    }
}
