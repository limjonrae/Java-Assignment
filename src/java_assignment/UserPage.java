package java_assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
    
public class UserPage extends JFrame {
    private JPanel panel;
    private JButton makeBookingButton;
    private JButton feedbackButton;
    private JButton logoutButton;
    private JButton orderStatusButton;

    public UserPage() {
        setTitle("User Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(4, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        makeBookingButton = new JButton("Make Booking");
        feedbackButton = new JButton("View Feedback");
        orderStatusButton = new JButton("View Order Status");
        logoutButton = new JButton("Logout");

        makeBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BookingPage();
}
        });

        feedbackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to view and submit feedback
                String name = JOptionPane.showInputDialog(null, "Enter your name:");
                String phoneNumber = JOptionPane.showInputDialog(null, "Enter your phone number:");
                String feedback = JOptionPane.showInputDialog(null, "Enter your feedback:");

                if (name != null && phoneNumber != null && feedback != null) {
                    // Save feedback to file
                    saveFeedback(name, phoneNumber, feedback);
                    JOptionPane.showMessageDialog(null, "Feedback submitted successfully!");
                }
            }
        });

        orderStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrderStatusPage();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to logout
                dispose();
                new LoginPage();
            }
        });

        panel.add(makeBookingButton);
        panel.add(feedbackButton);
        panel.add(orderStatusButton);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }

    private void saveFeedback(String name, String phoneNumber, String feedback) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("feedbacks.txt", true));
            writer.write("Name: " + name + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Feedback: " + feedback + "\n\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserPage userPage = new UserPage();
        userPage.setVisible(true);
    }
}

class BookingPage extends JFrame {

    // declaration of components
    private JTextField customerField;
    private JTextField roomNumberField;
    private JTextField blockField;
    private JTextField problemField;
    private JRadioButton time9AMRadioButton;
    private JRadioButton time12PMRadioButton;
    private JRadioButton time3PMRadioButton;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JButton backButton;
    private JButton proceedButton;
    private JComboBox<String> paymentMethodComboBox;

    public BookingPage() {
        setTitle("Booking Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(11, 2));
        getContentPane().setBackground(new Color(173, 216, 230));

        Font font = new Font("Courier New", Font.BOLD, 13);

        createCustomerField(font);
        createRoomNumberField(font);
        createBlockField(font);
        createProblemField(font);
        createTimeRadioButtons(font);
        createDateComboBoxes(font);
        createPaymentMethodComboBox(font);
        createButtons(font);

        setVisible(true);
    }

    private void createCustomerField(Font font) {
        JLabel customerLabel = new JLabel("Customer Name:");
        customerField = new JTextField();
        customerField.setColumns(15);
        customerField.setFont(font);
        add(customerLabel);
        add(customerField);
    }

    private void createProblemField(Font font) {
        JLabel problemLabel = new JLabel("Problem:");
        problemField = new JTextField();
        problemField.setFont(font);
        problemField.setColumns(15);
        add(problemLabel);
        add(problemField);
    }

    private void createRoomNumberField(Font font) {
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberField = new JTextField();
        roomNumberField.setFont(font);
        roomNumberField.setColumns(15);
        add(roomNumberLabel);
        add(roomNumberField);
    }

    private void createBlockField(Font font) {
        JLabel blockLabel = new JLabel("Block:");
        blockField = new JTextField();
        blockField.setFont(font);
        blockField.setColumns(15);
        add(blockLabel);
        add(blockField);
    }

    private void createTimeRadioButtons(Font font) {
        JLabel timeLabel = new JLabel("Cleaning Time:");
        time9AMRadioButton = new JRadioButton("9:00 AM");
        time12PMRadioButton = new JRadioButton("12:00 PM");
        time3PMRadioButton = new JRadioButton("3:00 PM");
        time9AMRadioButton.setFont(font);
        time12PMRadioButton.setFont(font);
        time3PMRadioButton.setFont(font);
        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(time9AMRadioButton);
        timeGroup.add(time12PMRadioButton);
        timeGroup.add(time3PMRadioButton);
        add(timeLabel);
        add(time9AMRadioButton);
        add(new JLabel(""));
        add(time12PMRadioButton);
        add(new JLabel(""));
        add(time3PMRadioButton);
    }

    private void createDateComboBoxes(Font font) {
        JLabel dateLabel = new JLabel("Date:");
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = Integer.toString(i + 1);
        }
        dayComboBox = new JComboBox<>(days);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthComboBox = new JComboBox<>(months);

        String[] years = {"2022", "2023", "2024", "2025"};
        yearComboBox = new JComboBox<>(years);

        dayComboBox.setFont(font);
        monthComboBox.setFont(font);
        yearComboBox.setFont(font);

        add(dateLabel);
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
    }

    private void createPaymentMethodComboBox(Font font) {
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        String[] paymentMethods = {"Cash", "Credit Card"};
        paymentMethodComboBox = new JComboBox<>(paymentMethods);
        paymentMethodComboBox.setFont(font);

        add(paymentMethodLabel);
        add(paymentMethodComboBox);
    }

    private void createButtons(Font font) {
        backButton = new JButton("Back");
        proceedButton = new JButton("Proceed");

        backButton.setFont(font);
        proceedButton.setFont(font);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserPage();
            }
        });

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String customer = customerField.getText();
                String roomNumber = roomNumberField.getText();
                String block = blockField.getText();
                String problem = problemField.getText();
                String time = "";
                if (time9AMRadioButton.isSelected()) {
                    time = "9:00 AM";
                } else if (time12PMRadioButton.isSelected()) {
                    time = "12:00 PM";
                } else if (time3PMRadioButton.isSelected()) {
                    time = "3:00 PM";
                }
                String day = (String) dayComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();

                saveBookingData(customer, roomNumber, block, time, problem, day, month, year, paymentMethod);

                BillingPage billingPage = new BillingPage(customer, roomNumber, block, time, problem, day, month, year, paymentMethod);
                billingPage.setVisible(true);
                dispose();
            }
        });

        add(backButton);
        add(proceedButton);
    }

    private void saveBookingData(String customer, String roomNumber, String block, String time, String problem,
                                 String day, String month, String year, String paymentMethod) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("booking_data.txt", true));
            // Append data to the text file
            writer.write("Customer: " + customer + "\n");
            writer.write("Room Number: " + roomNumber + "\n");
            writer.write("Block: " + block + "\n");
            writer.write("Cleaning Time: " + time + "\n");
            writer.write("Problem: " + problem + "\n");
            writer.write("Date: " + day + " " + month + " " + year + "\n");
            writer.write("Payment Method: " + paymentMethod + "\n");
            writer.write("---------------------------\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class BillingPage extends JFrame {
    private JButton backButton;
    private JButton payButton;

    public BillingPage(String customer, String roomNumber, String block, String time, String problem, String day, String month, String year, String paymentMethod) {
        setTitle("Payment Billing Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(13, 2));
        getContentPane().setBackground(new Color(173, 216, 230));

        Font font = new Font("Courier New", Font.BOLD, 13);

        JLabel customerLabel = new JLabel("Customer Name:");
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JLabel blockLabel = new JLabel("Block:");
        JLabel timeLabel = new JLabel("Cleaning Time:");
        JLabel problemLabel = new JLabel("Problem:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel paymentMethodLabel = new JLabel("Payment Method:");

        JLabel customerValueLabel = new JLabel(customer);
        JLabel roomNumberValueLabel = new JLabel(roomNumber);
        JLabel blockValueLabel = new JLabel(block);
        JLabel timeValueLabel = new JLabel(time);
        JLabel problemValueLabel = new JLabel(problem);
        JLabel dateValueLabel = new JLabel(day + " " + month + " " + year);
        JLabel paymentMethodValueLabel = new JLabel(paymentMethod);

        customerLabel.setFont(font);
        roomNumberLabel.setFont(font);
        blockLabel.setFont(font);
        timeLabel.setFont(font);
        problemLabel.setFont(font);
        dateLabel.setFont(font);
        paymentMethodLabel.setFont(font);

        customerValueLabel.setFont(font);
        roomNumberValueLabel.setFont(font);
        blockValueLabel.setFont(font);
        timeValueLabel.setFont(font);
        problemValueLabel.setFont(font);
        dateValueLabel.setFont(font);
        paymentMethodValueLabel.setFont(font);

        backButton = new JButton("Back");
        payButton = new JButton("Pay");
        backButton.setFont(font);
        payButton.setFont(font);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BookingPage();
            }
        });

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Payment successful via " + paymentMethod);
            }
        });

        add(customerLabel);
        add(customerValueLabel);
        add(roomNumberLabel);
        add(roomNumberValueLabel);
        add(blockLabel);
        add(blockValueLabel);
        add(timeLabel);
        add(timeValueLabel);
        add(problemLabel);
        add(problemValueLabel);
        add(dateLabel);
        add(dateValueLabel);
        add(paymentMethodLabel);
        add(paymentMethodValueLabel);
        add(backButton);
        add(payButton);

        setVisible(true);
    }
}

class OrderStatusPage extends JFrame {
    private JTextArea orderStatusTextArea;

    public OrderStatusPage() {
        setTitle("Order Status");
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

