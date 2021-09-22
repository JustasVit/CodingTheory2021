package sample;

public class RegisterFiller {

    public RegisterFiller() {
    }

    public LimitedArrayList fillListWithValue()
    {
        LimitedArrayList limitedArrayList = new LimitedArrayList();
        for(int i = 0; i < 6; i++)
        {
            limitedArrayList.add(0);
        }

        return limitedArrayList;
    }
}
