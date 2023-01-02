package ma.myrh.exeptions;

public class JwtExpiredToken extends Exception {


    public JwtExpiredToken() {
        super();
    }

    public JwtExpiredToken(String message) {
        super(message);
    }

    public JwtExpiredToken(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtExpiredToken(Throwable cause) {
        super(cause);
    }
}
