package java_assignment;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author theness_
 */
class TechnicianPage extends JFrame {
    public TechnicianPage() {
        setTitle("Technician Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Welcome to Technician Page!");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label);

        setVisible(true);
    }
}
