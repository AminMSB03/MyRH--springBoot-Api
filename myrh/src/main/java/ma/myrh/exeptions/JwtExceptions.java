package ma.myrh.exeptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptions {

    @ExceptionHandler(JwtExpiredToken.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        // create a response entity with the appropriate error message
        return new ResponseEntity<>("there's an error on the token", HttpStatus.UNAUTHORIZED);
    }


}
