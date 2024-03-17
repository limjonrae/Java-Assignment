package java_assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

public class Java_Assignment extends JFrame {
    private JPanel panel; // Panel to hold buttons
    private JButton registerButton; // Button to navigate to registration page
    private JButton loginButton; // Button to navigate to login page
    
    public Java_Assignment() {
        setTitle("Welcome to APU"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setSize(600, 400); // Set window size
        setLocationRelativeTo(null); // Center window on screen

        panel = new JPanel(new GridBagLayout()); // Create panel with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for GridBagLayout
        gbc.gridx = 0; // Set grid x-coordinate
        gbc.gridy = GridBagConstraints.RELATIVE; // Set grid y-coordinate to be relative
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding around components

        registerButton = new JButton("Register"); // Create register button
        loginButton = new JButton("Login"); // Create login button

        // ActionListener for register button
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new RegistrationPage(); // Open registration page
}
        });

        // ActionListener for login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new LoginPage(); // Open login page
            }
        });

        panel.add(registerButton, gbc); // Add register button to panel
        panel.add(loginButton, gbc); // Add login button to panel

        add(panel); // Add panel to frame
        setVisible(true); // Make frame visible
    }

    //main method
    public static void main(String[] args) {
        new Java_Assignment();
    }
}

class RegistrationPage extends JFrame {
    private JTextField usernameField; // Text field for username input
    private JPasswordField passwordField; // Password field for password input
    private JComboBox<String> userTypeDropdown; // Dropdown for selecting user type
    private JButton registerButton; // Button to register user
    private JButton backButton; // Button to back user

    // Constructor for RegistrationPage
    public RegistrationPage() {
        setTitle("Registration"); // Set window title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close window on dispose
        setSize(400, 350); // Set window size
        setLocationRelativeTo(null); // Center window on screen

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Create panel with GridLayout

         // Components for registration form
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"User", "Admin", "Technician"};
        userTypeDropdown = new JComboBox<>(userTypes);
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        // ActionListener for register button
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeDropdown.getSelectedItem();
                if (isPasswordValid(password)) {
                    if (registerUser(username, password, userType)) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        dispose(); // Close current window
                        new Java_Assignment(); // Open main page
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed! Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password does not meet requirements!");
                }
            }
        });

        // ActionListener for back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new Java_Assignment(); // Open main page
            }
        });

        // Add components to panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(userTypeLabel);
        panel.add(userTypeDropdown);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel); // Add panel to frame
        setVisible(true); // Make frame visible
    }

    // Method to validate password strength
    private boolean isPasswordValid(String password) {
        // Password must contain at least one alphabet, one number, and one special symbol (!@#$%&_)
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    // Method to register user
    private boolean registerUser(String username, String password, String userType) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_database.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    reader.close();
                    return false; // Username already exists
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("user_database.txt", true));
            writer.write("Username: " + username + ", Password: " + password + ", Role: " + userType + "\n");
            writer.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

// Class for the login page
class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    // Constructor for the login page
    public LoginPage() {
        setTitle("Login"); // Setting the title of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Setting the title of the window
        setSize(400, 300); // Setting window size
        setLocationRelativeTo(null); // Centering the window on the screen

        // Creating a panel with GridLayout for the login interface
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Creating labels, text fields, and buttons for username, password, and login
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        // Adding action listeners to the login and back buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieving the entered username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Checking if either username or password fields are empty
                if (username.isEmpty() || password.isEmpty()) {
                    // Displaying an error message if any field is empty
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                     // Retrieving the user type based on the entered username and password
                    String userType = getUserType(username, password);
                    
                    // Checking if user type is retrieved successfully
                    if (userType != null) {
                         // Displaying a success message if login is successful
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        dispose(); // Closing the login page
                        
                        // Opening a new page based on the user type
                        switch (userType) {
                            case "Admin":
                                new AdminPage(); // Opening AdminPage
                                break;
                            case "User":
                                new UserPage(); // Opening UserPage
                                break;
                            case "Technician":
                                new TechnicianPage(); // Opening TechnicianPage
                                break;
                            default:
                                break;
                        }
                    } else {
                        // Displaying an error message if login fails (invalid username or password)
                        JOptionPane.showMessageDialog(null, "Login failed! Invalid username or password.");
                    }
                }
            }
        });
        
        // Adding action listener to the back button
         backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closing the login page
                new Java_Assignment(); // Opening the main application window (Assignment)
            }
        });

         // Adding components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);

        add(panel); // Adding the panel to the frame
        setVisible(true); // Making the frame visible
    }
 
    // Method to retrieve user type based on entered username and password
    private String getUserType(String username, String password) {
        // Attempt to read from the user database file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_database.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                 // Check if the line contains the entered username and password
                if (line.contains("Username: " + username) && line.contains("Password: " + password)) {
                    int startIndex = line.indexOf("Role: ");
                    if (startIndex != -1) {
                        startIndex += 6; // Move to the beginning of the role
                        String userType = line.substring(startIndex);
                        reader.close();
                        return userType.trim(); // Return the user type
                    }
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace(); // Print stack trace if an exception occurs
        }
        return null; // Return null if user type cannot be determined
    }
}
