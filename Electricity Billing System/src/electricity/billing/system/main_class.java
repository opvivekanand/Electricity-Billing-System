package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame  implements ActionListener {
    String acctype;
    String meter_pass;
    main_class( String acctype , String meter_pass) {
        this.meter_pass = meter_pass;
        this.acctype = acctype;

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/ebs.png"));
        Image image = imageIcon.getImage().getScaledInstance(1530, 830, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);


        // Corrected MenuBar and Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif", Font.PLAIN, 15));
//        menuBar.add(menu);
        setJMenuBar(menuBar);

        JMenuItem newcustomer = new JMenuItem("New customer");
        newcustomer.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon customerImg = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image customerImage = customerImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(customerImage));
        newcustomer.addActionListener(this);

        menu.add(newcustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon customerdetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image customerdetailsImage = customerdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(customerdetailsImage));
        customerdetails.addActionListener(this);

        menu.add(customerdetails);

        JMenuItem depositedetails = new JMenuItem("Deposit Details");
        depositedetails.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon depositedetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        Image depositedetailsImage = depositedetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositedetails.setIcon(new ImageIcon(depositedetailsImage));
        depositedetails.addActionListener(this);
        menu.add(depositedetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill ");
        calculatebill .setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon calculatebillImg = new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
        Image calculatebillImage = calculatebillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(calculatebillImage));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);


        JMenu info = new JMenu("Information");
        menu.setFont(new Font("serif", Font.PLAIN, 15));
//        menuBar.add(info);

        JMenuItem Upinfo = new JMenuItem("Update Information");
        Upinfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon UpinfoImage = new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
        Image UpinfoScaledImage = UpinfoImage.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        Upinfo.setIcon(new ImageIcon(UpinfoScaledImage));
        Upinfo.addActionListener(this);
       info.add(Upinfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon viewInfoIcon = new ImageIcon(ClassLoader.getSystemResource("icon/information.png"));
        Image scaledViewInfoImage = viewInfoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(scaledViewInfoImage));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user = new JMenu("User");
      user.setFont(new Font("serif",Font.PLAIN,15));
//        menuBar.add(user);

        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon paybillIcon = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image scaledPaybillImage = paybillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(scaledPaybillImage));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon billdetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
        Image scaledbilldetailsImage = billdetailsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(scaledbilldetailsImage));
        billdetails.addActionListener(this);
        user.add(billdetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));
//        menuBar.add(bill);

        JMenuItem gebBill= new JMenuItem("Generate Bill");
        gebBill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon gebBillIcon = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
        Image scaledgebBillImage = gebBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        gebBill.setIcon(new ImageIcon(scaledbilldetailsImage));
        gebBill.addActionListener(this);
        bill.add(gebBill);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN,15));
//        menuBar.add(utility);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image scaledNotepadImage = notepadIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(scaledNotepadImage));
        notepad.addActionListener(this);
        utility.add(notepad);


        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon calculatorIcon = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image scaledcalculatorImage = calculatorIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(scaledNotepadImage));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));
        menuBar.add(exit);

        JMenuItem eexit = new JMenuItem("Exit");
        eexit.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon exitIcon = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image scaledeexitImage = exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(scaledeexitImage));
        eexit.addActionListener(this);
        exit.add(eexit);

        if (acctype.equals("Admin")){
            menuBar.add(menu);
        }else{
            menuBar.add(bill);
            menuBar.add(user);
            menuBar.add(info);

        }

        menuBar.add(utility);
        menuBar.add(exit);






    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Customer")){
           new newCustomer();
        }else if(msg.equals("Customer Details")){
            new customer_details();

        }else if(msg.equals("Deposit Details")){
            new deposite_details();
        } else if (msg.equals(" Calculate Bill")) {
            new calculate_bill();
            
        } else if (msg.equals("View Information")) {
            new view_information(meter_pass);
            
        } else if (msg.equals("Update Information")) {
            new update_information(meter_pass);

        }



    }

    public static void main(String[] args) {
        new main_class("","");
    }
}
