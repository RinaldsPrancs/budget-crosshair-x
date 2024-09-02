import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class App extends JFrame {
    public App() {
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
        // App sw = new App();
        JFrame f = new JFrame("main");
        ImageIcon img = new ImageIcon("src\\crosshairs\\1.png");
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH);

        // App sw = new App();
        // sw.setVisible(false);
        
        
        JSlider size = new JSlider(0, 200, 10);
       size.addChangeListener((ChangeEvent e) -> {
        
            });
        
            
        JToggleButton b1 = new JToggleButton("Show CSX");
        b1.addActionListener((ActionEvent e) -> {
            JToggleButton btn = (JToggleButton) e.getSource();
            
            btn.setText(btn.isSelected() ? "pushed" : "push me");
            if (btn.isSelected()) {
                System.out.println("IZSLEGT");
                System.out.println(size.getValue());
                
                
            } else {
               
            }
            // } else {
            //     System.out.println("IESLEGT");
            //     sw.setVisible(false);
            // }  
        } 
        );

       
        
        JPanel p = new JPanel();
        f.setLayout(new FlowLayout());
        p.add(b1);
        f.add(p);
        f.add(size);
        f.add(new JLabel(new ImageIcon(newimg), SwingConstants.CENTER));
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
}