package genetic_algortithm_API.exceptions;

/**
 * Created by User on 27.02.2016.
 */
public class IllegalLengthOfPhenotypeException extends Exception {
    private String message;

    public IllegalLengthOfPhenotypeException(String message) {
        super(message);
    }

    public IllegalLengthOfPhenotypeException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
