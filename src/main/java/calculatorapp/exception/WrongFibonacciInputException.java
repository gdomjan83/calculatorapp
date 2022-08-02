package calculatorapp.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class WrongFibonacciInputException extends AbstractThrowableProblem {
    public WrongFibonacciInputException(int input) {
        super(URI.create("calculator/wrong-fibonacci-input"), "Wrong input.",
                Status.BAD_REQUEST, String.format("Fibonacci number can not be displayed for number: %s. Input must be larger than zero.", input));
    }
}
