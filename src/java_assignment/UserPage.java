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
    private JButton orderStatusButton;
    private JButton logoutButton;

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
        feedbackButton = new JButton("Write Feedback");
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
                dispose();
                new FeedbackPage();
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
                new LoginSelection();
            }
        });

        panel.add(makeBookingButton);
        panel.add(feedbackButton);
        panel.add(orderStatusButton);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        UserPage userPage = new UserPage();
        userPage.setVisible(true);
    }
}

class FeedbackPage extends JFrame {
    private JPanel panel;
    private JTextField NameField;
    private JTextField emailField;
    private JLabel Rating;
    private JRadioButton Terrible;
    private JRadioButton Bad;
    private JRadioButton Medium;
    private JRadioButton Good;
    private JRadioButton Excellent;
    private JLabel comment;
    private JTextField commentText;
    private JButton backButton;
    private JButton proceedButton;

    public FeedbackPage(){
        setTitle("Feedback Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new GridLayout(20, 5));
        getContentPane().setBackground(new Color(173, 216, 230));

        Font font = new Font("Courier New", Font.BOLD, 13);

        createCustomerField(font);
        createratingField(font);
        createcommentField(font);
        createButtons(font);

        setVisible(true);
    }
    
    private void createCustomerField(Font font) {
        JLabel nameLabel = new JLabel("Customer Name:");
        JLabel emailLabel = new JLabel("Customer Email:");
        NameField = new JTextField();
        NameField.setColumns(15);
        NameField.setFont(font);
        emailField = new JTextField();
        emailField.setColumns(15);
        emailField.setFont(font);
        add(nameLabel);
        add(NameField);
        add(emailLabel);
        add(emailField);
    }
    
    private void createratingField(Font font) {
        JLabel Rating = new JLabel("Rating:");
        Rating.setFont(font);
        Terrible = new JRadioButton("Terrible");
        Bad = new JRadioButton("Bad");
        Medium = new JRadioButton("Medium");
        Good = new JRadioButton("Good");
        Excellent = new JRadioButton("Excellent");
        add(Rating);
        add(Terrible);
        add(Bad);
        add(Medium);
        add(Good);
        add(Excellent);
    }
    
    private void createcommentField(Font font) { 
        JLabel comment = new JLabel("Feedback (Optional):"); 
        commentText = new JTextField(); 
        commentText.setFont(font); 
        commentText.setColumns(15); 
        add(comment); 
        add(commentText); 
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
            
            String customer = NameField.getText();
                String email = emailField.getText();
                String Rating = "";
                if (Terrible.isSelected()) {
                    Rating = "Terrible";
                } else if (Bad.isSelected()) {
                    Rating = "Bad";
                } else if (Medium.isSelected()) {
                    Rating = "Medium";
                } else if (Good.isSelected()) {
                    Rating = "Good";
                } else if (Excellent.isSelected()) {
                    Rating = "Excellent";
                }
                String comment = commentText.getText();
                
                saveFeedback(customer, email, Rating, comment);
                JOptionPane.showMessageDialog(null, "Feedback submitted successfully!");
                }
        });

        add(backButton);
        add(proceedButton);
    }

                private void saveFeedback(String customer, String email, String Rating, String comment) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("feedbacks.txt", true));
            // Append data to the text file
            writer.write("Name: " + customer + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Rating: " + Rating + "\n");
            writer.write("Feedback: " + comment + "\n");
            writer.write("---------------------------\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class BookingPage extends JFrame {

    // declaration of components
    private JPanel panel;
    private JTextField customerNameField;
    private JTextField emailField;
    private JTextField roomNumberField;
    private JTextField blockField;
    private JLabel Furniture;
    private JRadioButton tables;
    private JRadioButton chairs;
    private JRadioButton cupboards;
    private JRadioButton beds;
    private JRadioButton windows;
    private JLabel Electrical;
    private JRadioButton airCond;
    private JRadioButton wallSocket;
    private JRadioButton waterHeater;
    private JRadioButton lights;
    private JRadioButton fans;
    private JLabel otherIssues;
    private JTextField others;
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
        setSize(1000, 1000);
        setLayout(new GridLayout(20, 5));
        getContentPane().setBackground(new Color(173, 216, 230));

        Font font = new Font("Courier New", Font.BOLD, 13);

        createCustomerField(font);
        createRoomNumberField(font);
        createBlockField(font);
        createIssueField(font);
        createotherIssueField(font);
        createTimeRadioButtons(font);
        createDateComboBoxes(font);
        createPaymentMethodComboBox(font);
        createButtons(font);

        setVisible(true);
    }

    private void createCustomerField(Font font) {
        JLabel customerLabel = new JLabel("Customer Name:");
        JLabel emailLabel = new JLabel("Customer Email:");
        customerNameField = new JTextField();
        customerNameField.setColumns(15);
        customerNameField.setFont(font);
        emailField = new JTextField();
        emailField.setColumns(15);
        emailField.setFont(font);
        add(customerLabel);
        add(customerNameField);
        add(emailLabel);
        add(emailField);
    }

    private void createIssueField(Font font) {
        JLabel Furniture = new JLabel("Furniture:");
        Furniture.setFont(font);
        tables = new JRadioButton("Tables");
        chairs = new JRadioButton("Chairs");
        cupboards = new JRadioButton("Cupboards");
        beds = new JRadioButton("Beds");
        windows = new JRadioButton("Windows");
        JLabel Electrical = new JLabel("Electrical Appliances:");
        Furniture.setFont(font);
        airCond = new JRadioButton("Air-Conditioner");
        wallSocket = new JRadioButton("Wall Socket");
        waterHeater = new JRadioButton("Water Heater");
        lights = new JRadioButton("Lights");
        fans = new JRadioButton("Fans");
        add(Furniture);
        add(tables);
        add(chairs);
        add(cupboards);
        add(beds);
        add(windows);
        add(Electrical);
        add(airCond);
        add(wallSocket);
        add(waterHeater);
        add(lights);
        add(fans);
    }

    private void createotherIssueField(Font font) { 
        JLabel otherIssues = new JLabel("Other Issues:"); 
        others = new JTextField(); 
        others.setFont(font); 
        others.setColumns(15); 
        add(otherIssues); 
        add(others); 
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
        JLabel timeLabel = new JLabel("Appointment Time:");
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

                String customer = customerNameField.getText();
                String email = emailField.getText();
                String roomNumber = roomNumberField.getText();
                String block = blockField.getText();
                String problem = "";
                if (tables.isSelected()) {
                    problem = "Tables";
                } else if (chairs.isSelected()) {
                    problem = "Chairs";
                } else if (cupboards.isSelected()) {
                    problem = "Cupboards";
                } else if (beds.isSelected()) {
                    problem = "Beds";
                } else if (windows.isSelected()) {
                    problem = "Windows";
                } else if (airCond.isSelected()) {
                    problem = "Air-Conditioner";
                } else if (wallSocket.isSelected()) {
                    problem = "Wall Socket";
                } else if (waterHeater.isSelected()) {
                    problem = "Water Heater";
                } else if (lights.isSelected()) {
                    problem = "Lights";
                } else if (fans.isSelected()) {
                    problem = "Fans";
                }
                String problemtext = others.getText();
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

                saveBookingData(customer, email, roomNumber, block, time, problem, problemtext, day, month, year, paymentMethod);

                BillingPage billingPage = new BillingPage(customer, email, roomNumber, block, time, problem, problemtext, day, month, year, paymentMethod);
                billingPage.setVisible(true);
                dispose();
            }
        });

        add(backButton);
        add(proceedButton);
    }

    private void saveBookingData(String customer, String email, String roomNumber, String block, String time, String problem, String problemtext,
                                 String day, String month, String year, String paymentMethod) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("booking_data.txt", true));
            // Append data to the text file
            writer.write("Customer: " + customer + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Room Number: " + roomNumber + "\n");
            writer.write("Block: " + block + "\n");
            writer.write("Appointment Time: " + time + "\n");
            writer.write("Problem: " + problem + "\n");
            writer.write("Other Problems: " + problemtext + "\n");
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

    public BillingPage(String customer, String email, String roomNumber, String block, String time, String problem, String problemtext, String day, String month, String year, String paymentMethod) {
        setTitle("Payment Billing Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(13, 2));
        getContentPane().setBackground(new Color(173, 216, 230));

        Font font = new Font("Courier New", Font.BOLD, 13);

        JLabel customerLabel = new JLabel("Customer Name:");
        JLabel emailLabel = new JLabel("Customer Email:");
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JLabel blockLabel = new JLabel("Block:");
        JLabel timeLabel = new JLabel("Appointment Time:");
        JLabel problemLabel = new JLabel("Problem:");
        JLabel problemtextLabel = new JLabel("Other Problems:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel paymentMethodLabel = new JLabel("Payment Method:");

        JLabel customerValueLabel = new JLabel(customer);
        JLabel emailValueLabel = new JLabel(email);
        JLabel roomNumberValueLabel = new JLabel(roomNumber);
        JLabel blockValueLabel = new JLabel(block);
        JLabel timeValueLabel = new JLabel(time);
        JLabel problemValueLabel = new JLabel(problem);
        JLabel problemtextValueLabel = new JLabel(problemtext);
        JLabel dateValueLabel = new JLabel(day + " " + month + " " + year);
        JLabel paymentMethodValueLabel = new JLabel(paymentMethod);

        customerLabel.setFont(font);
        emailLabel.setFont(font);
        roomNumberLabel.setFont(font);
        blockLabel.setFont(font);
        timeLabel.setFont(font);
        problemLabel.setFont(font);
        problemtextLabel.setFont(font);
        dateLabel.setFont(font);
        paymentMethodLabel.setFont(font);

        customerValueLabel.setFont(font);
        emailValueLabel.setFont(font);
        roomNumberValueLabel.setFont(font);
        blockValueLabel.setFont(font);
        timeValueLabel.setFont(font);
        problemValueLabel.setFont(font);
        problemtextValueLabel.setFont(font);
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
        add(emailLabel);
        add(emailValueLabel);
        add(roomNumberLabel);
        add(roomNumberValueLabel);
        add(blockLabel);
        add(blockValueLabel);
        add(timeLabel);
        add(timeValueLabel);
        add(problemLabel);
        add(problemValueLabel);
        add(problemtextLabel);
        add(problemtextValueLabel);
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