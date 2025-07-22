package StudentGradeFormatter;

import java.awt.CardLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame {

    JPanel masterPanel = new JPanel();

    CardLayout panelCollection = new CardLayout();

    JPanel mainPanel = new JPanel();

    JPanel dirPanel = new JPanel();

    GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

    int screenWidth = gEnv.getMaximumWindowBounds().width;

    int screenHeight = gEnv.getMaximumWindowBounds().height;

    JFileChooser fileSelect = new JFileChooser();

    public WindowFrame() {

        super();

        this.setVisible(true);

        this.setSize((int)this.screenWidth/4, (int)this.screenHeight/4);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        masterPanel.add(mainPanel, "Main");

        masterPanel.add(dirPanel, "Student Directory");

        


    }

    public void destroy() {

        this.dispose();
    }


}
