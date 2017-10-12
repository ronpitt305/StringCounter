package gui;


import stringcounter.StringClass;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainGui extends JFrame {

    private File[] fileLog;
    StringClass stringClass = new StringClass();

    public MainGui(){
        init();
    }

    private void init(){

        fileLog = new File[1];

        JTextArea fileLabel = new JTextArea("File:");
        fileLabel.setEditable(false);
        JTextArea countLabel = new JTextArea("String Count");
        countLabel.setEditable(false);

        JButton uploadButton = new JButton("Upload");
        JButton countButton = new JButton("Count");
        JTextArea fileSelect = new JTextArea();
        fileSelect.setSize(450, 50);
        fileSelect.setEditable(false);

        JTextArea textBar = new JTextArea();
        textBar.setSize(450, 50);
        textBar.setEditable(false);

        Box box = Box.createVerticalBox();
        box.add(fileLabel);
        box.add(fileSelect);
        box.add(countLabel);
        box.add(textBar);
        add(box, BorderLayout.NORTH);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(uploadButton);
        buttonBox.add(countButton);
        add(buttonBox, BorderLayout.SOUTH);

        setSize(450, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Select a file");

                int returnValue = fc.showOpenDialog(MainGui.this);
                if (returnValue == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    fileLog[0] = file;
                    fileSelect.setText(fileLog[0].getName());

                } else if (returnValue == JFileChooser.CANCEL_OPTION){
                    JOptionPane jOptionPane = new JOptionPane();
                    jOptionPane.createDialog("No File was Selected");
                    System.out.println("No file was selected");
                }
            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int count;

                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileLog[0]));
                    System.out.println("The file selected was " + fileLog[0]);

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = br.readLine()) != null){
                        sb.append(line);
                        sb.append(System.lineSeparator());

                    }
                    System.out.println("StringBuilder is: " + sb.toString());


                    count = stringClass.isWord(sb.toString());
                    String countString = String.format("The word count is %n", count);
                    textBar.setText(Integer.toString(count));

                    System.out.println("Count " + count);


                } catch (IOException ioe){
                    ioe.printStackTrace();
                }

            }
        });

    }
}
