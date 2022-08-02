package calculatorapp.controller;

import calculatorapp.dao.ResultDao;
import calculatorapp.service.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/calculator")
@AllArgsConstructor
public class CalculatorController {
    private CalculatorService service;

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResultDao addNumbers(@RequestParam Optional<String> number, @RequestParam Optional<String> otherNumber) {
        return service.addNumbers(number, otherNumber);
    }

    @GetMapping("/multiply")
    @ResponseStatus(HttpStatus.OK)
    public ResultDao multiplyNumbers(@RequestParam Optional<String> number, @RequestParam Optional<String> otherNumber) {
        return service.multiplyNumbers(number, otherNumber);
    }

    @GetMapping("/subtract")
    @ResponseStatus(HttpStatus.OK)
    public ResultDao subtractNumbers(@RequestParam Optional<String> number, @RequestParam Optional<String> otherNumber) {
        return service.subtractNumbers(number, otherNumber);
    }

    @GetMapping("/divide")
    @ResponseStatus(HttpStatus.OK)
    public ResultDao divideNumbers(@RequestParam Optional<String> number, @RequestParam Optional<String> otherNumber) {
        return service.divideNumbers(number, otherNumber);
    }

    @GetMapping("/fibonacci")
    @ResponseStatus(HttpStatus.OK)
    public ResultDao getFibonacci(@RequestParam Optional<String> number) {
        return service.getFibonacci(number);
    }



}
