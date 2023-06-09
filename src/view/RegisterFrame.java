package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class RegisterFrame extends MyFrame {
    JTextField userNameTextField;
    JPasswordField userPasswordTextField;
    JLabel title;
    LoginFrame loginFrame;
    JButton backButton;
    JButton registerButton;

    public RegisterFrame() {
        super(498, 280);

        addUserText();
        addPasswordText();
        addTitle();
        addBackButton();
        addRegisterButton();

        this.setBackground("resource/registerframe.gif");
    }

    private void addRegisterButton() {
        this.registerButton = new HomeButton("Register", 40, 200);
        registerButton.addActionListener((e) -> {
            String inputName = userNameTextField.getText();
            char[] pw1 = userPasswordTextField.getPassword();
            String inputPW = new String(pw1);
            for (User user : loginFrame.users) {
                if (Objects.equals(inputName, user.getName())) {
                    JOptionPane.showMessageDialog(null, "User has already exist.", "user error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter("user/users", true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        fileWriter.write(inputName + " " + inputPW + " 0\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        fileWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, "Register succeed!", "Jungle", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.users.add(new User(inputName, inputPW, 0));
                    this.setVisible(false);
                }
            }
        });
        add(registerButton);
    }

    private void addBackButton() {
        this.backButton = new HomeButton("Back", 260, 200);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
        });
        add(backButton);
    }

    private void addUserText() {
        this.userNameTextField = new JTextField();
        JLabel info = new JLabel("Name:");
        info.setSize(120, 30);
        info.setLocation(20, 100);
        info.setForeground(new Color(255, 235, 205));
        info.setFont(new Font("Arial", Font.BOLD, 30));
        info.setBackground(new Color(56, 94, 15, 100));
        userNameTextField.setText("please enter your username");
        userNameTextField.setBounds(120, 100, 330, 30);
        add(info);
        add(userNameTextField);
    }

    private void addPasswordText() {
        this.userPasswordTextField = new JPasswordField();
        JLabel info = new JLabel("PW:");
        info.setSize(120, 30);
        info.setLocation(20, 150);
        info.setForeground(new Color(255, 235, 205));
        info.setFont(new Font("Arial", Font.BOLD, 30));
        info.setBackground(new Color(56, 94, 15, 100));
        userPasswordTextField.setBounds(120, 150, 330, 30);
        add(info);
        add(userPasswordTextField);
    }

    private void addTitle() {
        this.title = new JLabel("REGISTER");
        title.setFont(new Font("Rockwell", Font.BOLD, 50));
        title.setForeground(new Color(128, 42, 42));
        title.setLocation(120, 20);
        title.setSize(500, 50);
        add(title);
    }

}
