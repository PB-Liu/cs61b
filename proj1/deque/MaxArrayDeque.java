package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }

        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = comparator.compare(items[i], items[maxIndex]);
            if (cmp > 0)
                maxIndex = i;
        }
        return (T) items[maxIndex];

    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = c.compare(items[i], items[maxIndex]);
            if (cmp > 0)
                maxIndex = i;
        }
        return (T) items[maxIndex];

    }
}
