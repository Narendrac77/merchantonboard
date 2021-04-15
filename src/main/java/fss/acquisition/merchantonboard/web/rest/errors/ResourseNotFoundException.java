package fss.acquisition.merchantonboard.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourseNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourseNotFoundException(String message){
        super(message);
    }

}
