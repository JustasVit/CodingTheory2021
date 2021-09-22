package sample;

import java.util.Vector;

public class ErrorChecker {
    Vector<Integer> correctBytes;
    Vector<Integer> possiblyIncorrectBytes;

    public ErrorChecker(Vector<Integer> correctBytes, Vector<Integer> possiblyIncorrectBytes) {
        this.correctBytes = correctBytes;
        this.possiblyIncorrectBytes = possiblyIncorrectBytes;
    }

    public String checkForErrors()
    {
        int errorCounter = 0;
        Vector<Integer> errorPositions = new Vector<>();
        for(int i = 0; i < correctBytes.size(); i++)
        {
            if(correctBytes.get(i) != possiblyIncorrectBytes.get(i))
            {
                errorCounter++;
                errorPositions.add(i+1);
            }
        }
        String output = "Iš viso klaidų: " + errorCounter + ". Klaidos įvyko šiose pozicijose: " + errorPositions.toString()  + ".";
        return output;
    }
}
