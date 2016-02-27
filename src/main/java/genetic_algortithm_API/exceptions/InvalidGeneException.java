package genetic_algortithm_API.exceptions;

/**
 * Created by User on 27.02.2016.
 */
public class InvalidGeneException extends Exception {
    private String message;

    public InvalidGeneException(String message) {
        super(message);
    }

    public InvalidGeneException() {
        super();
    }


    @Override
    public String getMessage() {
        return message;
    }
}
