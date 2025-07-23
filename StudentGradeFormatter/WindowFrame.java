package StudentGradeFormatter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class WindowFrame extends JFrame {

    JScrollPane dirView = new JScrollPane(new JList<>(DataHandler.studentsFormatted));

    CardLayout cl = new CardLayout();

    JPanel mainPanel = new JPanel(cl);

    JPanel p1 = new JPanel(new BorderLayout(2, 2));

    JPanel p2 = new JPanel(new BorderLayout(2, 2));

    String mPName = "Main";

    String dirName = "Student Directory";

    GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

    int screenWidth = gEnv.getMaximumWindowBounds().width;

    int screenHeight = gEnv.getMaximumWindowBounds().height;

    String currentPanel = mPName;

    JButton switchButton = new JButton("Go to " + dirName);

    JButton switchButton2 = new JButton("Go to " + mPName);

    JButton readNameFile = new JButton("Choose Student Name File");

    JButton readCourseFile = new JButton("Choose Course File");

    JButton exportFile = new JButton("Export Data");

    

    public WindowFrame() {

        super();

        this.setVisible(true);

        this.setLocation((int)this.screenWidth/2 - (int)this.screenWidth/4, (int)this.screenHeight/2 - (int)this.screenHeight/4);

        this.setSize((int)this.screenWidth/2, (int)this.screenHeight/2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle("Student Grade Formatter");

        this.setupAuxillaryAssets();

    }

    public void destroy() {

        this.dispose();
    }

    public void setupAuxillaryAssets() {

        int labelMaxWidth = this.screenWidth/10;

        int labelMaxHeight = this.screenHeight/10;

        switchButton.setPreferredSize(new Dimension(labelMaxWidth, labelMaxHeight));

        switchButton2.setPreferredSize(new Dimension(labelMaxWidth, labelMaxHeight));

        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchPanel();

            }
        });

        readNameFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });

        readCourseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });

        exportFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                
            }
        });

        switchButton2.addActionListener(switchButton.getActionListeners()[0]);

        p1.add(switchButton, BorderLayout.PAGE_START);

        JPanel panelception = new JPanel(new GridLayout());

        panelception.setBackground(Color.BLACK);

        panelception.setMaximumSize(new Dimension(p1.getWidth()/8, p1.getHeight()/8));

        panelception.add(readNameFile);

        panelception.add(readCourseFile);

        panelception.add(exportFile);

        p1.add(panelception);

        p2.add(dirView, BorderLayout.CENTER);

        p2.add(switchButton2, BorderLayout.PAGE_START);

        mainPanel.add(p1, mPName);

        mainPanel.add(p2, dirName);

        this.add(mainPanel);

    }

    public void switchPanel() {

        if (currentPanel.equals(mPName)) {

            cl.show(mainPanel, dirName);

            currentPanel = dirName;
        }

        else {
            cl.show(mainPanel, mPName);

            currentPanel = mPName;

        }
    }

    

}
