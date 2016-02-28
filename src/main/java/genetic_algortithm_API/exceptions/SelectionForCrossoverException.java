package genetic_algortithm_API.exceptions;

/**
 * Created by User on 28.02.2016.
 */
public class SelectionForCrossoverException extends Exception {

    private String message;

    public SelectionForCrossoverException(String message) {
        super(message);
    }

    public SelectionForCrossoverException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
