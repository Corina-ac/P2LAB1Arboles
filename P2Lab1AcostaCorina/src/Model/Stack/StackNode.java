package Model.Stack;

public class StackNode {
    
    private Object element;
    private StackNode next;

    public StackNode(Object x) {
        this.element = x;
        this.next = null;
    }

    public StackNode(Object element, StackNode next) {
        this.element = element;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}