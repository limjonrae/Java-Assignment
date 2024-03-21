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

        viewassignedtasksButton = new JButton("View Booking Details");
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
                new LoginSelection(); // Open the login page
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

    // Main method for testing
    public static void main(String[] args) {
        new TechnicianPage();
    }
}
   
