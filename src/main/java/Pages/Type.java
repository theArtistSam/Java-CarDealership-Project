package Pages;

import java.awt.*;
import javax.swing.*;

import Essentials.Text;
import Essentials.TypeBar;
import Essentials.Button;
import Essentials.Picture;

public class Type {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    
    // Side Buttons
    private Button Dashboard, Store, Booking, Back;
    
    // Argumented Constructor
    public Type(int width, int height, String title, String location, String text, String Name) {

        this.frame = new JFrame();

        // Essentials for menu display
        this.frame.setSize(width, height);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setBackground(new Color(Main.bg, Main.bg, Main.bg));

        // Title and Logo
        this.image = new ImageIcon(location);
        this.frame.setIconImage(image.getImage());
        this.frame.setTitle(title);

        // side Panels
        //panels(this.frame, 350, 50, 150, 60, 249, 165, 20, text, "C\:\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 18f);
        //panelInvert(this.frame, 10, 83, 280, 60, 255, 248, 238, name, "C\:\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 18f);

        // Side Buttons
        int start = 144;
        
        // Adding Text Type
        Text Type = new Text(this.frame, 350, 30, 400, 100, text, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        Type.setInvertColor();
        
        //Adding Logo to the Top
        new Picture(this.frame, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\CarGo-Light-PNG.png", 200, 42, 60, 60);
        new Text(this.frame, 40, 26, 140, 60, "Welcome,", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 18f);
        new Text(this.frame, 40, 55, 180, 60, Name, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 30f);
        
        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 144);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);

        //frame.add(homeLogo.setLogo());

        // Current Stick-Bar Indicating what page we currently on
        /* JPanel stick = new JPanel();
        stick.setBounds(0, start*2+2, 7, 144);
        stick.setBackground(new Color(Main.bg, Main.bg, Main.bg));
        stick.setLayout(new BorderLayout(40, 10));
        frame.add(stick); */

        Dashboard = new Button(false);
        frame.add(Dashboard.newButton(0, start+1, 300, 144, "Dashboard", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        
        Store = new Button(false);
        frame.add(Store.newButton(0, start*2+2, 300, 144, "Store", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        //Store.setEnabled(false);
        
        Booking = new Button(false);
        frame.add(Booking.newButton(0, start*3+3, 300, 144, "Booking","E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        
        Back = new Button(false);
        frame.add(Back.newButton(0, start*4+4, 300, 144, "Back","E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        
        new TypeBar(frame, 350, 140, "Tesla Model X.png", "SUV");
        new TypeBar(frame, 789, 140, "Lamborghirni Avendator.png", "Sports");
        new TypeBar(frame, 350, 314, "bmw m5.png", "Sedan");
        new TypeBar(frame, 789, 314, "hundai tucson.png", "Jeep");
        new TypeBar(frame, 350, 488, "toyota land-cruiser.png", "Hatch Back");

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
