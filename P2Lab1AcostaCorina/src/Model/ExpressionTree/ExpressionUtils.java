package Model.ExpressionTree;

public class ExpressionUtils {
    
    // Verifica si un token es un operador
    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/") || 
               token.equals("^");
    }
    
    // Retorna la precedencia de un operador
    public static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
    
    // Verifica si un carácter es un operador
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}