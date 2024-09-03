import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class App {
    public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final GraphicsDevice[] gd = ge.getScreenDevices();
    public static int currentMonitor;
    public static int cmWidth;
    public static int cmHeight;
    public static int cmX;
    public static void changeMonitor(JFrame frame) {
        currentMonitor++;  
        if (currentMonitor == gd.length) {
            currentMonitor = 0;
        }
        cmWidth = gd[currentMonitor].getDefaultConfiguration().getBounds().width;
        cmHeight = gd[currentMonitor].getDefaultConfiguration().getBounds().height;
        cmX = gd[currentMonitor].getDefaultConfiguration().getBounds().x;

        frame.setLocation(cmX + cmWidth / 2 - (frame.getHeight() / 2), frame.getY());


        // if (currentMonitor == 0) {
        //     int currentMonitorWidth = gd[currentMonitor].getDefaultConfiguration().getBounds().width;
        //     frame.setLocation(currentMonitorWidth / 2 - (frame.getHeight() / 2), frame.getY());
        // } else if (currentMonitor < gd.length) {
        //     int currentMonitorX = gd[currentMonitor].getDefaultConfiguration().getBounds().x;
        //     frame.setLocation(currentMonitorX + (currentMonitorX / 2 - (frame.getHeight() / 2)), frame.getY());
        // }
      
    }

    public static JFrame init() {
        JFrame csx = new JFrame();
        csx.setUndecorated(true);
        csx.setAlwaysOnTop(true);
        csx.setSize(100, 100);
        csx.setBackground(new Color(0, 0, 0, 0));
        csx.setLayout(new BorderLayout());
        csx.setLocation(cmWidth / 2 - (csx.getHeight() / 2), cmHeight / 2 - (csx.getHeight() / 2));
        csx.setVisible(false);
        return csx;
    }

    public static void main(String[] args) throws IOException {
        currentMonitor = 0;
        cmWidth = gd[currentMonitor].getDefaultConfiguration().getBounds().width;
        cmHeight = gd[currentMonitor].getDefaultConfiguration().getBounds().height;
        cmX = gd[currentMonitor].getDefaultConfiguration().getBounds().x;
        JFrame csx = init();
        BufferedImage bufferedImage = ImageIO.read(new File("src\\crosshairs\\1.png"));
        Image image = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel l = new JLabel();
        csx.add(l);
        l.setIcon(icon);

        JFrame f = new JFrame();
        JSlider s = new JSlider(5, 200);
        s.addChangeListener((ChangeEvent ce) -> {
            int value = s.getValue();
            Image newImage = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon newIcon = new ImageIcon(newImage);

            csx.setLocation(cmX + cmWidth / 2 - (csx.getHeight() / 2), cmHeight / 2 - (csx.getHeight() / 2));


            // csx.setLocation(cmWidth / 2 - (csx.getHeight() / 2), cmHeight / 2 - (csx.getHeight() / 2));
            l.setIcon(newIcon);
            csx.setSize(value, value);

        });
        f.add(s);
        JToggleButton b = new JToggleButton("Show");
        b.addActionListener((ActionEvent ae) -> {
            if (b.isSelected()) {
                b.setText("Hide");
                csx.setVisible(true);
            } else {
                b.setText("Show");
                csx.setVisible(false);
            }
        });
        JButton monitorBtn = new JButton("Change monitors");
        monitorBtn.addActionListener((ActionEvent ae) -> {
            changeMonitor(csx);
        });

        f.setLayout(new FlowLayout());
        f.add(b);
        f.add(monitorBtn);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}