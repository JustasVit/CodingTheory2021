package sample;

import java.util.Random;

public class RandomNumberGenerator {
    public static double returnRandomNumber()
    {
        Random r = new Random();
        return r.nextDouble();
    }
}

