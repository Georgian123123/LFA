import java.util.Stack;

public class SintacticErrors {
    private String input;

    SintacticErrors(String input) {
        this.input = input;
    }

    public int lengthError() {
        if (input.length() % 4 != 0) {
            System.err.println("Error:" + input.length() / 4);
            return -1;
        }
        return 0;
    }

    public int sintErrors(String str) {
        Stack<Pair<Character, Integer>> paranthesis = new Stack<>();

        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '[') {
                Pair<Character, Integer> p = new Pair<Character, Integer>('[', i);
                paranthesis.push(p);
            } else if (str.charAt(i) == ']' && !paranthesis.isEmpty()) {
                paranthesis.pop();
            } else if (str.charAt(i) == ']' && paranthesis.isEmpty()) {
                Pair<Character, Integer> p = new Pair<Character, Integer>(']', i);
                System.err.println("Error:" + p.getSecond());
                return -1;
            }
        }

        if (!paranthesis.isEmpty()) {
            Pair<Character, Integer> p = paranthesis.pop();
            System.err.println("Error:" + str.length());
            return -1;
        } 
        return 0;
    }
}
