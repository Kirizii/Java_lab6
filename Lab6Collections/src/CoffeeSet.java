import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Власна реалізація інтерфейсу Set для об'єктів типу Coffee.
 * Внутрішнє представлення — двозв'язний список на базі класу Node.
 */
public class CoffeeSet implements Set<Coffee> {

    private Node head;
    private Node tail;
    private int size;

    /**
     * Порожній конструктор.
     */
    public CoffeeSet() {
    }

    /**
     * Конструктор з одним елементом.
     */
    public CoffeeSet(Coffee coffee) {
        add(coffee);
    }

    /**
     * Конструктор зі стандартної колекції.
     */
    public CoffeeSet(Collection<? extends Coffee> coffees) {
        addAll(coffees);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        Node current = head;

        while (current != null) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public Iterator<Coffee> iterator() {

        return new Iterator<>() {

            private Node current = head;
            private Node lastReturned = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Coffee next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }

                lastReturned = current;
                current = current.next;

                return lastReturned.value;
            }

            @Override
            public void remove() {

                if (lastReturned == null) {
                    throw new IllegalStateException("Немає елемента для видалення");
                }

                removeNode(lastReturned);
                lastReturned = null;
            }
        };
    }

    @Override
    public Object[] toArray() {

        Object[] array = new Object[size];
        int index = 0;

        Node current = head;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {

        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(),
                    size
            );
        }

        int index = 0;
        Object[] result = a;
        Node current = head;

        while (current != null) {
            result[index++] = current.value;
            current = current.next;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(Coffee coffee) {

        if (coffee == null) {
            throw new IllegalArgumentException("Елемент не може бути null");
        }

        if (contains(coffee)) {
            return false;
        }

        Node node = new Node(coffee);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        Node current = head;

        while (current != null) {

            if (current.value.equals(o)) {
                removeNode(current);
                return true;
            }

            current = current.next;
        }

        return false;
    }

    private void removeNode(Node node) {

        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Coffee> c) {

        boolean modified = false;

        for (Coffee coffee : c) {
            if (add(coffee)) {
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        boolean modified = false;
        Node current = head;

        while (current != null) {

            Node next = current.next;

            if (!c.contains(current.value)) {
                removeNode(current);
                modified = true;
            }

            current = next;
        }

        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean modified = false;
        Node current = head;

        while (current != null) {

            Node next = current.next;

            if (c.contains(current.value)) {
                removeNode(current);
                modified = true;
            }

            current = next;
        }

        return modified;
    }

    @Override
    public void clear() {

        head = null;
        tail = null;
        size = 0;
    }
}
