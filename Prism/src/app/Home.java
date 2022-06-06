package app;

import logic.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.*;

public class Home extends JFrame {

    // Banner and Logo
    protected static int bannerHeight = 923;
    protected static int bannerWidth = 497;
    protected static int bannerPreferedHeight = bannerHeight - bannerHeight/2 - bannerHeight/8;
    protected static int bannerPreferedWidth = bannerWidth*bannerPreferedHeight/bannerHeight; // X/Y = Px/Py   Px = X*Py/Y
    protected static int shortLogoHeight = 361;
    protected static int shortLogoWidth = 1166;
    protected static int shortLogoPreferedWidth = shortLogoWidth - shortLogoWidth/2 - shortLogoWidth/6;
    protected static int shortLogoPreferedHeight = shortLogoHeight*shortLogoPreferedWidth/shortLogoWidth; // Y/X = Py/Px   Py = Y*Px/X
    protected static int appHeight = bannerPreferedHeight;
    protected static int appWidth = shortLogoPreferedWidth + bannerPreferedWidth;
    protected static ImageIcon banner = new ImageIcon(new ImageIcon("data/images/banner.jpg").getImage().getScaledInstance(bannerPreferedWidth, bannerPreferedHeight, Image.SCALE_SMOOTH));
    protected static ImageIcon shortLogo = new ImageIcon(new ImageIcon("data/images/short.jpg").getImage().getScaledInstance(shortLogoPreferedWidth, shortLogoPreferedHeight, Image.SCALE_SMOOTH));
    
    // Home Page
    protected static Image icon = new ImageIcon("data/images/icon.png").getImage();
    protected static Database database = new Database();

    // User
    protected static ImageIcon userIconTemp = new ImageIcon("data/images/userPic.png");
    protected User user = new User("Inori Yuzuriha","inori@uniandes.edu.co");
    protected static int iconHeight = userIconTemp.getIconHeight();
    protected static int iconWidth = userIconTemp.getIconWidth();
    protected static int iconPreferedHeight = iconHeight - iconHeight/2;
    protected static int iconPreferedWidth = iconWidth*iconPreferedHeight/iconHeight; // X/Y = Px/Py   Px = X*Py/Y
    protected ImageIcon userIcon = new ImageIcon(userIconTemp.getImage().getScaledInstance(iconPreferedWidth, iconPreferedHeight, Image.SCALE_SMOOTH));

    // Buttons
    protected static JButton buttonProjects;
    protected static JButton buttonStatistics;

    public Home() {
        setTitle("Prism Home");
        setIconImage(icon);
        setLayout(new BorderLayout());
        JLabel shortLogoLabel = new JLabel(shortLogo);
        JLabel bannerLabel = new JLabel(banner);
        bannerLabel.setVerticalAlignment(JLabel.NORTH);
        add(shortLogoLabel, BorderLayout.NORTH);
        add(bannerLabel, BorderLayout.EAST);
        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
            // Center
        centerPanel.add(userPanel(),BorderLayout.CENTER);
            // Bottom
        JPanel bottomPanelC = new JPanel();
        bottomPanelC.setLayout(new BorderLayout(10,25));
        buttonProjects = new JButton("Projects");
        buttonStatistics = new JButton("Statistics");
        buttonProjects.setVerticalAlignment(JButton.NORTH);
        buttonStatistics.setVerticalAlignment(JButton.NORTH);
        buttonProjects.setHorizontalAlignment(JButton.CENTER);
        buttonStatistics.setHorizontalAlignment(JButton.CENTER);
        buttonProjects.setPreferredSize(new Dimension(150,25));
        buttonStatistics.setPreferredSize(new Dimension(25,25));
        bottomPanelC.add(buttonProjects,BorderLayout.NORTH);
        bottomPanelC.add(buttonStatistics,BorderLayout.CENTER);
        centerPanel.add(bottomPanelC,BorderLayout.SOUTH);
        add(centerPanel,BorderLayout.CENTER);
        // BUTTONS ACTION LISTENERS
        buttonProjects.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                User.saveUsers();
                Projects projects = new Projects();
                setVisible(false);
            }
        });
        buttonStatistics.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){}
        });
    }

    protected JPanel userPanel() {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBackground(Color.WHITE);
        JLabel userIconLabel = new JLabel(userIcon);
        JLabel userNameLabel = new JLabel("@" + user.getUsername());
        JLabel userEmailLabel = new JLabel(user.getEmail());
        userIconLabel.setHorizontalAlignment(JLabel.CENTER);
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        userEmailLabel.setHorizontalAlignment(JLabel.CENTER);
        userIconLabel.setVerticalAlignment(JLabel.NORTH);
        userNameLabel.setVerticalAlignment(JLabel.NORTH);
        userEmailLabel.setVerticalAlignment(JLabel.NORTH);
        userPanel.add(userIconLabel, BorderLayout.NORTH);
        userPanel.add(userNameLabel, BorderLayout.CENTER);
        userPanel.add(userEmailLabel, BorderLayout.SOUTH);
        return userPanel;
    }

    public static void run() {
        Home home = new Home();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(appWidth, appHeight);
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.setBackground(new Color(255,255,255,255));
        home.pack();
        home.setVisible(true);
    }
}
