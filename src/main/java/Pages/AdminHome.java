package Pages;

import java.awt.*;
import javax.swing.*;

import Essentials.Text;
//import Essentials.TextPanel;
import Essentials.Button;
//import Essentials.Companies;


public class AdminHome {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;

    // Argumented Constructor
    public AdminHome(int width, int height, String title, String location, String text, String Name) {

        this.frame = new JFrame();

        // Essentials for menu display
        this.frame.setSize(width, height);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setBackground(new Color(Main.bg, Main.bg, Main.bg));

        // Title and E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo
        this.image = new ImageIcon(location);
        this.frame.setIconImage(image.getImage());
        this.frame.setTitle(title);


        // Adding Text Login
        Text login = new Text(this.frame, 350, 30, 300, 100, "Home", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        login.setInvertColor();

        // Logout Button
        Button logout = new Button(false);
        frame.add(logout.iconButton(216, 35, 50, 50, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\arrow-left.png"));
        logout.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\logout.png", 24, 27);
        logout.setInvert();


        new Text(this.frame, 121, 331, 80, 30, "Admin", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 22f);


        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 768);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);

        // Users
        Text Users = new Text(this.frame, 350, 140-45, 400, 150, "Users", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 50f);
        Users.setInvertColor();
        Button usersButton = new Button(false);
        frame.add(usersButton.newButton(350, 211, 350, 88, "+ View All Users", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
        usersButton.setLightColor();

        // Companies
        Text Companies = new Text(this.frame, 350, 318-45, 400, 150, "Companies", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 50f);
        Companies.setInvertColor();
        Button compButton = new Button(false);
        frame.add(compButton.newButton(350, 390, 350, 88, "+ View All Companies", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
        compButton.setLightColor();
    }

    public void dispose() {
        this.frame.dispose();
    }

    // Must call Method to display
    public void setVisible(boolean set) {
        this.frame.setVisible(set);
    }

    // Getters
    public JFrame getFrame() {
        return this.frame;
    }
}
