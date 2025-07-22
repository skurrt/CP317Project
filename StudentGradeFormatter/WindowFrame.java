package StudentGradeFormatter;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame implements ActionListener {
    
    JPanel panel = new JPanel();

    GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

    int screenWidth = gEnv.getMaximumWindowBounds().width;

    int screenHeight = gEnv.getMaximumWindowBounds().height;

    JFileChooser fileSelect = new JFileChooser();

    @Override
    public void actionPerformed(ActionEvent e) {
        

        
    }

    public WindowFrame() {

        super();

        this.setVisible(true);

        this.setSize((int)this.screenWidth/4, (int)this.screenHeight/4);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(this.panel);

        


    }

    public void destroy() {

        this.dispose();
    }

    


}
