package main.java.com.gmail.zagurskaya.number2word;

public class Validation {

    public static final String NUMBER_VALIDATE_PATTERN = "[0-9]{1,12}";

    public static boolean isValidNumber(String number) {

        return number.matches(NUMBER_VALIDATE_PATTERN);
    }
}
