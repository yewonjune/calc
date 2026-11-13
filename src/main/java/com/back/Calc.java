package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        // 1순위로 나눌 곳: 괄호 밖의 마지막 " + " 또는 " - " (왼쪽 결합)
        int index = findLastTopLevel(exp, "+-");

        if (index != -1) {
            int left = run(exp.substring(0, index));
            int right = run(exp.substring(index + 3));

            return exp.charAt(index + 1) == '+' ? left + right : left - right;
        }

        // 2순위: 괄호 밖의 마지막 " * "
        index = findLastTopLevel(exp, "*");

        if (index != -1) {
            return run(exp.substring(0, index)) * run(exp.substring(index + 3));
        }

        // 괄호 앞 음수 부호: -(...)
        if (exp.startsWith("-(")) {
            return -run(exp.substring(1));
        }

        // 전체를 감싼 괄호: (...)
        if (exp.startsWith("(") && exp.endsWith(")")) {
            return run(exp.substring(1, exp.length() - 1));
        }

        // 숫자 하나
        return Integer.parseInt(exp);
    }

    // 괄호 깊이가 0인 위치에서 " op " 형태의 연산자를 뒤에서부터 찾는다
    private static int findLastTopLevel(String exp, String operators) {
        int depth = 0;

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (c == ')') depth++;
            else if (c == '(') depth--;
            else if (depth == 0 && operators.indexOf(c) != -1
                    && i > 0 && i < exp.length() - 1
                    && exp.charAt(i - 1) == ' ' && exp.charAt(i + 1) == ' ') {
                return i - 1;
            }
        }

        return -1;
    }
}
