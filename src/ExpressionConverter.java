import java.util.Stack;

public class ExpressionConverter {

    // 1. Method to define operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // 2. Method for Infix to Postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    result.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            result.append(stack.pop());
        return result.toString();
    }

    // 3. Method for Infix to Prefix
    static String infixToPrefix(String exp) {
        StringBuilder revInfix = new StringBuilder(exp).reverse();
        for (int i = 0; i < revInfix.length(); i++) {
            if (revInfix.charAt(i) == '(')
                revInfix.setCharAt(i, ')');
            else if (revInfix.charAt(i) == ')')
                revInfix.setCharAt(i, '(');
        }
        String postfix = infixToPostfix(revInfix.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    // 4. Main method to run the program
    public static void main(String[] args) {
        String exp = "A+B*(C^D-E)";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + infixToPostfix(exp));
        System.out.println("Prefix Expression: " + infixToPrefix(exp));
    }
} 
