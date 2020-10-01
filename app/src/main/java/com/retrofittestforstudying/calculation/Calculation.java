package com.retrofittestforstudying.calculation;

public abstract class Calculation {
    private static String temp;
    private static boolean isDivOnNull;

    public static String Calculate(String expression) {
        temp = expression;
        Action(temp, '/');
        Action(temp, '*');
        Action(temp, '+');
        Action(temp, '-');
        return temp;
    }

    private static void Action(String expression, char sign) {
        System.out.println("---> " + expression);
        int index = expression.indexOf(sign, 1);
        if (index < 0)
            return;
        double first = Double.parseDouble(getNumberBeforeSign(expression, index));
        double second = Double.parseDouble(getNumberAfterSign(expression, index));
        double result = CalculationAction(first, second, sign);
        temp = "";
        for (int i = 0; i < index - getNumberBeforeSign(expression, index).length(); i++) {
            temp += expression.charAt(i);
        }
        if (temp.length() > 0 && Character.isDigit(temp.charAt(temp.length() - 1)) && result >= 0)
            temp += '+';
        temp += result;
        for (int i = index + getNumberAfterSign(expression, index).length() + 1; i < expression.length(); i++) {
            temp += expression.charAt(i);
        }
        Action(temp, sign);
    }

    private static double CalculationAction(double first, double second, char sign) {
        switch (sign) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
        }
        return 0.0;
    }

    private static String getNumberAfterSign(String expression, int indexOfSign) {
        StringBuilder number = new StringBuilder();
        int index = indexOfSign + 1;
        if (index >= expression.length())
            return number.toString();
        while (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.') {

            number.append(expression.charAt(index));
            index++;
            if (index >= expression.length())
                break;
        }
        if (number.toString().length() == 0)
            number.append("0");
        return number.toString();
    }

    private static String getNumberBeforeSign(String expression, int indexOfSign) {
        StringBuilder number = new StringBuilder();
        int index = indexOfSign - 1;
        if (index < 0)
            return number.toString();

        while (Character.isDigit(expression.charAt(index)) ||
                expression.charAt(index) == '.' ||
                expression.charAt(index) == '-') {
            number.insert(0, expression.charAt(index));
            if (index - 1 < 0 || expression.charAt(index) == '-')
                break;
            index--;
        }


        return number.toString();
    }

    public static boolean IsDivOnNullResult(String expression) {
        IsDivOnNull(expression, 0);
        return isDivOnNull;
    }

    private static void IsDivOnNull(String expression, int indexStart) {
        isDivOnNull = false;
        int index = expression.indexOf('/', indexStart);
        if (index < 0) {
            return;
        }
        double number = Double.parseDouble(getNumberAfterSign(expression, index));
        if (number == 0) {
            isDivOnNull = true;
            return;
        }
        IsDivOnNull(expression, index + 1);
    }

    public static boolean IsPointExist(String expression) {
        int index = expression.length() - 1;
        while (index >= 0) {
            if (expression.charAt(index) == '.') {
                return true;
            }
            if (!Character.isDigit(expression.charAt(index))) {
                return false;
            }
            index--;
        }
        return false;
    }
}
