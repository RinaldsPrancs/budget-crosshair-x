import java.awt.*;
import javax.swing.*;

public class test extends JFrame {
    public test() {
        super("Chrosshair");
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setSize(50,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src\\crosshairs\\1.png");
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH);
        add(new JLabel(new ImageIcon(newimg), SwingConstants.CENTER));
        setLocation(1920/2-10, 1080/2-10);
        setAlwaysOnTop(true);
        setVisible(true);
    }    public static void main(String[] args) {
        // test sw = new test();
        
        JFrame f = new JFrame();
        

       
        
        JPanel p = new JPanel();
        f.setLayout(new FlowLayout());

        f.add(p);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
}