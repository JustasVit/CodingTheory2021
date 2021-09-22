package sample;

import java.util.Vector;

public class Decoder {
    LimitedArrayList upperRegister;
    LimitedArrayList lowerRegister;

    public Decoder() {
        RegisterFiller registerFiller = new RegisterFiller();
        upperRegister = registerFiller.fillListWithValue();
        lowerRegister = registerFiller.fillListWithValue();
    }

    public Vector<Integer> decode(Vector<Integer> inputBits) {
        Vector<Integer> outputBits = new Vector<>();
        int counter = inputBits.size()/2;

        for(int i = 0; i < counter; i++)
        {
            int firstBit = inputBits.get(0);
            inputBits.remove(0);
            int secondBit = inputBits.get(0);
            inputBits.remove(0);

            int upperSecond = (int) upperRegister.get(1);
            int upperFifth = (int) upperRegister.get(4);
            int upperSixth = (int) upperRegister.get(5);

            int sumByte;

            if((firstBit+secondBit+upperSecond+upperFifth+upperSixth)%2 == 0)
            {
                sumByte = 0;
            }
            else sumByte = 1;

            int mdeByte;
            int lowerFirst = (int) lowerRegister.get(0);
            int lowerFourth = (int) lowerRegister.get(3);
            int lowerSixth = (int) lowerRegister.get(5);

            if(sumByte + lowerFirst + lowerFourth + lowerSixth > 2)
            {
                mdeByte = 1;
            }
            else mdeByte = 0;

            if((mdeByte + upperSixth) % 2 == 0)
            {
                outputBits.add(0);
            }
            else  outputBits.add(1);

            upperRegister.add(firstBit);
            lowerRegister.add(sumByte);
        }

        for(int i = 0; i < 6; i++) {
            outputBits.remove(0);
        }
        return outputBits;
    }

}
