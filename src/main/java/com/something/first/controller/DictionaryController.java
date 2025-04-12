package com.something.first.controller;

import com.something.first.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/find")
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    private Map<String, String> dictionary = new HashMap<>();

    private final EmailController emailController;
    @Autowired
    EmailDetails details;
    public DictionaryController(EmailController emailController) {
        this.emailController = emailController;
    }

    /**
     * Prints the first 50 entries of the dictionary map.
     */
    public void printFirst50Entries() {
        System.out.println("*********Print50Entries*********");
//        dictionary.entrySet()
//                .stream()
//                .forEach(entry -> logger.info("Word: {}, Meaning: {}", entry.getKey(), entry.getValue()));
    }

    /**
     * Method to load dictionary data from dict.csv file at startup.
     */
    @PostConstruct
    public void initializeDictionary() {
        String filePath = getClass().getClassLoader().getResource("dictionary.csv").getPath(); // Ensure the file is in the resources folder.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3); // Split into word, type, and meaning
                if (parts.length == 3) {
                    String word = parts[0].trim();
                    String meaning = parts[2].trim(); // Use only the meaning
                    dictionary.merge(word.toLowerCase(), meaning, (oldMeaning, newMeaning) -> oldMeaning + ", \n " + newMeaning); // Append meanings if the word already exists
                }
            }
            logger.info("Dictionary initialized successfully from file: {}", filePath);
//            printFirst50Entries();
        } catch (IOException e) {
            logger.error("Failed to initialize dictionary from file.", e);
        }
    }

    /**
     * Endpoint to find the meaning of a given word.
     *
     * @param word The word to search for.
     * @return The meaning of the word or an appropriate message.
     */
    @GetMapping("/{word}")
    public String getMeaning(@PathVariable String word) {
        logger.info("/find/{} endpoint called", word);
        details.setMsgBody(word.toUpperCase()+" Was Searched");
        // Retrieve the meaning from the map
        String meaning = dictionary.get("\""+word.toLowerCase()+"\"");
        logger.info("Search done for :{}", word);
        emailController.sendMail(details);
        logger.info("Mail Sent for :{}", word);
        if (meaning != null) {
            return String.format("<html><body><h2>Meaning of <b>%s</b>: %s</h2></body></html>", word, meaning.replace("\n", "<br>").replace(";", "<br>"));
        } else {
            return String.format("<html><body><h2>Word <b>%s</b> not found in the dictionary.</h2></body></html>", word);
        }
    }
}