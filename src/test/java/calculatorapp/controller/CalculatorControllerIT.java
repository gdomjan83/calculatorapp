package calculatorapp.controller;

import calculatorapp.dao.ResultDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerIT {

    @Autowired
    WebTestClient webTestClient;

    String[] numbers = {"4.3", "3", "-2"};
    String[] otherNumbers = {"6.1", "8", "-7.2"};


    //Addition tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("IT Testing addition with correct input values")
    void testAddNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {10.4, 11, -9.2};
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/add")
                        .queryParam("number", numbers[repetitionInfo.getCurrentRepetition() - 1])
                        .queryParam("otherNumber", otherNumbers[repetitionInfo.getCurrentRepetition() - 1]).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDao.class)
                .value(r -> assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], r.getResult(), 0.0001));
    }

    @Test
    @DisplayName("IT Testing addition with missing input")
    void testAddNumbersMissingInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/add").queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("One or both numbers are missing.", r.getDetail()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/add").queryParam("number", "6").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Missing input", r.getTitle()));
    }

    @Test
    @DisplayName("IT Testing addition with incorrect input")
    void testAddNumbersWrongInputFormat() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/add")
                        .queryParam("number", "4*l1")
                        .queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Input received is not a number: 4*l1", r.getDetail()));
    }

    //Multiplication tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("IT Testing multiplication with correct input values")
    void testMultiplyNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {26.23, 24, 14.4};
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/multiply")
                        .queryParam("number", numbers[repetitionInfo.getCurrentRepetition() - 1])
                        .queryParam("otherNumber", otherNumbers[repetitionInfo.getCurrentRepetition() - 1]).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDao.class)
                .value(r -> assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], r.getResult(), 0.0001));
    }

    @Test
    @DisplayName("IT Testing multiplication with missing input")
    void testMultiplyNumbersMissingInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/multiply").queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("One or both numbers are missing.", r.getDetail()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/multiply").queryParam("number", "6").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Missing input", r.getTitle()));
    }

    @Test
    @DisplayName("IT Testing multiplication with incorrect input")
    void testMultiplyNumbersWrongInputFormat() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/multiply")
                        .queryParam("number", "4*l1")
                        .queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Input received is not a number: 4*l1", r.getDetail()));
    }

    //Subtraction tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("IT Testing subtraction with correct input values")
    void testSubtractNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {-1.8, -5, 5.2};
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/subtract")
                        .queryParam("number", numbers[repetitionInfo.getCurrentRepetition() - 1])
                        .queryParam("otherNumber", otherNumbers[repetitionInfo.getCurrentRepetition() - 1]).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDao.class)
                .value(r -> assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], r.getResult(), 0.0001));
    }

    @Test
    @DisplayName("IT Testing subtraction with missing input")
    void testSubtractNumbersMissingInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/subtract").queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("One or both numbers are missing.", r.getDetail()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/subtract").queryParam("number", "6").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Missing input", r.getTitle()));
    }

    @Test
    @DisplayName("IT Testing subtraction with incorrect input")
    void testSubtractNumbersWrongInputFormat() {
        webTestClient
                .get().uri(uriBuilder -> uriBuilder.path("api/calculator/subtract")
                        .queryParam("number", "4*l1")
                        .queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Input received is not a number: 4*l1", r.getDetail()));
    }

    //Division tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("IT Testing division with correct input values")
    void testDivisionNumbersCorrectInput(RepetitionInfo repetitionInfo) {
        double[] resultNumber = {0.704918, 0.375, 0.27777};
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/divide")
                        .queryParam("number", numbers[repetitionInfo.getCurrentRepetition() - 1])
                        .queryParam("otherNumber", otherNumbers[repetitionInfo.getCurrentRepetition() - 1]).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDao.class)
                .value(r -> assertEquals(resultNumber[repetitionInfo.getCurrentRepetition() - 1], r.getResult(), 0.0001));
    }

    @Test
    @DisplayName("IT Testing division with missing input")
    void testDivisionNumbersMissingInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/divide").queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("One or both numbers are missing.", r.getDetail()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/divide").queryParam("number", "6").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Missing input", r.getTitle()));
    }

    @Test
    @DisplayName("IT Testing division with incorrect input")
    void testDivisionNumbersWrongInputFormat() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/divide")
                        .queryParam("number", "4*l1")
                        .queryParam("otherNumber", "3").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Input received is not a number: 4*l1", r.getDetail()));
    }

    @Test
    @DisplayName("IT Testing division with zero as divider")
    void testDivideNumbersZeroDivision() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/divide")
                        .queryParam("number", "22")
                        .queryParam("otherNumber", "0").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Division with zero is not allowed.", r.getDetail()));
    }

    //Fibonacci tests
    @RepeatedTest(value = 3, name = "Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("IT Testing fibonacci sequence with correct input")
    void testGetFibonacci(RepetitionInfo repetitionInfo) {
        String[] input = {"2", "6", "19"};
        int[] output = {1, 8, 4181};

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/fibonacci")
                        .queryParam("number", input[repetitionInfo.getCurrentRepetition() - 1]).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDao.class)
                .value(r -> assertEquals(output[repetitionInfo.getCurrentRepetition() - 1], r.getResult()));
    }

    @Test
    @DisplayName("IT Testing fibonacci sequence with wrong input (not whole number)")
    void testGetFibonacciNonIntegerInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/fibonacci")
                        .queryParam("number", "3.4").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Input received is not a whole number: 3.4", r.getDetail()));
    }

    @Test
    @DisplayName("IT Testing fibonacci sequence with wrong input (not positive number)")
    void testGetFibonacciNotPositiveInput() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/fibonacci")
                        .queryParam("number", "-1").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Fibonacci number can not be displayed for number: -1. Input must be larger than zero.", r.getDetail()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/calculator/fibonacci")
                        .queryParam("number", "0").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Problem.class)
                .value(r -> assertEquals("Fibonacci number can not be displayed for number: 0. Input must be larger than zero.", r.getDetail()));
    }
}