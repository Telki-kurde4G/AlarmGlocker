package alarmglocker.alarmglocker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class RessourceFoundException extends  RuntimeException{
    public RessourceFoundException (String message){
        super(message);
    }
}
