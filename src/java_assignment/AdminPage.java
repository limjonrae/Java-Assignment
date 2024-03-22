package java_assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
    
public class AdminPage extends JFrame {
    private JPanel panel;
    private JButton viewFeedbacksButton;
    private JButton adminManagementButton;
    private JButton userManagementButton;
    private JButton technicianManagementButton;
    private JButton viewBookingButton;
    private JButton registerButton;
    private JButton logoutButton;
    private List<Feedback> feedbacks;

    public AdminPage() {
        setTitle("Admin Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(4, 1)); // Increased grid layout to accommodate the logout button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        viewFeedbacksButton = new JButton("View Feedbacks");
        adminManagementButton = new JButton("Admin Account Management");
        userManagementButton = new JButton("Customer Account Management");
        technicianManagementButton = new JButton("Technician Account Management");
        viewBookingButton = new JButton("View Booking Details");
        registerButton = new JButton("Register New User");
        logoutButton = new JButton("Logout");

        // ActionListener for viewFeedbacksButton
        viewFeedbacksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Initialize feedbacks list
                new ViewFeedback();
            }
        });
        
        // ActionListener for adminManagementButton
        adminManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(AdminPage.this, "Admin Account Management displayed!");
                dispose(); // Close current window
                new AdminManagement();
            }
        });
        
        // ActionListener for userManagementButton
        userManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(AdminPage.this, "Customer Account Management displayed!");
                dispose(); // Close current window
                new UserManagement();
            }
        });

        // ActionListener for technicianManagementButton
        technicianManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for technician management
                JOptionPane.showMessageDialog(AdminPage.this, "Technician Account Management displayed!");
                dispose(); // Close current window
                new TechManagement();
            }
        });

        // ActionListener for serviceManagementButton
        viewBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for viewing booking details
                JOptionPane.showMessageDialog(AdminPage.this, "View Booking displayed!");
                new ViewBooking();
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Initialize feedbacks list
                new RegistrationPage();
            }
        });

        // ActionListener for logoutButton
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the AdminPage
                new LoginSelection(); // Open the login page
            }
        });

        // Adding buttons to the panel
        panel.add(viewFeedbacksButton);
        panel.add(adminManagementButton);
        panel.add(userManagementButton);
        panel.add(technicianManagementButton);
        panel.add(viewBookingButton);
        panel.add(registerButton);
        panel.add(logoutButton); // Added logout button to the panel

        add(panel);
        setVisible(true); // Set the frame visible
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
        new AdminPage();
    }
    
    public class AdminManagement extends JFrame {
    private JTextArea AdminDataTextArea;

    public AdminManagement() {
        setTitle("Admin Account Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        AdminDataTextArea = new JTextArea();
        AdminDataTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(AdminDataTextArea);
        add(scrollPane);

        displayAdminData();

        setVisible(true);
    }
    
    private void displayAdminData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_database.txt"));
            StringBuilder adminData = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Admin")) {
                    adminData.append(line).append("\n");
                }
            }
            
            AdminDataTextArea.setText(adminData.toString());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
    
    public class UserManagement extends JFrame {
    private JTextArea UserDataTextArea;

    public UserManagement() {
        setTitle("Customer Account Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        UserDataTextArea = new JTextArea();
        UserDataTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(UserDataTextArea);
        add(scrollPane);

        displayUserData();

        setVisible(true);
    }
    
    private void displayUserData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_database.txt"));
            StringBuilder userData = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Customer")) {
                    userData.append(line).append("\n");
                }
            }
            
            UserDataTextArea.setText(userData.toString());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
    
    public class TechManagement extends JFrame {
    private JTextArea TechDataTextArea;

    public TechManagement() {
        setTitle("Technician Account Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        TechDataTextArea = new JTextArea();
        TechDataTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(TechDataTextArea);
        add(scrollPane);

        displayTechData();

        setVisible(true);
    }

    private void displayTechData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_database.txt"));
            StringBuilder technicianData = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Technician")) {
                    technicianData.append(line).append("\n");
                }
            }
            
            TechDataTextArea.setText(technicianData.toString());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }
    
    class ViewBooking extends JFrame {
    private JTextArea orderStatusTextArea;

    public ViewBooking() {
        setTitle("Booking Data");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        orderStatusTextArea = new JTextArea();
        orderStatusTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(orderStatusTextArea);
        add(scrollPane);

        displayBookingData();

        setVisible(true);
    }

    private void displayBookingData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("booking_data.txt"));
            String line;
            StringBuilder bookingData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                bookingData.append(line).append("\n");
            }
            orderStatusTextArea.setText(bookingData.toString());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    }
    
    class ViewFeedback extends JFrame {
    private JTextArea ViewFeedbackTextArea;

    public ViewFeedback() {
        setTitle("Feedback");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        ViewFeedbackTextArea = new JTextArea();
        ViewFeedbackTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(ViewFeedbackTextArea);
        add(scrollPane);

        displayFeedbackData();

        setVisible(true);
    }
    
    private void displayFeedbackData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("feedbacks.txt"));
            String line;
            StringBuilder feedbackData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                feedbackData.append(line).append("\n");
            }
            ViewFeedbackTextArea.setText(feedbackData.toString());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
}