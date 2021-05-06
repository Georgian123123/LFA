import java.util.ArrayList;
import java.util.HashMap;

public class DecodeInput {
    private StringBuilder inputToDecode;
    private HashMap<Character, Integer> codes;

    DecodeInput(StringBuilder inputToDecode) {
        this.inputToDecode = inputToDecode;
    }

    protected void putInHashValues(String inp) {
        codes = new HashMap<Character, Integer>();
        int genCode = 0;
        for (int i = 0; i < inp.length(); ++i) {
            if(!codes.containsKey(inp.charAt(i))) {
                codes.put(inp.charAt(i), genCode);
                genCode += 1;
            }
        }
    }

    public ArrayList<String> getInstructions() {
        String inp = inputToDecode.toString();
        
        ArrayList<String> getInst = new ArrayList<>();
        for (int i = 0; i < inp.length(); i += 4) {
            putInHashValues(inp.substring(i, i + 4));

            StringBuilder result = new StringBuilder();
            result.append(codes.get(inp.charAt(i)));
            result.append(codes.get(inp.charAt(i + 1)));
            result.append(codes.get(inp.charAt(i + 2)));
            result.append(codes.get(inp.charAt(i + 3)));

            getInst.add(result.toString());
        }
        return getInst;
    }
}
