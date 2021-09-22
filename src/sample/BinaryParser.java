package sample;

import java.util.Vector;

public class BinaryParser {

    public BinaryParser() {
    }

    public Vector<Integer> parseString(String binaryString)
    {
        Vector<Integer> outputVector = new Vector<>();
        char[] bits = binaryString.toCharArray();
        for(int i = 0; i < bits.length; i++)
        {
            if(bits[i] == '0')
            {
                outputVector.add(0);
            }
            else outputVector.add(1);
        }
        return outputVector;
    }

    public String parseVector(Vector<Integer> binaryVector)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < binaryVector.size(); i++)
        {
            if(binaryVector.get(i).equals(0))
            {
                stringBuilder.append("0");
            }
            else if(binaryVector.get(i).equals(1))
            {
                stringBuilder.append("1");
            }
        }
        return stringBuilder.toString();
    }

}
