package Model.Stack;

public class ListStack {

    private StackNode top;

    public ListStack() {
        this.top = null;
    }

    public void pushStack(Object element) {
        System.out.println("\n>>> PUSH: Preparing to insert element: '" + element + "'");
        StackNode newNode = new StackNode(element);
        newNode.setNext(top);
        top = newNode;
        System.out.println("<<< PUSH: Element inserted. Current Stack State:");
        System.out.println(this.display());
    }

    public Object popStack() throws Exception {
        if (isEmpty()) {
            throw new Exception("Error: The stack is empty. Cannot perform POP.");
        }
        
        Object removedElement = top.getElement();
        System.out.println("\n>>> POP: Removing element from the top: '" + removedElement + "'");
        top = top.getNext();
        System.out.println("<<< POP: Element '" + removedElement + "' removed. New Stack State:");
        System.out.println(this.display());
        return removedElement;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Error: The stack is empty. Cannot perform PEEK.");
        }
        return top.getElement();
    }
    
    public Object topElement() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty Stack: Cannot get element.");
        }
        return top.getElement();
    }
    
    public StackNode topNode() {
        return top;
    }

    public void clearStack() {
        top = null;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Stack Content (Top to Base) ---\n");
        
        if (isEmpty()) {
            sb.append("The stack is empty.\n");
        } else {
            StackNode current = top;
            int count = 0;
            
            while (current != null) {
                String indicator = (count == 0) ? "  [TOP] -> " : "         -> ";
                sb.append(indicator)
                  .append(current.getElement().toString())
                  .append("\n");
                current = current.getNext();
                count++;
            }
        }
        sb.append("---------------------------------------------------\n");
        return sb.toString();
    }
}