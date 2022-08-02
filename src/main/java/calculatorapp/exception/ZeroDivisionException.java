package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ZeroDivisionException extends AbstractThrowableProblem {
    public ZeroDivisionException() {
        super(URI.create("calculator/zero-division"), "Zero division.", Status.BAD_REQUEST, "Division with zero is not allowed.");
    }
}
