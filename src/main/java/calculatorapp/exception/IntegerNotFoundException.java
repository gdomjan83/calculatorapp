package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IntegerNotFoundException extends AbstractThrowableProblem {
    public IntegerNotFoundException(String input) {
        super(URI.create("calculator/not-integer"), "Invalid number.",
                Status.BAD_REQUEST, String.format("Input received is not a whole number: %s", input));
    }
}
