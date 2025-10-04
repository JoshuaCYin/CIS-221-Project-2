// Circular doubly linked deque
// Note: For visualization purposes, front and forward refer to the left, and rear and back refer to the right in a horizontally depicted deque
public class Deque<T> implements DequeInterface<T> {
    protected DLLNode<T> front;     // Reference to the front of the deque
    protected DLLNode<T> rear;      // Reference to the rear of the deque
    protected int numElements = 0;  // Total number of elements in the deque

    // Constructor
    public Deque() {
        front = null;
        rear = null;
    }

    // Enqueues an element to the front
    public void enqueueFront(T element) {
        DLLNode<T> newNode = new DLLNode<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;

            // Node links to itself to ensure circularity
            newNode.setForward(newNode);
            newNode.setBack(newNode);
        }
        else {
            newNode.setBack(front);
            front.setForward(newNode);

            // Link front and rear nodes to make list circular
            newNode.setForward(rear);
            rear.setBack(newNode);

            front = newNode; // Update front
        }
        numElements++;
    }

    // Enqueues an element to the rear
    public void enqueueRear(T element) {
        DLLNode<T> newNode = new DLLNode<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;

            // Node links to itself to ensure circularity
            newNode.setForward(newNode);
            newNode.setBack(newNode);
        }
        else {
            newNode.setForward(rear);
            rear.setBack(newNode);

            // Link rear and front nodes to make list circular
            newNode.setBack(front);
            front.setForward(newNode);

            rear = newNode; // Update rear
        }
        numElements++;
    }
    
    // Dequeue an element from the front
    public T dequeueFront() {
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue front attempted on empty deque.");
        else {
            T element;
            element = front.getInfo();

            if (front.getForward() == front) { // If the node links to itself it is the only node in the list
                front = null;
                rear = null;
            }
            else {
                front = front.getBack(); // Set front to the next node in the deque

                // Link front and rear to each other to make list circular
                front.setForward(rear);
                rear.setBack(front);
            }

            numElements--;
            return element;
        }
    }

    // Dequeue an element from the rear
    public T dequeueRear() {
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue rear attempted on empty deque.");
        else {
            T element;
            element = rear.getInfo();

            if (rear.getBack() == rear) { // If the node links to itself it is the only node in the list
                front = null;
                rear = null;
            }
            else {
                rear = rear.getForward(); // Set rear to the next node in the deque

                // Link front and rear to each other to make list circular
                rear.setBack(front);
                front.setForward(rear);
            }
            
            numElements--;
            return element;
        }
    }

    // Checks if deque is full. Always returns false (a doubly linked deque is never full).
    public boolean isFull() {
        return false;
    }

    // Checks if deque is empty.
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    // Returns the total number of elements in the deque
    public int size() {
        return numElements;
    }

    // Returns a string that represents the current state of the deque
    public String toString() {
        DLLNode<T> currNode = front; // Node for iteration
        String dequeString = "";

        if (isEmpty()) { // If there are no elements in the deque
            return dequeString = "Deque is empty.";
        }

        for (int i = 0; i < numElements; i++) {
            dequeString = dequeString + currNode.getInfo();
            if (currNode != rear) { // Could use "<->" instead of a space to signify doubly linked nodes
                dequeString = dequeString + " ";
            }
            currNode = currNode.getBack(); // Since we started at the front, we move back through the deque
        }

        return dequeString;
    }
}
