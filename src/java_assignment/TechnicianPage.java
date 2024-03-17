package java_assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianPage extends JFrame {
    private JPanel panel;
    private JButton viewassignedtasksButton;
    private JButton updateservicestatusButton;
    private JButton viewservicehistoryButton;
    private JButton logoutButton;
    private List<Feedback> feedbacks;

public TechnicianPage() {
        setTitle("Technician Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(4, 1)); // Increased grid layout to accommodate the logout button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        viewassignedtasksButton = new JButton("View Assigned Tasks");
        updateservicestatusButton = new JButton("Update Service Status");
        viewservicehistoryButton = new JButton("View Service History");
        logoutButton = new JButton("Logout");

        // ActionListener for viewFeedbacksButton
        viewassignedtasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Initialize feedbacks list
                feedbacks = new ArrayList<>();
                try {
                    // Attempt to read feedbacks from file
                    feedbacks = getFeedbacksFromFile();
                } catch (IOException ex) {
                    // Show error message if reading feedbacks fails
                    JOptionPane.showMessageDialog(TechnicianPage.this, "Error reading feedbacks.");
                    return;
                }
                // Display feedbacks
                displayFeedbacks(feedbacks);
            }
        });

        // ActionListener for technicianManagementButton
        updateservicestatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(TechnicianPage.this, "Update Service Status displayed!");
            }
        });

        // ActionListener for serviceManagementButton
        viewservicehistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for service management
                JOptionPane.showMessageDialog(TechnicianPage.this, "View Service History displayed!");
            }
        });

        // ActionListener for logoutButton
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the AdminPage
                new LoginPage(); // Open the login page
            }
        });

        // Adding buttons to the panel
        panel.add(viewassignedtasksButton);
        panel.add(updateservicestatusButton);
        panel.add(viewservicehistoryButton);
        panel.add(logoutButton); // Added logout button to the panel

        add(panel);
        setVisible(true); // Set the frame visible
    }

    // Method to read feedbacks from file and parse them into a list
    private List<Feedback> getFeedbacksFromFile() throws IOException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("feedbacks.txt"))) {
            String line;
            String name = null;
            String phoneNumber = null;
            StringBuilder feedback = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // Check if line is empty, indicating the end of a feedback entry
                if (line.isEmpty()) {
                     // If name, phone number, and feedback are all not null and feedback content is not empty, add feedback to the list
                    if (name != null && phoneNumber != null && !feedback.toString().isEmpty()) {
                        feedbacks.add(new Feedback(name, phoneNumber, feedback.toString()));
                         // Reset variables for the next feedback entry
                        name = null;
                        phoneNumber = null;
                        feedback = new StringBuilder();
                    }
                } else {
                    // Parse the line and assign to corresponding variables
                    if (name == null) {
                        name = line;
                    } else if (phoneNumber == null) {
                        phoneNumber = line;
                    } else {
                        feedback.append(line).append("\n");
                    }
                }
            }
            // Add the last feedback entry if it exists
            if (name != null && phoneNumber != null && !feedback.toString().isEmpty()) {
                feedbacks.add(new Feedback(name, phoneNumber, feedback.toString()));
            }
        }
        return feedbacks;
    }

    // Method to display feedbacks using a dialog window
    private void displayFeedbacks(List<Feedback> feedbacks) {
        StringBuilder feedbackString = new StringBuilder();
         // Construct a string containing details of each feedback
        for (Feedback feedback : feedbacks) {
            feedbackString.append("Name: ").append(feedback.getName()).append("\n");
            feedbackString.append("Phone Number: ").append(feedback.getPhoneNumber()).append("\n");
            feedbackString.append("Feedback: ").append(feedback.getFeedback()).append("\n\n");
        }
        // Show the feedbacks in a dialog window
        JOptionPane.showMessageDialog(this, feedbackString.toString());
    }

    // Inner class representing feedback data
    public static class Feedback {
        private String name;
        private String phoneNumber;
        private String feedback;

        public Feedback(String name, String phoneNumber, String feedback) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.feedback = feedback;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getFeedback() {
            return feedback;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        new TechnicianPage();
    }
}
