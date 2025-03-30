package asmetics;

import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("-         CALCULATOR 2         -");
        System.out.println("--------------------------------");
        System.out.println("Input: +, -, /, *, %, =, int, float");
        System.out.println("Input = to calculate");
        calculate();
        while (true) {
            System.out.println("Would you like to calculate again? [y/n]");
            Scanner continueScanner = new Scanner(System.in);
            String input = continueScanner.next();
            if (input.equals("y")) {
                calculate();
            } else {
                break;
            }
        }
    }

    public static ArrayList<String> gatherInput() {
        ArrayList<String> dataSet = new ArrayList<>();
        String[] allowedOperators = {"+", "-", "*", "/", "%"};
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a number or an operator (+, -, /, *, %). Enter \"=\" to calculate:");
            String inputString = inputScanner.next();

            if (inputString.equals("=")) {
                System.out.println("Calculating...");
                break;
            }

            if (isNumeric(inputString) || Arrays.asList(allowedOperators).contains(inputString)) {
                dataSet.add(inputString);
            } else {
                System.out.println("Invalid input: " + inputString + ". Please enter a number or a supported operator.");
            }
        }

        return dataSet;
    }

    public static void calculate() {
        ArrayList<String> dataSet = gatherInput();

        if (dataSet.isEmpty() || !isNumeric(dataSet.getFirst())) {
            System.out.println("Invalid input. The calculation must start with a number.");
            return;
        }

        double currentNumber = Double.parseDouble(dataSet.getFirst());

        for (int i = 1; i < dataSet.size(); i += 2) {
            if (i + 1 >= dataSet.size() || !isNumeric(dataSet.get(i + 1))) {
                System.out.println("Invalid expression. Missing a number after an operator.");
                return;
            }

            String operator = dataSet.get(i);
            double nextNumber = Double.parseDouble(dataSet.get(i + 1));

            switch (operator) {
                case "+" -> currentNumber += nextNumber;
                case "-" -> currentNumber -= nextNumber;
                case "*" -> currentNumber *= nextNumber;
                case "/" -> {
                    if (nextNumber == 0) {
                        System.out.println("Error: Division by zero.");
                        return;
                    }
                    currentNumber /= nextNumber;
                }
                case "%" -> {
                    if (nextNumber == 0) {
                        System.out.println("Error: Modulo by zero.");
                        return;
                    }
                    currentNumber %= nextNumber;
                }
                default -> {
                    System.out.println("Invalid operator: " + operator);
                    return;
                }
            }
        }

        System.out.println("Result: " + currentNumber);
    }

    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
