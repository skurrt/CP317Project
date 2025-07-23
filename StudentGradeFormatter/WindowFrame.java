package StudentGradeFormatter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class WindowFrame extends JFrame {

    JScrollPane dirView = new JScrollPane(new JList<>(DataHandler.students.values().toArray()));

    JPanel mainPanel = new JPanel(new BorderLayout(2,2));

    JPanel dirPanel = new JPanel(new BorderLayout(2,2));

    JFileChooser fileSelect = new JFileChooser();

    CardLayout panelCollection = new CardLayout();

    JPanel masterPanel = new JPanel(panelCollection);

    String mPName = "Main";

    String dirName = "Student Directory";

    GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

    int screenWidth = gEnv.getMaximumWindowBounds().width;

    int screenHeight = gEnv.getMaximumWindowBounds().height;

    String currentPanel = mPName;

    JButton switchButton = new JButton(dirName);

    JButton readFile = new JButton("Choose File to Read");

    JButton exportFile = new JButton("Export Data");

    

    public WindowFrame() {

        super();

        this.setVisible(true);

        this.setLocation((int)this.screenWidth/2 - (int)this.screenWidth/4, (int)this.screenHeight/2 - (int)this.screenHeight/4);

        this.setSize((int)this.screenWidth/2, (int)this.screenHeight/2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setupAuxillaryAssets();

    }

    public void destroy() {

        this.dispose();
    }

    public void setupAuxillaryAssets() {

        int labelWidth = masterPanel.getWidth()/10;

        int labelHeight = masterPanel.getHeight()/10;

        JLabel cardLabel = new JLabel(currentPanel);

        JPanel buttonPanel = new JPanel();

        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchPanel();

                cardLabel.setText(currentPanel);
            }
        });

        buttonPanel.add(cardLabel, BorderLayout.WEST);

        buttonPanel.add(switchButton, BorderLayout.EAST);

        buttonPanel.setPreferredSize(new Dimension(labelWidth*2, labelHeight));

        dirPanel.add(dirView, BorderLayout.CENTER);

        dirPanel.add(buttonPanel, BorderLayout.NORTH);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        
        masterPanel.add(mainPanel);

        masterPanel.add(dirPanel);

        this.add(masterPanel);

    }

    public void switchPanel() {

        switchButton.setText(currentPanel);

        if (currentPanel.equals(mPName)) {

            panelCollection.show(masterPanel, dirName);

            currentPanel = dirName;
        }

        else {
            panelCollection.show(masterPanel, mPName);

            currentPanel = mPName;

        }
    }

}
