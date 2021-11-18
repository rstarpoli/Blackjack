//class in progress to create visuals for Blackjack
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Test {

    public Test() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\rayst\\eclipse-workspace\\Blackjack\\images\\table.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.add(panel);
        JButton b1 = new JButton("Test");
        panel.add(b1);
        JLabel inputLabel = new JLabel("Input:    ");
        panel.add(inputLabel);
        frame.add(new JLabel(new ImageIcon("C:\\Users\\rayst\\eclipse-workspace\\Blackjack\\images\\AceDiamonds.png")));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}