package au.com.rumesh.fxCalculator.ui;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Interface to handle CLI. Implement this to handle different CLI logic
 *
 * @author rnadeera
 */
@FunctionalInterface
public interface Console {

    Logger logger = Logger.getLogger(Console.class);

    String EXIT_OPTION = "EXIT";

    void start();

    default void withScanner(Consumer<Scanner> consumer){
        Scanner scanner = new Scanner(System.in);
        try {
            consumer.accept(scanner);
        } finally {
            scanner.close();
        }
    }

    default String trimUserInput(Scanner scanner){
        return trim(scanner.nextLine());
    }

    default String trim(String value){
        return StringUtils.trimToEmpty(value);
    }

    default String handleException(Exception e) {
        String message;
        if(e instanceof NumberFormatException){
            message = "Invalid input for numeric value";
        } else if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
        } else {
            logger.error("System Error", e);
            message = "Application malfunctioned.  Please contact Administrator.";
        }
        return message;
    }
}
