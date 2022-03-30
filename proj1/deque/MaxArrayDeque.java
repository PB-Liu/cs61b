package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    ArrayDeque<T> items;

    public MaxArrayDeque(Comparator<T> c){
        super();
        items = new ArrayDeque<T>();
        comparator = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }

        int maxIndex = 0;
        for (int i = 0; i < items.size(); i++) {
            int cmp = comparator.compare(items.get(i), items.get(maxIndex));
            if (cmp > 0)
                maxIndex = i;
        }
        return items.get(maxIndex);

    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < items.size(); i++) {
            int cmp = c.compare(items.get(i), items.get(maxIndex));
            if (cmp > 0)
                maxIndex = i;
        }
        return items.get(maxIndex);

    }
}
