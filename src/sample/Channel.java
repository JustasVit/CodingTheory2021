package sample;

import java.util.Vector;

public class Channel {

    public Channel() {

    }
    private double[] AssignProbabilitiesForEveryBit(Vector<Integer> bits)
    {
        double[] probabilitiesArray = new double[bits.size()];
        for(int i = 0; i < bits.size(); i++)
        {
            probabilitiesArray[i] = RandomNumberGenerator.returnRandomNumber();
        }
        return probabilitiesArray;
    }
    public Vector<Integer> SendBitsThroughChannel(double probability, Vector<Integer> bits)
    {
        double[] probabilitiesArray = AssignProbabilitiesForEveryBit(bits);
        for(int i = 0; i < bits.size(); i++)
        {
            if(probabilitiesArray[i] < probability)
            {
                if(bits.get(i).equals(1)){
                    bits.set(i,0);
                }
                else bits.set(i,1);
            }
        }
        return bits;
    }
}