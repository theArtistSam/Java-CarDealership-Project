package Pages;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

import Essentials.*;
import Essentials.Button;

public class ListProducts {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    protected Textfield searchField;
    private String CarText;
    protected ArrayList<Backend.Car> array = new ArrayList<Backend.Car>();
    public static Boolean searchCheck = false;
    // Side Buttons
    private Button Stats, Products, Modify, Back, SearchOrCancel;

    String pic_address = "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\CarGo-Light-PNG.png";


    // Argumented Constructor
    public ListProducts(int width, int height, String title, String location, String CarText, String Name) {
        String[] car_name;
        if(CarText.contains("-")){
            car_name = CarText.split("-");
        }
        else{

            car_name = new String[1];
            car_name[0] = CarText;
            this.CarText = CarText;
        }

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


        // Side Buttons
        int start = 144;

        // Adding TypeText
        Text TypeText = new Text(this.frame, 350, 30, 300, 100, car_name[0], "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        TypeText.setInvertColor();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();

            ResultSet pic  = stmt.executeQuery("SELECT address from picture WHERE picture_id = (select picture_id from manufacturer where company_name = '" + Static.current_user + "')");
            while (pic.next()){
                pic_address = pic.getString(1);
            }
        }
        catch (Exception e2){
            System.out.println("Error <Add Picture - Company Home Screen> .................");
        }

        //Adding Logo to the Top
        new Picture(this.frame, pic_address, 200, 42, 60, 60);
        new Text(this.frame, 40, 26, 140, 60, "Welcome,", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 18f);
        new Text(this.frame, 40, 55, 180, 60, Name, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 30f);

        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 144);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);


        // Current Stick-Bar Indicating what page we currently on
        JPanel stick = new JPanel();
        stick.setBounds(0, start*2+2, 7, 144);
        stick.setBackground(new Color(Main.bg, Main.bg, Main.bg));
        stick.setLayout(new BorderLayout(40, 10));
        frame.add(stick);

        // _________________________________ //


        // Search text field
        this.searchField = new Textfield();

        if (searchCheck){
            System.out.println(car_name[1]);
            this.frame.add(searchField.textField(1032, 60, 138, 40, car_name[1]));
            this.searchField.setFontSize(14f);
            this.searchField.setInvert();
            //this.searchField.setTextColor(184, 184, 184);
            this.searchField.disable();

        }
        else{
            this.frame.add(searchField.textField(1032, 60, 138, 40, "Search Prod..." ));
            this.searchField.setFontSize(14f);
            this.searchField.setInvert();
            this.searchField.setTextColor(184, 184, 184);
        }


        // Search Button or Cancel Button
        this.SearchOrCancel = new Button(false);
        if (searchCheck){
            frame.add(SearchOrCancel.iconButton(1170, 60, 40, 40, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\cancel.png"));
            SearchOrCancel.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\cancel.png", 18, 18);
            SearchOrCancel.setText("C");
            System.out.println(SearchOrCancel.getText());
        }
        else{
            frame.add(SearchOrCancel.iconButton(1170, 60, 40, 40, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\search.png"));
            SearchOrCancel.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\search.png", 19, 19);
            //SearchOrCancel.setText("S");
            System.out.println(SearchOrCancel.getText());
        }

        // _________________________________ //

        Stats = new Button(false);
        frame.add(Stats.newButton(0, start+1, 300, 144, "Statistics", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Products = new Button(false);
        frame.add(Products.newButton(0, start*2+2, 300, 144, "Products", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        Products.setEnabled(false);

        Modify = new Button(false);
        frame.add(Modify.newButton(0, start*3+3, 300, 144, "Modify", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Back = new Button(false);
        frame.add(Back.newButton(0, start*4+4, 300, 144, "Back", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();

            String company = Static.current_user;

            if (searchCheck){

                ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM car CROSS JOIN Picture where CAR.picture_id = picture.picture_id and car.company = '" + company + "' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                while (rs.next()){
                    array.add(new Backend.Car(Integer.parseInt(rs.getString(7)),Integer.parseInt(rs.getString(6)),0,rs.getString(1),rs.getString(5),rs.getString(4),rs.getString(2),null,true,new Backend.Picture(Integer.parseInt(rs.getString(9)),rs.getString(11),rs.getString(10))));
                }
            }
            else{

                ResultSet rs = stmt.executeQuery("SELECT * FROM car CROSS JOIN Picture where CAR.picture_id = picture.picture_id and car.company = '" + company + "'");

                while (rs.next()){
                    array.add(new Backend.Car(Integer.parseInt(rs.getString(7)),Integer.parseInt(rs.getString(6)),0,rs.getString(1),rs.getString(5),rs.getString(4),rs.getString(2),null,true,new Backend.Picture(Integer.parseInt(rs.getString(9)),rs.getString(11),rs.getString(10))));
                }
            }
        }
        catch (Exception e){
            System.out.println("Error  List Products....");
        }


        if (array.size() == 0){
            Text sorry = new Text(this.frame, 670, 350, 250, 40, "No Products Exist!", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 25f);
            sorry.setInvertColor();
        }

        // This can certainly get fixed
        int start_x = 0; // 140
        int start_y = 0; // 267

        for(int i = 1; i <= array.size(); i++){
            if (i % 2 == 0){
                new CarProducts(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCar_picture().getPicture_address(), array.get(i-1).getCar_name());
                start_x = 0;
                start_y = start_y + 268;
            }
            else{
                new CarProducts(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCar_picture().getPicture_address(), array.get(i-1).getCar_name());
                start_x = start_x + 440;
            }
        }
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


    public String getCarText() {
        return CarText;
    }

    // search field text
    public String getSearchFieldText() {
        return searchField.getText();
    }

}
