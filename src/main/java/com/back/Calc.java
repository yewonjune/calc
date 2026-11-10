package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        // 덧셈·뺄셈: 마지막 " + " 또는 " - " 기준으로 나눈다 (왼쪽 결합)
        int plusIndex = exp.lastIndexOf(" + ");
        int minusIndex = exp.lastIndexOf(" - ");
        int index = Math.max(plusIndex, minusIndex);

        if (index != -1) {
            int left = run(exp.substring(0, index));
            int right = run(exp.substring(index + 3));

            return index == plusIndex ? left + right : left - right;
        }

        // 곱셈: 마지막 " * " 기준으로 나눈다
        int multiplyIndex = exp.lastIndexOf(" * ");

        if (multiplyIndex != -1) {
            return run(exp.substring(0, multiplyIndex)) * run(exp.substring(multiplyIndex + 3));
        }

        // 숫자 하나
        return Integer.parseInt(exp);
    }
}
