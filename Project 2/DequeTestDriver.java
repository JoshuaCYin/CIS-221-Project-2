/*
 * Name: Joshua Yin
 * Course Number: CIS-221-82A
 * Code Description: The code in Deque.java is the implementation of the generic-type DequeInterface using a circular, doubly-linked list.
 *                   The code in DequeTestDriver.java is meant to test the deque implementation found in Deque.java.
 *                   The code in WebBrowserSimGUI.java is meant to simulate a web browser with forward and back buttons.
 * Integrity Statement: I did not copy code from other people or sources other than our CIS-221 textbook. I did not use any AI software to help write code.
*/

// Test driver for the deque implementation
public class DequeTestDriver {
    public static void main(String[] args) {
        // Create a deque, myDeque
        Deque<Integer> myDeque = new Deque<>();

        // Integer variables for testing
        int testInt1 = 10;
        int testInt2 = 20;
        int testInt3 = 30;

        /*
         * Test Case 1: Enqueue to the front and rear when there are no elements in the deque yet
         */
        System.out.println("Test Case 1: Enqueue when there are no elements in the deque yet:");

        // Enqueue to the front
        try {
            System.out.println("Enqueuing element to the front: " + testInt1);
            myDeque.enqueueFront(testInt1);
            System.out.println("Dequeuing element from the front: " + myDeque.dequeueFront());
        } catch (QueueOverflowException e) {
            System.out.println("Queue overflow exception caught: " + e + " This should not be happening!");
        }

        // Enqueue to the rear
        try {
            System.out.println("Enqueuing element to the rear: " + testInt2);
            myDeque.enqueueRear(testInt2);
            System.out.println("Dequeuing element from the rear: " + myDeque.dequeueRear());
        } catch (QueueOverflowException e) {
            System.out.println("Queue overflow exception caught: " + e + " This should not be happening!");
        }

        /*
         * Test Case 2: Enqueue to the front and rear when there are 1 or more elements
         */
        System.out.println("\nTest Case 2: Enqueue to the front and rear when there are 1 or more elements");

        // Enqueue an element to the front first
        System.out.println("Testing front: First enqueuing " + testInt1 + " to the rear.");
        myDeque.enqueueFront(testInt1);

        // Enqueue two more elements to the front
        try {
            System.out.println("Enqueuing elements to the front: " + testInt2 + ", " + testInt3);
            myDeque.enqueueFront(testInt2);
            myDeque.enqueueFront(testInt3);
            System.out.println("Deque state: " + myDeque);
            System.out.println("Dequeuing all from front:");
            while (!myDeque.isEmpty()) {
                System.out.println(myDeque.dequeueFront());
            }
        } catch (QueueOverflowException e) {
            System.out.println("Queue overflow exception caught: " + e + " This should not be happening!");
        }

        // Enqueue an element to the front first
        System.out.println("Testing rear: enqueuing " + testInt1 + " to the rear.");
        myDeque.enqueueRear(testInt1);

        // Enqueue two more elements to the rear
        try {
            System.out.println("Enqueuing elements to the rear: " + testInt2 + ", " + testInt3);
            myDeque.enqueueRear(testInt2);
            myDeque.enqueueRear(testInt3);
            System.out.println("Deque state: " + myDeque);
            System.out.println("Dequeuing all from rear:");
            while (!myDeque.isEmpty()) {
                System.out.println(myDeque.dequeueRear());
            }
        } catch (QueueOverflowException e) {
            System.out.println("Queue overflow exception caught: " + e + " This should not be happening!");
        }

        /*
         * Test Case 3: Dequeue (front and rear) when there are no elements in the deque
         */
        System.out.println("\nTest Case 3: Dequeue when there are no elements in the deque:");

        // Attempt to dequeue from front of deque. Deque should be empty from previous test.
        System.out.println("Attempting to dequeue from front:");
        try {
            myDeque.dequeueFront();
        } catch (QueueUnderflowException e) {
            System.out.println("Queue undeflow exception successfully caught: " + e);
        }

        // Attempt to dequeue from back of deque.
        System.out.println("Attempting to dequeue from rear:");
        try {
            myDeque.dequeueRear();
        } catch (QueueUnderflowException e) {
            System.out.println("Queue undeflow exception successfully caught: " + e);
        }

        /*
         * Test Case 4: Alternate between enqueuing and dequeuing from the front and rear
         */
        System.out.println("\nTest Case 4: Alternate between enqueuing and dequeuing from the front and rear:");

        // Alternate between enqueuing and dequeuing from different ends of the deque
        try {
            System.out.println("Initial deque state: " + myDeque);

            myDeque.enqueueFront(testInt1);
            System.out.println("Enqueued to front. Deque state: " + myDeque);

            myDeque.enqueueRear(testInt3);
            System.out.println("Enqueued to rear. Deque state: " + myDeque);

            myDeque.dequeueFront();
            System.out.println("Dequeued from front. Deque state: " + myDeque);

            myDeque.enqueueFront(testInt2);
            System.out.println("Enqueued to front. Deque state: " + myDeque);

            myDeque.dequeueRear();
            System.out.println("Dequeued from rear. Deque state: " + myDeque);

            myDeque.dequeueFront();
            System.out.println("Dequeued from front. Deque state: " + myDeque);

        } catch (QueueUnderflowException e) {
            System.out.println("Queue underflow exception caught: " + e);
        }

        /*
         * Test Case 5: Ensure circularity with one element
         */
        System.out.println("\nTest Case 5: Ensure circularity when there's one element:");

        myDeque.enqueueFront(testInt1);

        // Display initial deque state
        System.out.println("Deque state: " + myDeque);

        // Display forward link of front node
        System.out.println("Front: " + myDeque.front.getInfo());
        System.out.println("Front forward link: " + myDeque.front.getForward().getInfo());

        // Display back link of rear node
        System.out.println("Rear: " + myDeque.rear.getInfo());
        System.out.println("Rear back link: " + myDeque.rear.getBack().getInfo());

        // Remove element
        myDeque.dequeueFront();

        /*
         * Test Case 6: Ensure circularity with more than one element
         */
        System.out.println("\nTest Case 6: Ensure circularity when there's more than one element:");

        // Enqueue some elements first
        for (int i = 1; i <= 7; i++) {
            myDeque.enqueueFront(i);
        }

        // Display initial deque state
        System.out.println("Deque state: " + myDeque);

        // Display forward link of front node
        System.out.println("Front: " + myDeque.front.getInfo());
        System.out.println("Front forward link: " + myDeque.front.getForward().getInfo());

        // Display back link of rear node
        System.out.println("Rear: " + myDeque.rear.getInfo());
        System.out.println("Rear back link: " + myDeque.rear.getBack().getInfo());

        /*
         * Test Case 7: Enqueue a large number of items to the front and back, then dequeue all
         */
        System.out.println("\nTest Case 7: Enqueue a large number of items to the front and back, then dequeue all:");
        try {
            // Enqueue numbers 1 to 100 to front then rear
            for (int i = 1; i <= 100; i++) {
                myDeque.enqueueFront(i);
            }
            for (int i = 1; i <= 100; i++) {
                myDeque.enqueueRear(i);
            }
            System.out.println("Deque state: " + myDeque);

            // Dequeue 100 elements from front then rear
            System.out.println("Dequeuing 100 elements from both the front and rear...");
            for (int i = 1; i <= 100; i++) {
                myDeque.dequeueFront();
            }
            for (int i = 1; i <= 100; i++) {
                myDeque.dequeueRear();
            }
            System.out.println("Deque state: " + myDeque);
        } catch (QueueOverflowException e) {
            System.out.println("Queue overflow exception caught: " + e + " This should not be happening!");
        } catch (QueueUnderflowException e) {
            System.out.println("Queue underflow exception caught: " + e);
        }

        System.out.println("All tests completed.");
    }
}