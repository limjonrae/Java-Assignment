package java_assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
    
public class TechnicianPage extends JFrame {
    private JPanel panel;
    private JButton updateservicestatusButton;
    private JButton viewservicehistoryButton;
    private JButton viewBookingButton;
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

        updateservicestatusButton = new JButton("Update Service Status");
        viewservicehistoryButton = new JButton("View Service History");
        viewBookingButton = new JButton("View Booking Details");
        logoutButton = new JButton("Logout");

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
        
        // ActionListener for viewBookingButton
        viewBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // Display message for viewing booking details
                JOptionPane.showMessageDialog(TechnicianPage.this, "View Booking displayed!");
                new ViewBooking();
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
        panel.add(updateservicestatusButton);
        panel.add(viewservicehistoryButton);
        panel.add(viewBookingButton);
        panel.add(logoutButton); // Added logout button to the panel

        add(panel);
        setVisible(true); // Set the frame visible
    }

    // Main method for testing
    public static void main(String[] args) {
        new TechnicianPage();
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
}
   