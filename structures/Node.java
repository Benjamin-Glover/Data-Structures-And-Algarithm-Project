package structures;

import core.Drug;

public class Node {
    public Drug data;
    public Node next;

    public Node(Drug data) {
        this.data = data;
        this.next = null;
    }
}
