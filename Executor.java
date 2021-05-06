import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Executor {
    private String input;
    private Stack<BigInteger> stack;
    private Stack<Integer> Lbrace = new Stack<>();
    private Stack<Integer> LbraceIgnore = new Stack<>();
    private boolean ignoreOperation = false;
    private Scanner myScanner = new Scanner(System.in);
    int counter = 0;
    Executor(String input) {
        this.input = input;
        this.stack = new Stack<>();
    }

    public void run() {
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == 'i' && !ignoreOperation) {
                if (myScanner.hasNextBigInteger()) {
                    BigInteger number = myScanner.nextBigInteger();
                    stack.push(number);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
                //System.out.println(stack);
                
            } else if (input.charAt(i) == '>' && !ignoreOperation) {
                if (!stack.empty()) {
                    BigInteger number = stack.pop();
                    stack.add(0, number);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                    
                }
            } else if (input.charAt(i) == '\\' && !ignoreOperation) {
                if (stack.size() >= 2) {
                    BigInteger numberOne = stack.pop();
                    BigInteger numberTwo = stack.pop();
                    
                    stack.push(numberOne);
                    stack.push(numberTwo);
                    //System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
            } else if (input.charAt(i) == '1' && !ignoreOperation) {
                BigInteger number = new BigInteger("1");
                stack.push(number);
                //System.out.println(stack);
            } else if (input.charAt(i) == '<' && !ignoreOperation) {
                if (!stack.isEmpty()) {
                    BigInteger number = stack.remove(0);
                    stack.push(number);
                   // System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }       
            } else if (input.charAt(i) == 'd' && !ignoreOperation) {
                if (!stack.isEmpty()) {
                    BigInteger number = stack.pop();
                    stack.push(number);
                    stack.push(number);
                    //System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                } 
            } else if (input.charAt(i) == '+' && !ignoreOperation) {
                if (stack.size() >= 2) {
                    BigInteger numberOne = stack.pop();
                    BigInteger numberTwo = stack.pop();

                    stack.push(numberOne.add(numberTwo));
                   // System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
            } else if (input.charAt(i) == 'o' && !ignoreOperation) {
                if (!stack.isEmpty()) {
                    BigInteger number = stack.pop();
                    System.out.println(number);
                  //  System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
            } else if (input.charAt(i) == '*' && !ignoreOperation) {
                if (stack.size() >= 2) {
                    BigInteger numberOne = stack.pop();
                    BigInteger numberTwo = stack.pop();

                    stack.push(numberOne.multiply(numberTwo));
                    //System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
            } else if (input.charAt(i) == '-' && !ignoreOperation) {
                if (!stack.isEmpty()) {
                    BigInteger number = stack.pop();
                    BigInteger b = new BigInteger("-1");

                    stack.push(number.multiply(b));
                   // System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                } 
            } else if (input.charAt(i) == '!' && !ignoreOperation) {
                if (!stack.isEmpty()) {
                    BigInteger number = stack.pop();
                   // System.out.println(stack);
                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                } 
            } else if (input.charAt(i) == 'e' && !ignoreOperation) {
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
                    ExecutorHelper exec = new ExecutorHelper(stack);
                    if (!exec.runHelper(instruction)) {
                        System.err.println("Exception:" + i);
                        System.exit(-2);
                    }
                    stack = exec.stack;

                } else {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
            } else if (input.charAt(i) == '[') {
                if (stack.isEmpty()) {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
                Lbrace.push(i);
                if (stack.peek().toString().equals("0")) {
                    ignoreOperation = true;
                    LbraceIgnore.push(1);
                }
            } else if (input.charAt(i) == ']') {
                if (Lbrace.size() == 0) {
                    System.err.println("Exception:" + i);
                    System.exit(-2);
                }
                int pos = Lbrace.pop();
                if (LbraceIgnore.size() > 0) {
                    LbraceIgnore.pop();
                    if (LbraceIgnore.size() == 0) {
                        ignoreOperation = false;
                    }
                } else {
                    i = pos - 1;
                }
            }
        }
    }
}
