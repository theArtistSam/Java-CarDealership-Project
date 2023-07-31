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

public class ListCars {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    protected Textfield searchField;
    protected ArrayList<Backend.Car> array = new ArrayList<Backend.Car>();
    private String CarText;
    public static String carName;
    public static Boolean searchCheck = false;
    // Side Buttons
    private Button Dashboard, Store, Booking, Back, SearchOrCancel;

    // Argumented Constructor
    public ListCars(int width, int height, String title, String location, String CarText, String Name) {
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
        Text TypeText = new Text(this.frame, 350, 30, 400, 100, car_name[0], "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        TypeText.setInvertColor();

        // _________________________________ //


        // Search text field
        this.searchField = new Textfield();
        System.out.println(CarText);

        if (searchCheck){
            System.out.println(car_name[1]);
            this.frame.add(searchField.textField(1032, 60, 138, 40, car_name[1]));
            this.searchField.setFontSize(14f);
            this.searchField.setInvert();
            //this.searchField.setTextColor(184, 184, 184);
            this.searchField.disable();

        }
        else{
            this.frame.add(searchField.textField(1032, 60, 138, 40, "Search Cars..." ));
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

        //Adding Logo to the Top
        new Picture(this.frame, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\CarGo-Light-PNG.png", 200, 42, 60, 60);
        new Text(this.frame, 40, 26, 140, 60, "Welcome,", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 18f);
        new Text(this.frame, 40, 55, 180, 60, Name, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 30f);

        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 144);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);


        Dashboard = new Button(false);
        frame.add(Dashboard.newButton(0, start+1, 300, 144, "Dashboard", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Store = new Button(false);
        frame.add(Store.newButton(0, start*2+2, 300, 144, "Store", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Booking = new Button(false);
        frame.add(Booking.newButton(0, start*3+3, 300, 144, "Booking", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Back = new Button(false);
        frame.add(Back.newButton(0, start*4+4, 300, 144, "Back", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();

            if (CarText.contains("Sports")){
                System.out.println(">>Here!");
                System.out.println(searchField.getText());
                if (searchCheck){
                    System.out.println("<<>Here!");

                    ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SP%' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                    System.out.println(searchField.getText());

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
                else{
                    ResultSet rs = stmt.executeQuery("SELECT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SP%'");

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
            }
            else if (CarText.contains("SUV")){
                System.out.println(">>Here!");
                System.out.println(searchField.getText());
                if (searchCheck){
                    System.out.println("<<>Here!");

                    ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SV%' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                    System.out.println(searchField.getText());

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
                else{
                    ResultSet rs = stmt.executeQuery("SELECT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SV%'");

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
            }
            else if (CarText.contains("Sedan")){
                System.out.println(">>Here!");
                System.out.println(searchField.getText());
                if (searchCheck){
                    System.out.println("<<>Here!");

                    ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SD%' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                    System.out.println(searchField.getText());

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
                else{
                    ResultSet rs = stmt.executeQuery("SELECT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'SD%'");

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
            }
            else if (CarText.contains("Hatch Back")){
                System.out.println(">>Here!");
                System.out.println(searchField.getText());
                if (searchCheck){
                    System.out.println("<<>Here!");

                    ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'HB%' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                    System.out.println(searchField.getText());

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
                else{
                    ResultSet rs = stmt.executeQuery("SELECT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'HB%'");

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
            }
            if (CarText.contains("Jeep")){
                System.out.println(">>Here!");
                System.out.println(searchField.getText());
                if (searchCheck){
                    System.out.println("<<>Here!");

                    ResultSet rs = stmt.executeQuery("SELECT DISTINCT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'JP%' and UPPER(car.name) like UPPER('%" + searchField.getText() + "%')");

                    System.out.println(searchField.getText());

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
                else{
                    ResultSet rs = stmt.executeQuery("SELECT * from car cross join picture where car.picture_id = picture.picture_id and company = '" + Static.current_company + "' and car_id like 'JP%'");

                    while (rs.next()){
                        if (rs.getInt(6) != 0){
                            System.out.println(rs.getInt(6));
                            array.add(new Backend.Car(rs.getString(2),new Backend.Picture(rs.getInt(8),rs.getString(11),rs.getString(10))));
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Error <List Cars>....");
        }

        if (array.size() == 0){
            Text sorry = new Text(this.frame, 590, 350, 400, 40, "Adding New Products Soon : )", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 25f);
            sorry.setInvertColor();

        }
        // This can certainly get fixed
        int start_x = 0; // 140
        int start_y = 0; // 267

        System.out.println("Array size is: " + array.size());
        for(int i = 1; i <= array.size(); i++){
            if (i % 2 == 0){
                new DisplayCars(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCar_picture().getPicture_address(), array.get(i-1).getCar_name());
                start_x = 0;
                start_y = start_y + 268;
            }
            else{
                new DisplayCars(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCar_picture().getPicture_address(), array.get(i-1).getCar_name());
                start_x = start_x + 440;
            }
        }
    }
    public void dispose() {
        this.frame.dispose();
    }

    public String getCarText() {
        return CarText;
    }

    // search field text
    public String getSearchFieldText() {
        return searchField.getText();
    }

    public void setSearchFieldText(String text) {
        this.searchField.setText(text);
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
