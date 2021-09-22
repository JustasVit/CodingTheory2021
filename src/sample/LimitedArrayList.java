package sample;

import java.util.ArrayList;

public class LimitedArrayList extends ArrayList<Object> {
    @Override
    public boolean add(Object o) {

        if (this.size() < 6) {
            super.add(0,o);
            return true;
        }
        if (this.size() == 6)
        {
            super.add(0,o);
            super.remove(6);
        }
        return false;
    }

    @Override
    public void add(int index, Object element) {

        if (this.size() < 6 && index <= 5) {
            super.add(index, element);
        }
    }
}