package electricity.billing.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposite_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchMonthCho;
    JTable table;
    JButton search, print, close;

    deposite_details() {
        super("Deposit Details"); // Fixed typo
        getContentPane().setBackground(new Color(192, 186, 254));

        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search BY Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180, 20, 150, 20);
        add(searchMeterCho);

        // Fetch meter numbers from the database
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("SELECT meter_no FROM bill");
            while (resultSet.next()) {
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel searchMonth = new JLabel("Search BY Month"); // Fixed label text
        searchMonth.setBounds(400, 20, 100, 20);
        add(searchMonth);

        // Month Selection
        searchMonthCho = new Choice();
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        for (String month : months) {
            searchMonthCho.add(month);
        }
        searchMonthCho.setBounds(520, 20, 150, 20);
        add(searchMonthCho);

        // JTable for displaying deposit details
        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JScrollPane for the table
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 300);
        add(sp);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600, 70, 80, 20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query_search = "SELECT * FROM bill WHERE meter_no='" + searchMeterCho.getSelectedItem() + "' AND month='" + searchMonthCho.getSelectedItem() + "'";
            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == close) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new deposite_details();
    }
}
