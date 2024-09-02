import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class App{
    // public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    // public static final GraphicsDevice[] gd = ge.getScreenDevices();

    // public static void changeMonitor( int screen, JFrame frame ) {
       
    //     if( screen > -1 && screen < gd.length ) {
    //         frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
    //     } else if( gd.length > 0 ) {
    //         frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
    //     } else {
    //         throw new RuntimeException( "No Screens Found" );
    //     }
    // }

    public static JFrame init(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
        int width = (int)size.getWidth(); 
        int height = (int)size.getHeight();
        JFrame csx = new JFrame();
        csx.setUndecorated(true);
        csx.setAlwaysOnTop(true);
        csx.setSize(100,100);
        csx.setBackground(new Color(0,0,0,0));
        csx.setLayout(new BorderLayout());
        csx.setLocation(width/2-(csx.getHeight()/2), height/2-(csx.getHeight()/2));
        csx.setVisible(false);
        return csx;
    }

    public static void main(String[] args) throws IOException {

        JFrame csx = init();
        BufferedImage bufferedImage = ImageIO.read(new File("src\\crosshairs\\1.png"));
        Image image = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel l = new JLabel();
        csx.add(l);
        l.setIcon(icon);

        JFrame f = new JFrame();
        JSlider s = new JSlider(5,200);
        s.addChangeListener((ChangeEvent ce) -> {
            System.out.println(((JSlider) ce.getSource()).getValue());
            int v = s.getValue();
            Image newImage = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon newIcon = new ImageIcon(newImage);
            csx.setLocation(1920/2-(csx.getHeight()/2), 1080/2-(csx.getHeight()/2));
            l.setIcon(newIcon);
            csx.setSize(v,v);
            
        });
        f.add(s);
        JToggleButton b = new JToggleButton("Show");
        b.addActionListener((ActionEvent ae) -> {
            if (b.isSelected()){
                b.setText("Hide");
                csx.setVisible(true);
            } else {
                b.setText("Show");
                csx.setVisible(false);
            }
        });
        JButton monitorBtn = new JButton("Change monitors");
        
        // monitorBtn.addActionListener((ActionEvent ae) -> {
        // int monitor = 0;
        //    changeMonitor(monitor, csx);
           
           
        // });

        f.setLayout(new FlowLayout());
        f.add(b);
        f.add(monitorBtn);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
}