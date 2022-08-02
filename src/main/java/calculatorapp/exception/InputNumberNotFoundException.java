package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InputNumberNotFoundException extends AbstractThrowableProblem {
    public InputNumberNotFoundException() {
        super(URI.create("calculator/number-not-found"), "Missing input", Status.BAD_REQUEST, "One or both numbers are missing.");
    }
}
