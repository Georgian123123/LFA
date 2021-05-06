import java.util.HashMap;
import java.util.ArrayList;

public class MatchInstructions {
    private HashMap<String, Character> instructions;

    MatchInstructions() {
        instructions = new HashMap<>();
        instructions.put("0000", 'n');
        instructions.put("0001", 'i');
        instructions.put("0010", '>');
        instructions.put("0011", '\\');

        instructions.put("0012", '1');
        instructions.put("0100", '<');
        instructions.put("0101", 'd');
        instructions.put("0102", '+');

        instructions.put("0110", '[');
        instructions.put("0111", 'o');
        instructions.put("0112", '*');
        instructions.put("0120", 'e');
        
        instructions.put("0121", '-');
        instructions.put("0122", '!');
        instructions.put("0123", ']');
    }

    public Character getInstr(String str) {
        return instructions.get(str);
    }

    public String getInstructionsFull(ArrayList<String> instr) {
        StringBuilder str = new StringBuilder();
        for (String s : instr) {
            str.append(instructions.get(s));
        }

        return str.toString();
    }
}
