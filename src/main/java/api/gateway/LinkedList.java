package api.gateway;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {

    public Node<T> head;
    public Node<T> current;


    public List<Node<T>> print() {
        List<Node<T>> nodes = new ArrayList<>();
        current = head;
        while (current != null) {
            nodes.add(current);
            System.out.println(current.value);
            current = current.next;
        }
        current = head;
        return nodes;
    }

    public void insert(Node node) {
        if (node instanceof Node) {
            node.next = head;
            head = node;
            current = head;
        } else {
            System.out.println("Only a node can be inserted.");
        }
    }

    public void append(Node<T> node) {
        if (node instanceof Node) {
            if (head == null) {
                head = node;
                head.next = null;
            } else {
                current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
            current = head;
        } else {
            System.out.println("Only a node can be appended.");
        }
    }

    public boolean includes(T data) {
        current = head;
        while (current != null) {
            if (current.value == data) {
                return true;
            }
            current = current.next;
        }
        current = head;
        return false;
    }

    public void insertBefore(Node<T> newValue, Node<T> existingValue) {
        if (!(newValue instanceof Node) || !(existingValue instanceof Node)) {
            System.out.println("Only node types can be inputs");
            return;
        }
        boolean success = false;
        if (newValue.next != null) {
            System.out.println("Cannot add node - input node contains reference to another node.");
        } else {
            current = head;
            if (head.value == existingValue.value) {
                insert(newValue);
                success = true;
            } else {
                while (current.next != null) {
                    if (current.next.value == existingValue.value) {
                        newValue.next = current.next;
                        current.next = newValue;
                        success = true;
                        break;
                    }
                    current = current.next;
                }
            }
            if (success) {
                System.out.println("Successfully added Node " + newValue.value + " before Node " + existingValue.value);
            } else {
                System.out.println("Unable to add Node. Check if reference node exists in the Linked List");
            }
            current = head;
        }
    }

    public void insertAfter(Node<T> existingValue, Node<T> newValue) {
        if (!(newValue instanceof Node) || !(existingValue instanceof Node)) {
            System.out.println("Only node types can be inputs");
            return;
        }
        boolean success = false;
        if (newValue.next != null) {
            System.out.println("Cannot add node - input node contains reference to another node.");
        } else {
            current = head;
            while (current != null) {
                if (current.value == existingValue.value) {
                    newValue.next = current.next;
                    current.next = newValue;
                    success = true;
                    break;
                }
                current = current.next;
            }
        }
        if (success) {
            System.out.println("Successfully added Node " + newValue.value + " after Node " + existingValue.value);
        } else {
            System.out.println("Unable to add Node. Check if reference node exists in the Linked List");
        }
        current = head;
    }

    public T getFromEnd(int endIndex) {
        if (endIndex < 0) {
            throw new IllegalArgumentException("Index number cannot be negative");
        }
        Node<T> targetedNode = null;
        int count = 0;
        current = head;
        while (current != null) {
            if (count == endIndex) {
                targetedNode = head;
            } else if (count > endIndex) {
                targetedNode = targetedNode.next;
            }
            count++;
            current = current.next;
        }
        if (targetedNode != null) {
            return (T) targetedNode.value;
        } else {
            throw new IndexOutOfBoundsException("Input value is greater than the length of the Linked List");
        }
    }

    public static LinkedList mergeLists(LinkedList one, LinkedList two) {
        if (one.head == null && two.head != null) {
            System.out.println("Null argument found in first parameter");
            return two;
        } else {
            one.current = one.head;
            two.current = two.head;
            while (one.current != null && two.current != null) {
                if(one.current.next == null) {
                    one.current.next = two.current;
                    break;
                }
                Node tempOne = one.current.next;
                Node tempTwo = two.current.next;
                one.current.next = two.current;
                if(tempOne !=null) {
                    two.current.next = tempOne;
                }
                one.current = tempOne;
                two.current = tempTwo;
            }

            one.current = one.head;
            return one;
        }
    }

    public static int getLength(Node input) {
        if(input == null) {
            return 0;
        }
        return 1 + getLength(input.next);
    }
}
