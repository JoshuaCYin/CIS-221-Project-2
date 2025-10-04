/*
 * Name: Joshua Yin
 * Course Number: CIS-221-82A
 * Code Description: This program is meant to simulate a web browser with forward and back buttons.
 *                   It allows entry of a new web address, displays the current web address, and uses a deque to allow the user to go forward and backward through the websites entered.
 * Integrity Statement: I did not copy code from other people or sources other than our CIS-221 textbook. I did not use any AI software to help write code.
*/

import java.awt.*;            
import java.awt.event.*;
import javax.swing.*;

// Simulate a web browser's history function using a deque
// Note: For visualization purposes, front and forward refer to the left, and rear and back refer to the right in a horizontally depicted deque.
//       New addresses are added to the rear, and the oldest addresses are at the front.
public class WebBrowserSimGUI {
    // Create a deque to store web addresses
    private static Deque<String> webDeque = new Deque<>();

    // Address text field
    private static JTextField inputAddressField;  // text field for postfix expression

    // Current address label
    private static JLabel currAddressLabel;

    // Main frame
    private static JFrame displayFrame;

    // Forward and back buttons
    private static JButton backButton;
    private static JButton forwardButton;

    // Node to keep track of where the user is in the deque
    private static DLLNode<String> currNode = webDeque.rear;

    // Button listener
    private static class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) { // Listener for button events
            if (event.getActionCommand().equals("Back")) {
                // Handles Back event.
                if (currNode != webDeque.front) {
                    currNode = currNode.getForward(); // Navigate to the front, which is back in terms of history
                    currAddressLabel.setText("Address: " + currNode.getInfo());
                }

                // Update button states
                updateButtonStates();
            }
            else if (event.getActionCommand().equals("Forward")) {
                // Handles Forward event.
                if (currNode != webDeque.rear) {
                    currNode = currNode.getBack(); // Navigate to the rear, which is forward in terms of history
                    currAddressLabel.setText("Address: " + currNode.getInfo());
                }

                // Update button states
                updateButtonStates();
            }
        }
    }

    // Updates button states depending on whether there are still previous or next addresses
    private static void updateButtonStates() {
        if (currNode == webDeque.front) // User has reached the beginning of the queue
            backButton.setEnabled(false); // Disable back button
        else
            backButton.setEnabled(true);

        if (currNode == webDeque.rear) // User has reached the end of the queue
            forwardButton.setEnabled(false); // Disable forward button
        else
            forwardButton.setEnabled(true);
    }
    
    public static void main(String[] args) {

        // Declare/instantiate/intialize/center display frame
        displayFrame = new JFrame();
        displayFrame.setTitle("Web Browser History Simulator");
        displayFrame.setSize(400, 100);
        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayFrame.setLocationRelativeTo(null);

        /*
         * Current address display
         */

        // Instantiate label for the current address
        currAddressLabel = new JLabel("Address: None");

        // Panel for current address
        JPanel currAddressPanel = new JPanel();
        currAddressPanel.add(currAddressLabel);
        currAddressPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align address to begin from the left

        /*
         * Text field for new address
         */

        // Label for the prompt
        JLabel inputAddressLabel = new JLabel("Enter new address:");

        // Text box for address
        inputAddressField = new JTextField("", 30);

        // Panel for address text field
        JPanel inputAddressPanel = new JPanel();
        inputAddressPanel.add(inputAddressLabel);
        inputAddressPanel.add(inputAddressField);

        // Key listener for entering an address
        inputAddressField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Listens for enter key
                    String input = inputAddressField.getText();
                    webDeque.enqueueRear(input); // Enqueue input to deque
                    
                    currAddressLabel.setText("Address: " + input); // Update current address
                    inputAddressField.setText(""); // Clear text field
                    currNode = webDeque.rear; // Set user's current position to the newest address
                    updateButtonStates(); // Update button states
                }
            }
        });

        /*
         * Forward and back buttons
         */

        // Forward and back buttons
        backButton = new JButton("Back");
        forwardButton = new JButton("Forward");

        // Panel for back and forward buttons (navigating)
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1, 2));
        navPanel.add(backButton);
        navPanel.add(forwardButton);

        // Add button functionality
        ActionHandler action = new ActionHandler();
        backButton.addActionListener(action);
        forwardButton.addActionListener(action);

        /*
         * Add GUI components to main frame
         */

        Container contentPane = displayFrame.getContentPane();

        contentPane.add(currAddressPanel, "North");
        contentPane.add(inputAddressPanel, "Center");
        contentPane.add(navPanel, "South");
    
        displayFrame.pack();
        displayFrame.setVisible(true);

        updateButtonStates(); // Set beginning button states
    }
}