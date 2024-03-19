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
    private JButton viewavailabilitystatusButton;
    private JButton logoutButton;
    private List<AssignedTask> assignedtasks;

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
        viewavailabilitystatusButton = new JButton("View Availability Status");
        logoutButton = new JButton("Logout");

        // ActionListener for viewFeedbacksButton
        viewassignedtasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Initialize feedbacks list
                new OrderStatusPage();
            }
        });

        // ActionListener for technicianManagementButton
        updateservicestatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(TechnicianPage.this, "Update Service Status displayed!");
                dispose();
                new service_status();
            }
        });

        // ActionListener for serviceManagementButton
        viewservicehistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for service management
                JOptionPane.showMessageDialog(TechnicianPage.this, "View Service History displayed!");
            }
        });
        
        // ActionListener for technicianManagementButton
        viewavailabilitystatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(TechnicianPage.this, "View Availability Status displayed!");
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
        panel.add(viewavailabilitystatusButton);
        panel.add(logoutButton); // Added logout button to the panel

        add(panel);
        setVisible(true); // Set the frame visible
    }

    // Method to read feedbacks from file and parse them into a list
    private List<AssignedTask> getAssignedTasksFromFile() throws IOException {
        List<AssignedTask> assignedtasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("assigned_tasks.txt"))) {
            String line;
            String name = null;
            String phoneNumber = null;
            StringBuilder assignedtask = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // Check if line is empty, indicating the end of a feedback entry
                if (line.isEmpty()) {
                     // If name, phone number, and feedback are all not null and feedback content is not empty, add feedback to the list
                    if (name != null && phoneNumber != null && !assignedtask.toString().isEmpty()) {
                        assignedtasks.add(new AssignedTask(name, phoneNumber, assignedtask.toString()));
                         // Reset variables for the next feedback entry
                        name = null;
                        phoneNumber = null;
                        assignedtask = new StringBuilder();
                    }
                } else {
                    // Parse the line and assign to corresponding variables
                    if (name == null) {
                        name = line;
                    } else if (phoneNumber == null) {
                        phoneNumber = line;
                    } else {
                        assignedtask.append(line).append("\n");
                    }
                }
            }
            // Add the last feedback entry if it exists
            if (name != null && phoneNumber != null && !assignedtask.toString().isEmpty()) {
                assignedtasks.add(new AssignedTask(name, phoneNumber, assignedtask.toString()));
            }
        }
        return assignedtasks;
    }

    // Method to display feedbacks using a dialog window
    private void displayAssignedTasks(List<AssignedTask> assignedtasks) {
        StringBuilder AssignedTaskString = new StringBuilder();
         // Construct a string containing details of each feedback
        for (AssignedTask assignedtask: assignedtasks) {
            AssignedTaskString.append("Name: ").append(assignedtask.getName()).append("\n");
            AssignedTaskString.append("Phone Number: ").append(assignedtask.getPhoneNumber()).append("\n");
            AssignedTaskString.append("Assigned Task: ").append(assignedtask.getAssignedTask()).append("\n\n");
        }
        // Show the feedbacks in a dialog window
        JOptionPane.showMessageDialog(this, AssignedTaskString.toString());
    }

    // Inner class representing feedback data
    public static class AssignedTask {
        private String name;
        private String phoneNumber;
        private String assignedtask;

        public AssignedTask(String name, String phoneNumber, String assignedtask) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.assignedtask = assignedtask;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAssignedTask() {
            return assignedtask;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        new TechnicianPage();
    }
    
    class service_status extends JFrame {
        private JComboBox<String> StatusDropdown; // Dropdown for selecting technician's status
        private JButton updateButton;
        
        
        // Constructor for Service Status Page
    public service_status() {
        setTitle("Update Service Status"); // Set window title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close window on dispose
        setSize(400, 350); // Set window size
        setLocationRelativeTo(null); // Center window on screen

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Create panel with GridLayout

         // Components for update service status page
        JLabel StatusLabel = new JLabel("Status: ");
        String[] Status = {"Busy", "Free", "On Leave"};
        StatusDropdown = new JComboBox<>(Status);
        updateButton = new JButton("OK");

        // ActionListener for register button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Status = (String) StatusDropdown.getSelectedItem();
                JOptionPane.showMessageDialog(null, "Status updated!");
                dispose();
                new TechnicianPage();
            }
        });
        setVisible(true); // Making the frame visible
    
    
        panel.add(StatusLabel);
        panel.add(StatusDropdown);
        panel.add(updateButton);
 
        add(panel); // Add panel to frame
        setVisible(true); // Make frame visible
    }
    
        }
    }
   
