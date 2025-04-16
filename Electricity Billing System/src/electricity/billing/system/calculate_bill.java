package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {
    JLabel nameText, addressText;
    JTextField unitText; // Changed to JTextField
    JButton submit, cancel;
    Choice meternumCho, monthCho;

    calculate_bill() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(50, 80, 100, 20);
        panel.add(meternum);

        meternumCho = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            while (resultSet.next()) {
                meternumCho.add(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        meternumCho.setBounds(180, 80, 100, 20);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 120, 100, 20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meternumCho.getSelectedItem() + "'");
            while (resultSet.next()) {
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meternumCho.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(50, 200, 100, 20);
        panel.add(unitconsumed);

        unitText = new JTextField(); // Changed from TextField to JTextField
        unitText.setBounds(180, 200, 150, 20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50, 240, 100, 20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");

        monthCho.setBounds(180, 240, 150, 20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80, 300, 100, 25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 300, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel, BorderLayout.EAST);

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new calculate_bill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNo = meternumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();
            int totalBill = 0;

            // Validate user input
            int units;
            try {
                units = Integer.parseInt(sunit);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for units consumed.");
                return; // Exit if input is invalid
            }

            String query_tax = "SELECT * FROM tax";
            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_tax);
                while (resultSet.next()) {
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // **Fixed table name from "bil" to "bill"**
            String query_total_bill = "INSERT INTO bill (meter_no, month, units, total_bill, status) VALUES ('" + smeterNo + "', '" + smonth + "', '" + units + "', '" + totalBill + "', 'not Paid')";


            try {
                database c = new database();
                c.statement.executeUpdate(query_total_bill);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }
}
