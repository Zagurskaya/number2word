package main.java.com.gmail.zagurskaya.number2word;

import java.util.List;

public class Converter {

    private String[][] numbersToWordFrom1To9 = new String[2][9];
    private String[] numbersToWordFrom10To19 = new String[10];
    private String[] dozensToWordFrom20To90 = new String[8];
    private String[] hundredsToWordFrom100To900 = new String[9];
    private String[][] levelsWord = new String[5][3];

    public Converter() {
        initializeArrayConstant();
    }

    private void initializeArrayConstant() {
        Reader reader = new Reader();
        List<String> numberToWordFrom1To9List = reader.readNumbersFromFileToList("/numbersToWordFrom1To9.txt");
        ListToArray(numberToWordFrom1To9List, numbersToWordFrom1To9);

        List<String> numberToWordFrom10To19List = reader.readNumbersFromFileToList("/numbersToWordFrom10To19.txt");
        ListToArray(numberToWordFrom10To19List, numbersToWordFrom10To19);

        List<String> dozensToWordFrom20To90List = reader.readNumbersFromFileToList("/dozensToWordFrom20To90.txt");
        ListToArray(dozensToWordFrom20To90List, dozensToWordFrom20To90);

        List<String> hundredsToWordFrom100To900List = reader.readNumbersFromFileToList("/hundredsToWordFrom100To900.txt");
        ListToArray(hundredsToWordFrom100To900List, hundredsToWordFrom100To900);

        List<String> levelWordList = reader.readNumbersFromFileToList("/levelsWord.txt");
        ListToArray(levelWordList, levelsWord);
    }

    public String numberToWords(String numberString) {
        if (Validation.isValidNumber(numberString)) {
            long number = Long.parseLong(numberString);
            return number == 0 ? "ноль" : segmentToWords(number, 0);
        } else {
            System.out.println("Некорректное значение в поле " + numberString);
            return "";
        }
    }

    private String segmentToWords(Long number, int level) {
        StringBuilder numberWords = new StringBuilder(100);
        int analysisSegment = (int) (number % 1000);//текущий трехзначный сегмент
        int hundredsAnalysisSegment = analysisSegment / 100;//цифра сотен
        int partNumber = analysisSegment % 100;
        int dozensAnalysisSegment = partNumber / 10;//цифра десятков
        int numberAnalysisSegment = partNumber % 10;//цифра единиц

        addHundredsToNumberWords(hundredsAnalysisSegment, numberWords);
        numberAnalysisSegment = addDozensToNumberWords(dozensAnalysisSegment, numberAnalysisSegment, numberWords);
        addNumberToNumberWords(numberAnalysisSegment, numberWords);
        addLevelToNumberWords(numberAnalysisSegment, analysisSegment, level, numberWords);

        Long nextNumber = number / 1000;
        return nextNumber > 0
                ? segmentToWords(nextNumber, level + 1) + " " + numberWords.toString().trim()
                : numberWords.toString().trim();
    }

    private void addHundredsToNumberWords(int hundredsAnalysisSegment, StringBuilder numberWords) {
        if (hundredsAnalysisSegment != 0) {
            numberWords.append(hundredsToWordFrom100To900[(hundredsAnalysisSegment - 1)]).append(" ");
        }
    }

    private int addDozensToNumberWords(int dozensAnalysisSegment, int numberAnalysisSegment, StringBuilder numberWords) {
        if (dozensAnalysisSegment != 0) {
            if (dozensAnalysisSegment == 1) {
                numberWords.append(numbersToWordFrom10To19[numberAnalysisSegment]).append(" ");
                numberAnalysisSegment = 0;//при двузначном остатке от 10 до 19, цифра едициц не должна учитываться
                return numberAnalysisSegment;
            } else {
                numberWords.append(dozensToWordFrom20To90[(dozensAnalysisSegment - 2)]).append(" ");
            }
        }
        return numberAnalysisSegment;
    }

    private void addNumberToNumberWords(int numberAnalysisSegment, StringBuilder numberWords) {
        if (numberAnalysisSegment != 0) {
            if (numberAnalysisSegment == 1 || numberAnalysisSegment == 2) {
                numberWords.append(numbersToWordFrom1To9[1][(numberAnalysisSegment - 1)]).append(" ");
            } else {
                numberWords.append(numbersToWordFrom1To9[0][(numberAnalysisSegment - 1)]).append(" ");
            }
        }
    }

    private void addLevelToNumberWords(int numberAnalysisSegment, double analysisSegment, int level, StringBuilder numberWords) {
        switch (numberAnalysisSegment) {
            case 1:
                numberWords.append(levelsWord[level][0]);
                break;
            case 2:
            case 3:
            case 4:
                numberWords.append(levelsWord[level][1]);
                break;
            default:
                if (!(analysisSegment == 0 && level > 1))
                    numberWords.append(levelsWord[level][2]);
        }
    }

    public void ListToArray(List<String> list, String Array[][]) {
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array[i].length; j++) {
                if (i * Array[0].length + j <= list.size() - 1) {
                    Array[i][j] = list.get(i * Array[0].length + j);
                }
            }
        }
    }

    public void ListToArray(List<String> list, String Array[]) {
        for (int i = 0; i < Array.length; i++) {
            Array[i] = list.get(i);
        }
    }
}
