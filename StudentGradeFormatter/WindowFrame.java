package StudentGradeFormatter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;



public class WindowFrame extends JFrame {

    boolean nameFileSelected = false;

    boolean courseFilesSelected = false;

    JScrollPane dirView = new JScrollPane(new JList<>((DataHandler.studentsFormatted).toArray()));

    CardLayout cl = new CardLayout();

    JPanel mainPanel = new JPanel(cl);

    JPanel p1 = new JPanel(new BorderLayout(2, 2));

    JPanel p2 = new JPanel(new BorderLayout(2, 2));

    String mPName = "Main";

    String dirName = "Student Directory";

    GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    int screenWidth = gEnv.getMaximumWindowBounds().width;

    int screenHeight = gEnv.getMaximumWindowBounds().height;

    String currentPanel = mPName;

    JButton switchButton = new JButton("Go to " + dirName);

    JButton switchButton2 = new JButton("Go to " + mPName);

    JButton readNameFile = new JButton("Choose Student Name File");

    JButton readCourseFile = new JButton("Choose Course File");

    JButton exportFile = new JButton("Export Data");

    JFileChooser fileChooser = new JFileChooser();

    

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

                File file = selectFile();

                if (file != null) {

                    try {

                        DataHandler.readStudentFile(file.getAbsolutePath());

                        nameFileSelected = true;

                        updateViewport();

                    } catch (IOException ex) {

                        ex.printStackTrace();
                    }
                }
            }
        });

        readCourseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                File file = selectFile();

                if (file != null) {

                    try {

                        DataHandler.readGradesFile(file.getAbsolutePath());

                        courseFilesSelected = true;

                        updateViewport();

                    } catch (IOException ex) {
                    }
                }

            }
        });

        exportFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameFileSelected && courseFilesSelected) {

                    File file = selectFile();
                    try {

                        DataHandler.writeOutputFile(file.getAbsolutePath());

                        popup("Output file created!");

                    } catch (IOException ex){

                        ex.printStackTrace();
                    }
                }
                else {
                    popup("Can't export non-existent data");
                }
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

    public File selectFile() {

        File file = null;

        int fileFound = this.fileChooser.showOpenDialog(null);

        if (fileFound == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();
        }
        return file;
    }

    public void updateViewport() {

        if (courseFilesSelected && nameFileSelected) {

            DataHandler.formatIntoArray();

            dirView.setViewport(null);

            dirView.setViewportView(new JList<>((DataHandler.studentsFormatted).toArray()));

        }
    }

    public void popup(String message) {

        JFrame temp = new JFrame();

        temp.setSize(new Dimension(this.screenWidth/6, this.screenHeight/6));

        temp.setLocation(this.screenWidth/2 - this.screenWidth/12, this.screenHeight/2 - this.screenHeight/12);

        temp.setVisible(true);

        JPanel tempPanel = new JPanel(new BorderLayout());

        temp.add(tempPanel);

        temp.setTitle("Confirmation Popup");

        tempPanel.add(new Label(message + ", exit window to close"), BorderLayout.CENTER);

        temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
