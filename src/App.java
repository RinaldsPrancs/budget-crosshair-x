import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

// TODO 
// fix image scaling
// add listed jframe with csx's
// 
public class App {
    public static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final GraphicsDevice[] gd = ge.getScreenDevices();
    public static int currentMonitor;
    public static int cmWidth;
    public static int cmHeight;
    public static int cmX;
    public static ImageIcon currentCSX;
    public static Image bufferedImage;

    public static void changeMonitor(JFrame frame) {
        currentMonitor++;
        if (currentMonitor == gd.length) {
            currentMonitor = 0;
        }
        cmWidth = gd[currentMonitor].getDefaultConfiguration().getBounds().width;
        cmHeight = gd[currentMonitor].getDefaultConfiguration().getBounds().height;
        cmX = gd[currentMonitor].getDefaultConfiguration().getBounds().x;

        frame.setLocation(cmX + cmWidth / 2 - (frame.getHeight() / 2), frame.getY());

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
        bufferedImage = ImageIO.read(new File("src\\crosshairs\\1.png"));
        Image image = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
        currentCSX = new ImageIcon(image);
        JLabel l = new JLabel();
        csx.add(l);
        l.setIcon(currentCSX);

        JFrame f = new JFrame();
        JSlider s = new JSlider(1, 200);
        s.addChangeListener((ChangeEvent ce) -> {
            int value = s.getValue();
            Image newImage = bufferedImage.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon newIcon = new ImageIcon(newImage);

            csx.setLocation(cmX + cmWidth / 2 - (csx.getHeight() / 2), cmHeight / 2 - (csx.getHeight() / 2));

            l.setIcon(newIcon);
            csx.setSize(value, value);

        });

        f.add(s);
        JToggleButton b = new JToggleButton("See");
        b.addActionListener((ActionEvent ae) -> {
            if (b.isSelected()) {
                b.setText("Hide");
                csx.setVisible(true);
            } else {
                b.setText("See");
                csx.setVisible(false);
            }
        });

        JButton monitorBtn = new JButton("Change monitor");
        monitorBtn.addActionListener((ActionEvent ae) -> {
            changeMonitor(csx);
        });
        JButton fileChooseButton = new JButton("Choose your own crosshair");
        fileChooseButton.addActionListener((ActionEvent ae) -> {
            JFileChooser chooser = new JFileChooser("src\\crosshairs");
            chooser.showOpenDialog(b);
            try {
                File chosenCrosshair = chooser.getSelectedFile();
                Image newCSX = ImageIO.read(chosenCrosshair);
                bufferedImage = newCSX.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon newIcon = new ImageIcon(bufferedImage);
                currentCSX = newIcon;
                l.setIcon(currentCSX);
            } catch (IOException ex) {

            }

        });

        JButton changeCrosshair = new JButton("Change crosshair from presets");
        changeCrosshair.addActionListener((ae) -> {
            JFrame crosshairFrame = new JFrame("Change crosshair");
            crosshairFrame.setLocationRelativeTo(null);
            File directoryPath = new File("src\\crosshairs");
            String contents[] = directoryPath.list();
            JPanel panel = new JPanel();
            panel.setSize(new Dimension(300, 300));
            for (String content : contents) {
                
                JButton temp = new JButton();
                temp.setPreferredSize(new Dimension(100, 100));
                Image tempImg;
                try {
                    tempImg = ImageIO.read(new File("src\\crosshairs\\" + content));
                    tempImg = tempImg.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    ImageIcon tempIcon = new ImageIcon(tempImg);
                    temp.setIcon(tempIcon);

                    temp.addActionListener((actionEvent) -> {
                 
                        Image newCSX;
                        try {
                            newCSX = ImageIO.read(new File("src\\crosshairs\\" + content));
                            bufferedImage = newCSX.getScaledInstance(csx.getHeight(), csx.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon newIcon = new ImageIcon(bufferedImage);
                currentCSX = newIcon;
                l.setIcon(currentCSX);
                        } catch (IOException e) {
                        }
                
                    });



                }catch (IOException ex) {
                }
                panel.add(temp);
            }
            JScrollPane jScrollPane = new JScrollPane(panel);

            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            crosshairFrame.setLayout(new BorderLayout());
            crosshairFrame.add(jScrollPane);
            // crosshairFrame.add(panel);
            crosshairFrame.setSize(new Dimension(300, 300));
            crosshairFrame.setVisible(true);

        });

        f.setLayout(new FlowLayout());
        f.add(b);
        f.add(monitorBtn);
        f.add(fileChooseButton);
        f.add(changeCrosshair);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}