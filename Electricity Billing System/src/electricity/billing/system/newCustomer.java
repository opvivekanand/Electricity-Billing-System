package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {

    JLabel heading, meternumText, customerName, meterNum, address, city, state, email, phone;
    JButton next, cancel;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;

    newCustomer() {
        super("New Customer");

        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("Customer Name");
        customerName.setBounds(50, 80, 100, 20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 120, 100, 20);
        panel.add(meterNum);

        meternumText = new JLabel("");  // Fix: Use class variable
        meternumText.setBounds(180, 120, 150, 20);
        panel.add(meternumText);

        Random ran = new Random();
        long number = Math.abs(ran.nextLong() % 1000000);
        meternumText.setText("" + number);

        address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new TextField("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new TextField("");
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new TextField("");
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new TextField("");
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new TextField("");
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 390, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(getClass().getResource("/icon/boy.png")); // Ensure correct path
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, BorderLayout.WEST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String sname = nameText.getText();
            String smeter = meternumText.getText();  // Fix: Use JLabel text
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            String query_customer = "INSERT INTO new_customer VALUES('" + sname + "', '" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + semail + "','" + sphone + "')";
            String query_signup = "INSERT INTO Signup VALUES('" + smeter + "', '', '" + sname + "', '', '')";  // Fix: Correct SQL syntax

            try {
                database c = new database(); // Ensure 'database' class is correctly implemented
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully");

                setVisible(false);
                new meterInfo(smeter);
            } catch (Exception ex) {  // Fix: Rename exception variable to avoid conflict
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
