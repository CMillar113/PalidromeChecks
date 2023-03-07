package cmillar.palindrome;

import cmillar.palindrome.service.PalindromeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PalindromeApplicationTests {

	PalindromeService palindromeService = new PalindromeService();

	@Test
	void contextLoads() {
	}


	@Test
	void isPalindrome_negative() {
		String input = "sauce";
		boolean actual = palindromeService.isPalindrome(input);
		boolean expected = false;
		assert actual == expected;
	}

	@Test
	void isPalindrome_positive() {
		String input = "racecar";
		boolean actual = palindromeService.isPalindrome(input);
		boolean expected = true;
		assert actual == expected;
	}

	@Test
	void isValidInput_valid(){
		String input = "racecar";
		boolean actual = palindromeService.isValidInput(input);
		boolean expected = true;
		assert actual == expected;
	}


	@Test
	void isValidInput_Invalid_numbers(){
		String input = "racecar64";
		boolean actual = palindromeService.isValidInput(input);
		boolean expected = false;
		assert actual == expected;
	}

	@Test
	void isValidInput_invalid_punctuation(){
		String input = "race^car";
		boolean actual = palindromeService.isValidInput(input);
		boolean expected = false;
		assert actual == expected;
	}

	@Test
	void isValidInput_invalid_spaces(){
		String input = "race car";
		boolean actual = palindromeService.isValidInput(input);
		boolean expected = false;
		assert actual == expected;
	}

}
