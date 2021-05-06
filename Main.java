import java.io.File;
import java.util.ArrayList;

class Main{
    private static InputParser inputParser;
    public static DecodeInput decodeInput;
    public static String instructions;
    private static Executor program;
    public static MatchInstructions matchInstructions = new MatchInstructions(); 
    public static void main(String[] args) {
        // iau fisierul de input si obtin stringul
        inputParser = new InputParser(new File(args[0]));
        
        // verific erorile de sintaxa
        SintacticErrors handle = new SintacticErrors(inputParser.readInput().toString());
        if (handle.lengthError() == -1) {
            System.exit(-1);
        }

        // Decodez inputul
        decodeInput = new DecodeInput(inputParser.readInput());

        // Obtin sirul de instructiuni
        instructions = matchInstructions.getInstructionsFull(decodeInput.getInstructions());
        // verific erori de sintaxa
        if (handle.sintErrors(instructions) == -1) {
            System.exit(-1);
        }
        program = new Executor(instructions);
        program.run();
        return;
    }
}