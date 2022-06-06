package app;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.*;

public class Start extends JFrame{

    protected static Image icon = new ImageIcon("data/images/icon.png").getImage();
    // Banner and Logo
    protected static int shortLogoHeight = 361;
    protected static int shortLogoWidth = 1166;
    protected static int shortLogoPreferedWidth = shortLogoWidth - shortLogoWidth/2 - shortLogoWidth/6;
    protected static int shortLogoPreferedHeight = shortLogoHeight*shortLogoPreferedWidth/shortLogoWidth; // Y/X = Py/Px   Py = Y*Px/X
    protected static ImageIcon shortLogo = new ImageIcon(new ImageIcon("data/images/short.jpg").getImage().getScaledInstance(shortLogoPreferedWidth, shortLogoPreferedHeight, Image.SCALE_SMOOTH));

    public Start() {
        setTitle("Prism");
        setIconImage(icon);
        setLayout(new BorderLayout());
        JLabel shortLogoLabel = new JLabel(shortLogo);
        add(shortLogoLabel, BorderLayout.NORTH);
        // Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JTextField username = new JTextField("Username");
        JTextField email = new JTextField("unknown@uniandes.edu.co");
        centerPanel.add(username, BorderLayout.NORTH);
        centerPanel.add(email, BorderLayout.CENTER);
        // Bottom
        JButton signin = new JButton("Get Access");
        centerPanel.add(signin, BorderLayout.SOUTH);
        signin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new User(username.getText(),email.getText());
                dispose();
                Home.run();
            }
        });
        add(centerPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        User.loadUsers();
        Project.loadProjects();
        new Start();
    }
}
