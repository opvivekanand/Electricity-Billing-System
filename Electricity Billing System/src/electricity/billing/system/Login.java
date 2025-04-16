package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");

        setLayout(null);

        // Username Label
        JLabel username = new JLabel("User Name");
        username.setBounds(300, 60, 100, 20);
        add(username);

        // Username Text Field
        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        // Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        // Password Field (for security)
        passwordText = new JPasswordField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        // Login As Label
        JLabel login = new JLabel("Login As");
        login.setBounds(300, 140, 100, 20);
        add(login);

        // Login Choice (Dropdown)
        loginChoice = new Choice();
        loginChoice.setBounds(400, 140, 150, 20);
        loginChoice.add("Admin");
        loginChoice.add("User");
        add(loginChoice);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        // Profile Image
        ImageIcon profileIcon = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileImg = profileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel profileLabel = new JLabel(new ImageIcon(profileImg));
        profileLabel.setBounds(5, 5, 250, 250);
        add(profileLabel);

        // Frame properties
        setSize(640, 300);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String susername = userText.getText();
            String spassword = new String(passwordText.getPassword());  // Secure conversion
            String suser = loginChoice.getSelectedItem();

            try {
                database c = new database();

                String query = "SELECT * FROM Signup WHERE username = '" + susername +
                        "' AND password = '" + spassword +
                        "' AND usertype = '" + suser + "'";

                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
