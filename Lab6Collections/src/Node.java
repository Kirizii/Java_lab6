/**
 * Вузол двозв'язного списку для власної реалізації колекції.
 */
public class Node {

    Coffee value;
    Node prev;
    Node next;

    public Node(Coffee value) {
        this.value = value;
    }
}
