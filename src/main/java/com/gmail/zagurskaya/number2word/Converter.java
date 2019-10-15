package main.java.com.gmail.zagurskaya.number2word;

public class Converter {

    private static final String numberToWordFrom1To9[][] = {{"одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"один", "два"}};

    private static final String numbersToWordFrom10To19[] = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private static final String dozensToWordFrom20To90[] = {"двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String hundredsToWordFrom100To900[] = {"сто", "двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private static final String levelWord[][] = {
            {"", "", ""},
            {"тысяча", "тысячи", "тысяч"},
            {"миллион", "миллиона", "миллионов"},
            {"миллиард", "миллиарда", "миллиардов"},
            {"триллион", "триллиона", "триллионов"}};

    // рекурсивная функция преобразования целого числа в прописное число
    public static String numberToWords(double number) {
        if (number < 0.0) {
            return "Внимание! отрицательное значение";
        }
        if (number == 0) {
            return "ноль";
        }
        if (number > 1000000000000000l) {
            return "Внимание! Большой формат числа";
        }
        long num = (long) Math.floor(number);
        return segmentToWords(num, 0);
    }

    private static String segmentToWords(long number, int level) {
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

        long nextNumber = number / 1000;
        if (nextNumber > 0) {
            return (segmentToWords(nextNumber, level + 1) + " " + numberWords.toString()).trim();
        } else {
            return numberWords.toString().trim();
        }
    }

    private static void addHundredsToNumberWords(int hundredsAnalysisSegment, StringBuilder numberWords) {
        if (hundredsAnalysisSegment != 0) {
            numberWords.append(hundredsToWordFrom100To900[(hundredsAnalysisSegment - 1)]).append(" ");
        }
    }

    private static int addDozensToNumberWords(int dozensAnalysisSegment, int numberAnalysisSegment, StringBuilder numberWords) {
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

    private static void addNumberToNumberWords(int numberAnalysisSegment, StringBuilder numberWords) {
        if (numberAnalysisSegment != 0) {
            if (numberAnalysisSegment == 1 || numberAnalysisSegment == 2) {
                numberWords.append(numberToWordFrom1To9[1][(numberAnalysisSegment - 1)]).append(" ");
            } else {
                numberWords.append(numberToWordFrom1To9[0][(numberAnalysisSegment - 1)]).append(" ");
            }
        }
    }

    private static void addLevelToNumberWords(int numberAnalysisSegment, double analysisSegment, int level, StringBuilder numberWords) {
        switch (numberAnalysisSegment) {
            case 1:
                numberWords.append(levelWord[level][0]);
                break;
            case 2:
            case 3:
            case 4:
                numberWords.append(levelWord[level][1]);
                break;
            default:
                if ((analysisSegment != 0) || ((analysisSegment == 0) && (level == 1)))
                    numberWords.append(levelWord[level][2]);
        }
    }
}
