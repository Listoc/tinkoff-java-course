package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private int cursor;
    private final T[] array;

    public BackwardIterator(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Null collection");
        }

        this.cursor = collection.size() - 1;
        array = (T[]) collection.toArray();
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("End of collection");
        }
        return array[cursor--];
    }
}
