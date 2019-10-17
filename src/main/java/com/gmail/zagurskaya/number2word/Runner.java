package main.java.com.gmail.zagurskaya.number2word;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Converter converter = new Converter();
        System.out.println("Введите число, для завершения введите end");
        String numberString;
        Scanner scanner = new Scanner(System.in);

        while (!(numberString = scanner.nextLine()).equals("end")) {
            System.out.println(converter.numberToWords(numberString));
        }
    }
}
