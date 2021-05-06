import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class ExecutorHelper {
    public Stack<BigInteger> stack;
    private Scanner myScanner = new Scanner(System.in);

    ExecutorHelper(Stack<BigInteger> stack) {
        this.stack = stack;
    }

    public boolean runHelper(final String para) {
        Character param = para.charAt(0);
        if (param == 'i') {
            if (myScanner.hasNextBigInteger()) {
                BigInteger number = myScanner.nextBigInteger();
                stack.push(number);
            } else {
                return false;
            }
            
        } else if (param == '>') {
            if (!stack.empty()) {
                BigInteger number = stack.pop();
                stack.add(0, number);
            } else {
                return false;
            }
        } else if (param == '\\') {
            if (stack.size() >= 2) {
                BigInteger numberOne = stack.pop();
                BigInteger numberTwo = stack.pop();
                
                stack.push(numberOne);
                stack.push(numberTwo);
            } else {
                return false;
            }
        } else if (param == '1') {
            BigInteger number = new BigInteger("1");
            stack.push(number);
        } else if (param == '<') {
            if (!stack.isEmpty()) {
                BigInteger number = stack.get(0);
                stack.remove(0);
                stack.push(number);
            } else {
                return false;
            }  
        } else if (param == 'd') {
            if (!stack.isEmpty()) {
                BigInteger number = stack.pop();
                stack.push(number);
                stack.push(number);
            } else {
                return false;
            }
        } else if (param == '+') {
            if (stack.size() >= 2) {
                BigInteger numberOne = stack.pop();
                BigInteger numberTwo = stack.pop();

                stack.push(numberOne.add(numberTwo));
            } else {
                return false;
            }
        } else if (param == 'o') {
            if (!stack.isEmpty()) {
                BigInteger number = stack.pop();
                System.out.println(number);
            } else {
                return false;
            }
        } else if (param == '*') {
            if (stack.size() >= 2) {
                BigInteger numberOne = stack.pop();
                BigInteger numberTwo = stack.pop();

                stack.push(numberOne.multiply(numberTwo));
            } else {
                return false;
            }
        } else if (param == '-') {
            if (!stack.isEmpty()) {
                BigInteger number = stack.pop();
                BigInteger b = new BigInteger("-1");

                stack.push(number.multiply(b));
            } else {
                return false;
            }
        } else if (param == '!') {
            if (!stack.isEmpty()) {
                BigInteger number = stack.pop();
            } else {
                return false;
            }
        } else if (param == 'e') {
            if (stack.size() >= 4) {
                BigInteger n1 = stack.pop();
                BigInteger n2 = stack.pop();
                BigInteger n3 = stack.pop();
                BigInteger n4 = stack.pop();

                StringBuilder str = new StringBuilder();
                HashMap<String, String> toMap = new HashMap<String, String>();

                toMap.put(n1.toString(), "0");
                toMap.put(n2.toString(), "1");
                toMap.put(n3.toString(), "2");
                toMap.put(n4.toString(), "3");
                str.append(toMap.get(n1.toString()));
                str.append(toMap.get(n2.toString()));
                str.append(toMap.get(n3.toString()));
                str.append(toMap.get(n4.toString()));
               
                MatchInstructions matchInstructions = new MatchInstructions(); 
                DecodeInput decodeInput = new DecodeInput(str);
                String instruction = matchInstructions.getInstructionsFull(decodeInput.getInstructions());
                
                if (this.runHelper(instruction) == false) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (param == '[') {
            return false;
        } else if (param == ']') {
            return false;
        }
        return true;
    }
}
