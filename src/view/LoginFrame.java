package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginFrame extends MyFrame {
    boolean isLogin;
    User user=new User("Visitor","1",0);
    BeginFrame beginFrame;
    AIFrame aiFrame;
    JButton backButton;
    JButton registerButton;
    JButton loginButton;
    JButton visitorButton;
    JLabel title;
    ArrayList<User> users;
    JTextField userNameTextField;
    JPasswordField userPasswordTextField;
    RegisterFrame registerFrame;

    public LoginFrame() throws FileNotFoundException {
        super(480,640);

        //读取用户信息
        this.users= new ArrayList<>();
        File file=new File("user/users");
        Scanner scanner=new Scanner(file);
        while (scanner.hasNextLine()){
            String line= scanner.nextLine();
            String[] parts =line.split(" ");
            String name=parts[0];
            String password=parts[1];
            String score=parts[2];
            int scores=Integer.parseInt(score);
            User user1=new User(name,password,scores);
            users.add(user1);
        }
        scanner.close();

        addBackButton();
        addTitle();
        addUserText();
        addPasswordText();
        addLoginButton();
        addVisitorButton();
        addRegisterButton();

        RegisterFrame registerFrame=new RegisterFrame();
        this.registerFrame=registerFrame;
        registerFrame.loginFrame=this;

        AIFrame aiFrame=new AIFrame();
        this.aiFrame=aiFrame;
        aiFrame.loginFrame=this;

        this.setBackground("resource/8E4.gif");
    }
    private void addBackButton(){
        this.backButton=new HomeButton("Back",150,550);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(backButton);
    }
    private void addTitle(){
        this.title=new JLabel("LOGIN");
        title.setFont(new Font("Rockwell",Font.BOLD,100));
        title.setForeground(new Color(128,42,42));
        title.setLocation(75,0);
        title.setSize(500,150);
        add(title);
    }
    private void addUserText(){
        this.userNameTextField=new JTextField();
        JLabel info=new JLabel("Name:");
        info.setSize(120,30);
        info.setLocation(20,150);
        info.setForeground(new Color(255,235,205));
        info.setFont(new Font("Arial",Font.BOLD,30));
        info.setBackground(new Color(56,94,15,100));
        userNameTextField.setText("please enter your username");
        userNameTextField.setBounds(120,150,330,30);
        add(info);
        add(userNameTextField);
    }
    private void addPasswordText(){
        this.userPasswordTextField=new JPasswordField();
        JLabel info=new JLabel("PW:");
        info.setSize(120,30);
        info.setLocation(20,200);
        info.setForeground(new Color(255,235,205));
        info.setFont(new Font("Arial",Font.BOLD,30));
        info.setBackground(new Color(56,94,15,100));
        userPasswordTextField.setBounds(120,200,330,30);
        add(info);
        add(userPasswordTextField);
    }
    private void addLoginButton(){
     this.loginButton=new HomeButton("Login",150,250);
     loginButton.addActionListener((e)->{
        login();
        if(isLogin){
            this.setVisible(false);
            aiFrame.setVisible(true);
        }
     });
     add(loginButton);
    }
    private void addRegisterButton(){
        this.registerButton=new HomeButton("Register",150,350);
        registerButton.addActionListener((e)->{
            this.registerFrame.setVisible(true);
        });
        add(registerButton);
    }
    private void addVisitorButton(){
        this.visitorButton=new HomeButton("Visitor",150,450);

        add(visitorButton);
    }
    private void login(){
        HashMap<String, String> nameAndPW = new HashMap<>();
        HashMap<String, User> nameAndUser = new HashMap<>();
        if (users.size() != 0){
            for (User user : users) {
                nameAndPW.put(user.getName(), user.getPassword());
            }
            for (User user : users) {
                nameAndUser.put(user.getName(), user);
            }
        }

        String inputName = userNameTextField.getText();
        char[] inputPW1 = userPasswordTextField.getPassword();
        String inputPW = new String(inputPW1);
        if (inputName.equals("")){
            JOptionPane.showMessageDialog(null, "Please input ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (inputPW1.length == 0){
            JOptionPane.showMessageDialog(null, "Please input password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nameAndPW.containsKey(inputName)){  // 包含此用户
            if (inputPW.equals(nameAndPW.get(inputName))){  // 密码正确
                setVisible(false);
                this.isLogin  = true;
                user = nameAndUser.get(inputName);
                aiFrame.setUser(user);
                aiFrame.addWelcomeLabel();
                this.setVisible(false);
            } else {  // 密码错误
                JOptionPane.showMessageDialog(null, "Wrong password !", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {  // 没有此用户
            JOptionPane.showMessageDialog(null, "No such User", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
