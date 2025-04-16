package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash() {


        // Load the image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.png"));

        // Scale the image to fit the frame
        Image imageOne = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);

        // Create JLabel with the scaled image
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel); // Correctly adding the label

        // Frame properties
        setSize(600, 400);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try{
            Thread.sleep(3000);
            setVisible(false);

            new Login();

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
