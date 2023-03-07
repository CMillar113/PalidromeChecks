package cmillar.palindrome.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


//Where the business logic lives
@Service
public class PalindromeService {

    private final Logger LOGGER = LoggerFactory.getLogger(PalindromeService.class);

    private Map<String, Boolean> cache = new HashMap<>();
    private final String pathToCache = "src/main/resources/cache.txt";

    public PalindromeService() {
        loadCacheFromFile();

    }

    public Map<String, Boolean> getCache() {
        return cache;
    }

    /**
     * Compares String to its Reversed, provides positive result if string is a palindrome
     *
     * @param userInput, user input, String
     * @return boolean value, true if palindrome, false if not
     */
    public boolean isPalindrome(String userInput) {

        // check for existing entry, return if so
        if (this.cache.containsKey(userInput)) {
            LOGGER.warn("This word has already been checked, returning result from cache");
            return cache.get(userInput);
        }
        // else calculate
        String reversedInput;
        boolean isPalindrome;
        reversedInput = new StringBuilder(userInput).reverse().toString();
        isPalindrome = userInput.equalsIgnoreCase(reversedInput);

        // update the cache with new entry
        this.cache.put(userInput, isPalindrome);
        saveCacheToFile();
        return isPalindrome;
    }


    /**
     * Checks input is string of letters only, no punctuation, no spaces
     *
     * @param userInput, user input, string
     * @return false if invalid input, true if word matches validation criteria
     */
    public boolean isValidInput(String userInput) {
        if (userInput.isEmpty()) {
            LOGGER.warn("Input is empty, please provide a word");
            return false;
        }
        if (!userInput.matches("[a-z]+")) {
            LOGGER.error("Input must only contain letters");
            return false;
        } else {
            return true;
        }
    }


    /**
     * Load File of previous words into cache
     * If File Does Not Exist Return Without Loading
     */
    private void loadCacheFromFile() {

        File file = new File(pathToCache);

        if (!file.exists()) {
            return;
        }
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            this.cache = (Map<String, Boolean>) objectIn.readObject();

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error loading cache file: " + e.getMessage());
        }

    }


    /**
     * Save Current Cache of valid Words to File
     * Create File if it Does Not Already Exist
     */
    private void saveCacheToFile() {

        try (FileOutputStream fileOut = new FileOutputStream(pathToCache);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(cache);

        } catch (IOException e) {
            LOGGER.error("Error saving cache to file: " + e.getMessage());
        }
    }


}
