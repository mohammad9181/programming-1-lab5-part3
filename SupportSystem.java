import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class implements a technical support system. It is the top level class 
 * in this project. The support system communicates via text input/output 
 * in the text terminal.
 * 
 * This class uses an object of class InputReader to read input from the user,
 * and an object of class Responder to generate responses. It contains a loop
 * that repeatedly reads input and generates output until the users wants to 
 * leave.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.4
 */
public class SupportSystem
{
    private InputReader reader;
    private Responder responder;
    private WordCounter counter;
    

    
    /**
     * Creates a technical support system.
     */
    public SupportSystem()
    {
        reader = new InputReader();
        responder = new Responder();
        counter = new WordCounter();
    }

    /**
     * Start the technical support system. This will print a welcome message and enter
     * into a dialog with the user, until the user ends the dialog.
     */
    public void start()
    {
        boolean finished = false;

        printWelcome();
        
        while (!finished) 
        {
            HashSet<String> words = reader.getInput();

            if (words.contains("bye")) {
                finished = true;
            } else {
                counter.addWords(words);
                String response = responder.generateResponse(words);
                System.out.println(response);
            }
        }
        
        printGoodbye();
        
        HashMap<String, Integer> wordCounts = counter.getWordCounts();
        HashMap<String, String> responseMap = responder.getResponseMap();

        for (String word : wordCounts.keySet()) 
        {
            if (!responseMap.containsKey(word)) 
            {
                System.out.println(word + ": " + wordCounts.get(word));
            }
        }

    }


    /**
     * Print a welcome message to the screen.
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the DodgySoft Technical Support System.");
        System.out.println();
        System.out.println("Please tell us about your problem.");
        System.out.println("We will assist you with any problem you might have.");
        System.out.println("Please type 'bye' to exit our system.");
    }

    /**
     * Print a good-bye message to the screen.
     */
    private void printGoodbye()
    {
        System.out.println("Nice talking to you. Bye...");
        counter.printWordCounts();
    }
    
    
}
