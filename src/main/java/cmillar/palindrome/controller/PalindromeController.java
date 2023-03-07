package cmillar.palindrome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cmillar.palindrome.service.PalindromeService;

@RestController
public class PalindromeController {

//Controllers control access to services

    private PalindromeService palindromeService;

    @Autowired
    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    /**
     * map pathway to function, api to check palindrome
     *
     * @return
     */
    @GetMapping("api/palindrome/{userInput}")
    public Boolean isPalindrome(@PathVariable String userInput) {
        System.out.println("Endpoint being hit");
        return palindromeService.isPalindrome(userInput);

    }

    /**
     * request cache file
     *
     * @return cache file
     */
    @GetMapping("api/palindrome/cache")
    public String getCache() {
        return palindromeService.getCache().toString();
    }

    @PostMapping("api/palindrome/")
    public String isPalindrome(@RequestParam(name = "username") String username,
                               @RequestParam(name = "input") String input) {

        if (palindromeService.isValidInput(input)) {
            boolean isPalindrome = palindromeService.isPalindrome(input);
            String result = "Hello " + username + "\n";
            if (isPalindrome) {
                result += input + " is a palindrome.";
            } else {
                result += input + " is not a palindrome";
            }
            return result;
        } else {
            return "Input must only contain letters and also must not be empty";
        }
    }


}
