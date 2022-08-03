package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class FibonacciInputNumberNotFound extends AbstractThrowableProblem {
    public FibonacciInputNumberNotFound() {
        super(URI.create("calculator/number-not-found"), "Missing input", Status.BAD_REQUEST, "The input number is missing.");
    }
}
