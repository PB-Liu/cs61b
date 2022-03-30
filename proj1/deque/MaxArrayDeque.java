package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max() {
        if (super.size() == 0) {
            return null;
        }

        int maxIndex = 0;
        for (int i = 0; i < super.size(); i++) {
            int cmp = comparator.compare(super.get(i), super.get(maxIndex));
            if (cmp > 0)
                maxIndex = i;
        }
        return super.get(maxIndex);

    }

    public T max(Comparator<T> c) {
        if (super.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < super.size(); i++) {
            int cmp = c.compare(super.get(i), super.get(maxIndex));
            if (cmp > 0)
                maxIndex = i;
        }
        return super.get(maxIndex);

    }
}
