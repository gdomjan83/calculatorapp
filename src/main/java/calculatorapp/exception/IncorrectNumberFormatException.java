package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IncorrectNumberFormatException extends AbstractThrowableProblem {
    public IncorrectNumberFormatException(String input) {
        super(URI.create("calculator/incorrect-number"), "Incorrect number.",
                Status.BAD_REQUEST, String.format("Input received is not a number: %s", input));
    }
}
