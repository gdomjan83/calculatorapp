package calculatorapp.service;

import calculatorapp.dto.ResultDto;
import calculatorapp.exception.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculatorService {

    public ResultDto addNumbers(Optional<String> number, Optional<String> anotherNumber) {
        double firstNumber = parseStringForDouble(number);
        double secondNumber = parseStringForDouble(anotherNumber);
        return new ResultDto(firstNumber + secondNumber);
    }

    public ResultDto multiplyNumbers(Optional<String> number, Optional<String> anotherNumber) {
        double firstNumber = parseStringForDouble(number);
        double secondNumber = parseStringForDouble(anotherNumber);
        return new ResultDto(firstNumber * secondNumber);
    }

    public ResultDto subtractNumbers(Optional<String> number, Optional<String> anotherNumber) {
        double firstNumber = parseStringForDouble(number);
        double secondNumber = parseStringForDouble(anotherNumber);
        return new ResultDto(firstNumber - secondNumber);
    }

    public ResultDto divideNumbers(Optional<String> number, Optional<String> anotherNumber) {
        double firstNumber = parseStringForDouble(number);
        double secondNumber = parseStringForDouble(anotherNumber);
        checkForZero(secondNumber);
        return new ResultDto(firstNumber / secondNumber);
    }

    public ResultDto getFibonacci(Optional<String> number) {
        int inputNumber = parseStringForInteger(number);
        if (inputNumber == 1 || inputNumber == 2) {
            return new ResultDto(1);
        } else {
            return new ResultDto(calculateFibonacciNumber(inputNumber));
        }
    }

    private int calculateFibonacciNumber(int input) {
        int firstNumber = 1;
        int secondNumber = 1;
        int temp;
        for (int i = 3; i <= input; i++) {
            temp = secondNumber;
            secondNumber += firstNumber;
            firstNumber = temp;
        }
        return secondNumber;
    }

    private String checkForNumberPresent(Optional<String> number) {
        return number.orElseThrow(() -> new InputNumberNotFoundException());
    }

    private double parseStringForDouble(Optional<String> number) {
        String numberInString = checkForNumberPresent(number);
        try {
            return Double.parseDouble(numberInString);
        } catch (NumberFormatException nfe) {
            throw new IncorrectNumberFormatException(numberInString);
        }
    }

    private int parseStringForInteger(Optional<String> number) {
        String numberInString = checkForNumberPresent(number);
        try {
            int result = Integer.parseInt(numberInString);
            checkPositiveInput(result);
            return result;
        } catch (NumberFormatException nfe) {
            throw new IntegerNotFoundException(numberInString);
        }
    }

    private void checkForZero(double number) {
        if (number == 0) {
            throw new ZeroDivisionException();
        }
    }

    private void checkPositiveInput(int number) {
        if (number <= 0) {
            throw new WrongFibonacciInputException(number);
        }
    }
}
