package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    Choice loginASCho;
    TextField meterText, EmployerText, userNameText, nameText,passwordText;  // Fixed variable
    JButton create,back;

    Signup() {
        super("Signup Page");
        getContentPane().setBackground(new Color(168,203,255));

        // Create Account Label
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        // Choice Dropdown (Admin/User)
        loginASCho = new Choice();
        loginASCho.add("admin");
        loginASCho.add("user");
        loginASCho.setBounds(170, 50, 120, 20);
        add(loginASCho);

        // Meter Number Label
        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);  // Corrected method name
        add(meterNo);

        // Meter Number Text Field
        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);  // Corrected method name
        add(meterText);

        // Employer ID Label
        JLabel Employer = new JLabel("Employer ID");  // Fixed capitalization
        Employer.setBounds(30, 100, 125, 20);  // Moved down to prevent overlap
        add(Employer);

        // Employer ID Text Field
        EmployerText = new TextField();
        EmployerText.setBounds(170, 100, 125, 20);  // Moved down to prevent overlap
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("User Name");
        userName.setBounds(30,140,125,20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170, 140, 125, 20);  // Fixed: Applied setBounds() on userNameText
        userNameText.setVisible(true);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup where meter_no = '"+meterText.getText()+"'");
                    if(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }


                }catch (Exception E){
                    E.printStackTrace();
                }

            }
        });

        JLabel password =new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);

        loginASCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                if(user.equals("user")){
                    Employer.setVisible(false);
                    nameText.setEditable(false);
                    EmployerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);

                }else{
                    Employer.setVisible(true);
                      EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }


            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(227,140,134 ));
        create.setForeground(Color.BLACK);
        create.setBounds(50,285,100,25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(227,140,134));
        back.setForeground(Color.BLACK);
        back.setBounds(180,285,100,25);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(225,225,Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(320,30,250,250);
        add(boyLabel);

        // Frame properties
        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){

           String sloginAs = loginASCho.getSelectedItem();
           String susername = userNameText.getText();
           String sname = nameText.getText();
           String spassword = passwordText.getText();
           String smeter = meterText.getText();
           try{

               database c = new database();
               String query = null;
               if(loginASCho.equals("Admin")) {


                   query = "insert into Signup value ('" + smeter + "', '" + susername + "', '" + sname + "', '" + spassword + "', '" + sloginAs + "')";
               }else{
                   query = "UPDATE Signup SET username = '" + susername + "', password = '" + spassword + "', usertype = '" + sloginAs + "' WHERE meter_no = '" + smeter + "'";

               }

               c.statement.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Account Created");
               setVisible(false);
               new Login();

           }catch(Exception E){
               E.printStackTrace();
           }

        }else if(e.getSource() == back){
            setVisible(false);
            new Login();

        }


    }

    public static void main(String[] args) {
        new Signup();
    }
}
