package calculatorapp.controller;

import calculatorapp.dto.ResultDto;
import calculatorapp.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/calculator")
@AllArgsConstructor
@Tag(name = "Arithmetic operations")
public class CalculatorController {
    private CalculatorService service;

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Operation successful.")
    @Operation(summary = "Adding two numbers.")
    public ResultDto addNumbers(@Parameter(example = "3.5") @RequestParam Optional<String> number, @Parameter(example = "10") @RequestParam Optional<String> otherNumber) {
        return service.addNumbers(number, otherNumber);
    }

    @GetMapping("/multiply")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Operation successful.")
    @Operation(summary = "Multiplying two numbers.")
    public ResultDto multiplyNumbers(@Parameter(example = "2") @RequestParam Optional<String> number, @Parameter(example = "4.2") @RequestParam Optional<String> otherNumber) {
        return service.multiplyNumbers(number, otherNumber);
    }

    @GetMapping("/subtract")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Operation successful.")
    @Operation(summary = "Subtracting two numbers.")
    public ResultDto subtractNumbers(@Parameter(example = "11") @RequestParam Optional<String> number, @Parameter(example = "7") @RequestParam Optional<String> otherNumber) {
        return service.subtractNumbers(number, otherNumber);
    }

    @GetMapping("/divide")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Operation successful.")
    @Operation(summary = "Dividing two numbers.")
    public ResultDto divideNumbers(@Parameter(example = "10") @RequestParam Optional<String> number, @Parameter(example = "2.5")@RequestParam Optional<String> otherNumber) {
        return service.divideNumbers(number, otherNumber);
    }

    @GetMapping("/fibonacci")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Operation successful.")
    @Operation(summary = "Getting the n-th Fibonacci number.")
    public ResultDto getFibonacci(@Parameter(example = "6") @RequestParam Optional<String> number) {
        return service.getFibonacci(number);
    }
}
