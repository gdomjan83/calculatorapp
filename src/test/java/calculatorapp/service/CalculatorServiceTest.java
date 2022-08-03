package calculatorapp.service;

import calculatorapp.dto.ResultDto;
import calculatorapp.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    CalculatorService service = new CalculatorService();
    String[] numbers = {"4.3", "3", "-2"};
    String[] otherNumbers = {"6.1", "8", "-7.2"};

    //Addition tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing addition with correct input values")
    void testAddNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {10.4, 11, -9.2};
        ResultDto result = service.addNumbers(Optional.of(numbers[repetitionInfo.getCurrentRepetition() - 1]), Optional.of(otherNumbers[repetitionInfo.getCurrentRepetition() - 1]));
        assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], result.getResult(), 0.001);
    }

    @Test
    @DisplayName("Testing addition with missing input")
    void testAddNumbersMissingInput() {
        InputNumberNotFoundException exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.addNumbers(Optional.empty(), Optional.of("3")));
        assertEquals("One or both numbers are missing.", exception.getDetail());

        exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.addNumbers(Optional.of("6"), Optional.empty()));
        assertEquals("Missing input", exception.getTitle());
    }

    @Test
    @DisplayName("Testing addition with incorrect input")
    void testAddNumbersWrongInputFormat() {
        IncorrectNumberFormatException exception = assertThrows(IncorrectNumberFormatException.class,
                () -> service.addNumbers(Optional.of("4.5?"), Optional.of("3")));
        assertEquals("Input received is not a number: 4.5?", exception.getDetail());
    }

    //Multiplication tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing multiplication with correct input values")
    void testMultiplyNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {26.23, 24, 14.4};
        ResultDto result = service.multiplyNumbers(Optional.of(numbers[repetitionInfo.getCurrentRepetition() - 1]), Optional.of(otherNumbers[repetitionInfo.getCurrentRepetition() - 1]));
        assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], result.getResult(), 0.001);
    }

    @Test
    @DisplayName("Testing multiplication with missing input")
    void testMultiplyNumbersMissingInput() {
        InputNumberNotFoundException exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.multiplyNumbers(Optional.empty(), Optional.of("3")));
        assertEquals("One or both numbers are missing.", exception.getDetail());

        exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.multiplyNumbers(Optional.of("6"), Optional.empty()));
        assertEquals("Missing input", exception.getTitle());
    }

    @Test
    @DisplayName("Testing multiplication with incorrect input")
    void testMultiplyNumbersWrongInputFormat() {
        IncorrectNumberFormatException exception = assertThrows(IncorrectNumberFormatException.class,
                () -> service.multiplyNumbers(Optional.of("4.5?"), Optional.of("3")));
        assertEquals("Input received is not a number: 4.5?", exception.getDetail());
    }

    //Subtraction tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing subtraction with correct input values")
    void testSubtractNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {-1.8, -5, 5.2};
        ResultDto result = service.subtractNumbers(Optional.of(numbers[repetitionInfo.getCurrentRepetition() - 1]), Optional.of(otherNumbers[repetitionInfo.getCurrentRepetition() - 1]));
        assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], result.getResult(), 0.001);
    }

    @Test
    @DisplayName("Testing subtraction with missing input")
    void testSubtractNumbersMissingInput() {
        InputNumberNotFoundException exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.subtractNumbers(Optional.empty(), Optional.of("3")));
        assertEquals("One or both numbers are missing.", exception.getDetail());

        exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.subtractNumbers(Optional.of("6"), Optional.empty()));
        assertEquals("Missing input", exception.getTitle());
    }

    @Test
    @DisplayName("Testing subtraction with incorrect input")
    void testSubtractNumbersWrongInputFormat() {
        IncorrectNumberFormatException exception = assertThrows(IncorrectNumberFormatException.class,
                () -> service.subtractNumbers(Optional.of("4.5?"), Optional.of("3")));
        assertEquals("Input received is not a number: 4.5?", exception.getDetail());
    }

    //Division tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing division with correct input values")
    void testDivideNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {0.704918, 0.375, 0.27777};
        ResultDto result = service.divideNumbers(Optional.of(numbers[repetitionInfo.getCurrentRepetition() - 1]), Optional.of(otherNumbers[repetitionInfo.getCurrentRepetition() - 1]));
        assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], result.getResult(), 0.001);
    }

    @Test
    @DisplayName("Testing subtraction with missing input")
    void testDivideNumbersMissingInput() {
        InputNumberNotFoundException exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.divideNumbers(Optional.empty(), Optional.of("3")));
        assertEquals("One or both numbers are missing.", exception.getDetail());

        exception = assertThrows(InputNumberNotFoundException.class,
                () -> service.divideNumbers(Optional.of("6"), Optional.empty()));
        assertEquals("Missing input", exception.getTitle());
    }

    @Test
    @DisplayName("Testing subtraction with incorrect input")
    void testDivideNumbersWrongInputFormat() {
        IncorrectNumberFormatException exception = assertThrows(IncorrectNumberFormatException.class,
                () -> service.divideNumbers(Optional.of("4.5?"), Optional.of("3")));
        assertEquals("Input received is not a number: 4.5?", exception.getDetail());
    }

    @Test
    @DisplayName("Testing division with zero as divider")
    void testDivideNumbersZeroDivision() {
        ZeroDivisionException exception = assertThrows(ZeroDivisionException.class,
                () -> service.divideNumbers(Optional.of("4.4"), Optional.of("0")));
        assertEquals("Division with zero is not allowed.", exception.getDetail());
    }

    //Fibonacci tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing fibonacci sequence with correct input")
    void testGetFibonacci(RepetitionInfo repetitionInfo) {
        String[] input = {"2", "6", "19"};
        int[] output = {1, 8, 4181};

        ResultDto resultDto = service.getFibonacci(Optional.of(input[repetitionInfo.getCurrentRepetition() - 1]));
        assertEquals(output[repetitionInfo.getCurrentRepetition() - 1], resultDto.getResult());
    }

    @Test
    @DisplayName("Testing fibonacci sequence with missing input")
    void testGetFibonacciMissingInput() {
        FibonacciInputNumberNotFound exception = assertThrows(FibonacciInputNumberNotFound.class,
                () -> service.getFibonacci(Optional.empty()));
        assertEquals("The input number is missing.", exception.getDetail());
    }

    @Test
    @DisplayName("Testing fibonacci sequence with wrong input (not whole number)")
    void testGetFibonacciNonIntegerInput() {
        IntegerNotFoundException exception = assertThrows(IntegerNotFoundException.class,
                () -> service.getFibonacci(Optional.of("4.5")));
        assertEquals("Input received is not a whole number: 4.5", exception.getDetail());
    }

    @Test
    @DisplayName("Testing fibonacci sequence with wrong input (not positive number)")
    void testGetFibonacciNotPositiveInput() {
        WrongFibonacciInputException exception = assertThrows(WrongFibonacciInputException.class,
                () -> service.getFibonacci(Optional.of("-1")));
        assertEquals("Fibonacci number can not be displayed for number: -1. Input must be larger than zero.", exception.getDetail());

        exception = assertThrows(WrongFibonacciInputException.class,
                () -> service.getFibonacci(Optional.of("0")));
        assertEquals("Fibonacci number can not be displayed for number: 0. Input must be larger than zero.", exception.getDetail());
    }

}