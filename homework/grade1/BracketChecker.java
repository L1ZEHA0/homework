package homework.grade1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BracketChecker {
    
    public static void checkBrackets() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("请输入括号字符串：");
        String input = scanner.nextLine();
        
        if (isBalanced(input)) {
            System.out.println("括号平衡且配对！");
        } else {
            System.out.println("括号不平衡或不配对！");
        }
        
        scanner.close();
    }
    
    private static boolean isBalanced(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char ch : str.toCharArray()) {
            if (isLeftBracket(ch)) {
                stack.push(ch);
            } else if (isRightBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if (!matches(top, ch)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{' || ch == '<';
    }
    
    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}' || ch == '>';
    }
    
    private static boolean matches(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}') ||
               (left == '<' && right == '>');
    }
    
    public static void main(String[] args) {
        checkBrackets();
    }
}
