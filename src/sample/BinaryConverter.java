package sample;

import java.util.Vector;

public class BinaryConverter {

    public BinaryConverter() {
    }

    public Vector<Integer> ConvertStringToBits(String input)
    {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();

        String temporary;
        for(int i = 0; i < chars.length; i++)
        {
            temporary = Integer.toBinaryString(chars[i]);
            while (temporary.length() < 8)
            {
                temporary = "0" + temporary;
            }
            stringBuilder.append(temporary);
        }

        String binaryString = stringBuilder.toString();
        BinaryParser binaryParser = new BinaryParser();

        return binaryParser.parseString(binaryString);
    }

    public String ConvertBitsToString(Vector<Integer> input)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i< input.size(); i++)
        {
            stringBuilder.append(input.get(i));
        }

        String string = stringBuilder.toString();

        String output = "";

        for(int i = 0; i < string.length(); i+=8)
        {
            String subString = string.substring(i,i+8);
            int number = Integer.parseInt(subString,2);
            char letter = (char) number;
            output+= letter;
        }
        return output;
    }
}
