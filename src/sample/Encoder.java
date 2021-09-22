package sample;

import java.util.Vector;

public class Encoder {

    LimitedArrayList register;
    RegisterFiller registerFiller;

    public Encoder()
    {
       registerFiller = new RegisterFiller();
       register = registerFiller.fillListWithValue();
    }

    public Vector<Integer> Encode(Vector<Integer> bits)
    {
        Vector<Integer> outputBits = new Vector<>();
        int initialBitsCount = bits.size();

        for(int i = 0; i < initialBitsCount; i++) {
            int bit = bits.get(0);
            bits.remove(0);
            outputBits.add(bit);
            int second = (int) register.get(1);
            int fifth = (int) register.get(4);
            int sixth = (int) register.get(5);

            if ((bit + second + fifth + sixth) % 2 == 0) {
                outputBits.add(0);
            } else outputBits.add(1);

            register.add(bit);
        }

        for(int i = 0; i < 6; i++)
        {
            outputBits.add(0);
            int second = (int) register.get(1);
            int fifth = (int) register.get(4);
            int sixth = (int) register.get(5);

            if ((second + fifth + sixth) % 2 == 0) {
                outputBits.add(0);
            } else outputBits.add(1);

            register.add(0);
        }

        return outputBits;
    }
}
