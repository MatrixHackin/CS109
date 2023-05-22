package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CreateFileFrame extends MyFrame {
    JTextField fileNameTF;
    JButton createFileBtn;
    GameFrame gameFrame;
    JLabel title;
    String path;

    public CreateFileFrame() {
        super(498, 280);

        addFileTF();
        addCreateButton();
        addTitle();
        this.setBackground("resource/registerframe.gif");
    }
    public String getPath(){
        return path;
    }

    private void addTitle() {
        this.title = new JLabel("Save");
        title.setFont(new Font("Rockwell", Font.BOLD, 50));
        title.setForeground(new Color(128, 42, 42));
        title.setLocation(180, 20);
        title.setSize(500, 50);
        add(title);
    }

    private void addFileTF() {
        fileNameTF = new JTextField();
        fileNameTF.setText("please enter file name end with .ser");
        fileNameTF.setBounds(45, 120, 400, 30);
        add(fileNameTF);
    }

    private void addCreateButton() {
        createFileBtn = new HomeButton("Create", 150, 200);
        createFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameTF.getText();
                if (fileName.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateFileFrame.this, "Please enter a file name");
                    return;
                }
                File file = new File("board", fileName);
                try {
                    if (file.createNewFile()) {
                        JOptionPane.showMessageDialog(CreateFileFrame.this, "File created successfully");
                        path=String.format("board/"+fileName);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(CreateFileFrame.this, "File already exists");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(CreateFileFrame.this, "Error while creating file");
                }
            }
        });
        add(createFileBtn);
    }
}

