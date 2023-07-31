package Pages;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import Backend.Date;
import Essentials.*;
import Essentials.Button;

// For .pdf
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Company_HomeScreen {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    protected ArrayList<Backend.Car> array = new ArrayList<>();
    public static Boolean isReport = false;
    // Side Buttons
    private Button Stats, Products, Modify, Logout, Pic;

    String pic_address = "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\CarGo-Light-PNG.png";
    // Argumented Constructor
    public Company_HomeScreen(int width, int height, String title, String location, String text, String Name) {

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

        // Adding Text Statistics
        Text stats = new Text(this.frame, 350, 30, 300, 100, "Statistics", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        stats.setInvertColor();

        // _________________________________ //
        // Panel Report
        TextPanel report = new TextPanel(frame, 1100, 60, 70, 40,"Report", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 14);
        //report.setRightAlignment();

        // Download Button
        Button Download = new Button(false);
        frame.add(Download.iconButton(1170, 60, 40, 40, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\download.png"));
        Download.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\download.png", 12, 19);

        // _________________________________ //

        // Side Buttons
        int start = 144;


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
        new Picture(this.frame,  this.pic_address, 170, 42, 60, 60);
        new Text(this.frame, 30, 26, 140, 60, "Welcome,", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 18f);
        new Text(this.frame, 30, 55, 180, 60, Name, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 30f);

        // Add Company Picture
        Pic = new Button(false);
        frame.add(Pic.newButton(230, 42, 60, 60, "+", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Regular.ttf", 45f));

        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 144);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);

        //frame.add(homeLogo.setLogo());


        // Current Stick-Bar Indicating what page we currently on
        JPanel stick = new JPanel();
        stick.setBounds(0, start, 7, 144);
        stick.setBackground(new Color(Main.bg, Main.bg, Main.bg));
        stick.setLayout(new BorderLayout(40, 10));
        frame.add(stick);

        Stats = new Button(false);
        frame.add(Stats.newButton(0, start+1, 300, 144, "Statistics", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        Stats.setEnabled(false);
        //Home.setInvert();

        Products = new Button(false);
        frame.add(Products.newButton(0, start*2+2, 300, 144, "Products", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Modify = new Button(false);
        frame.add(Modify.newButton(0, start*3+3, 300, 144, "Modify", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Logout = new Button(false);
        frame.add(Logout.newButton(0, start*4+4, 300, 144, "Logout", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));



        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();


            int c_id = 0;
            ResultSet rs2 = stmt.executeQuery("Select id from manufacturer where cnic = '" + Static.CNIC + "'");

            while (rs2.next()){
                c_id = rs2.getInt(1);
            }

            System.out.println(c_id);
            // "select car.price,car.car_id,car.name,booking.booking_date, name.f_name, name.l_name from (booking natural join car) cross join stats natural join customer natural join name where company = '" + Static.current_user + "' and booking.id = stats.booking_id and car.car_id = booking.car and booking.owner = customer.id"
            ResultSet rs = stmt.executeQuery("select car.price,car.car_id,car.name,booking.booking_date, name.f_name, name.l_name from (booking natural join car) cross join stats natural join customer natural join name where company = '" + Static.current_user + "' and booking.id = stats.booking_id and car.car_id = booking.car and booking.owner = customer.id");
//    public Car(int car_price, String car_id, String car_name, Date purchase_date) {
//
            while (rs.next()){
                String d = rs.getString(4).substring(0,10);
                System.out.println(d);
                String[] da = d.split("-");

                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + Integer.parseInt(da[2]) + Integer.parseInt(da[1]) + Integer.parseInt(da[0]) + rs.getString(5) +" "+ rs.getString(6));

                array.add(new Backend.Car(rs.getInt(1),rs.getString(2),rs.getString(3),new Date(Integer.parseInt(da[2]),Integer.parseInt(da[1]),Integer.parseInt(da[0])),rs.getString(5) +" "+ rs.getString(6)));
            }
        }
        catch (Exception e){
            System.out.println("Error  company Stats ....");
        }

        int t_price = 0;

        // Stats
        int y = 0;
        for (int i = 1; i <= array.size(); i++) {
            new Statistics(frame, 140 + y, array.get(i-1).getCar_id(), array.get(i-1).getCar_name(), "$"+array.get(i-1).getCar_price(), array.get(i-1).getPurchase_date().getDay()+"-"+array.get(i-1).getPurchase_date().getMonth()+"-"+array.get(i-1).getPurchase_date().getYear());
            t_price += array.get(i-1).getCar_price();
            y = y + 105;
        }
        // 1140, 580, 70, 70
        new TextPanel(frame, 1140, 580, 70, 70,"$", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 48f);
        TextPanel total_price = new TextPanel(frame, 780, 580, 360, 70,"+ $" + t_price, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 48);
        total_price.setTransparent();
        total_price.setRightAlignment();



        if(isReport && array.size() != 0){
            System.out.println("?>>>");
            try{

                // Create a new PDF document
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Seller\\"+Name+"_Sell_Report.pdf"));
                document.open();

                // Create a .pdf Table
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);

                // Add table headers
                table.addCell("Car ID");
                table.addCell("Car Name");
                table.addCell("Car Price");
                table.addCell("Purchase Date");
                table.addCell("Customer Name");

                // Get Current Date of Today
                LocalDate currentDate = LocalDate.now();

                for (int i = 0; i < array.size() ; i++) {

                    // Add elements into the table
                    table.addCell(array.get(i).getCar_id());
                    table.addCell(array.get(i).getCar_name());
                    table.addCell(array.get(i).getCar_price() + "");

                    table.addCell(array.get(i).getPurchase_date().getDay() + "-" + array.get(i).getPurchase_date().getMonth() + "-" + array.get(i).getPurchase_date().getYear() + "");
                    table.addCell(array.get(i).getFull_name());
                }
                // Close the document
                String[] cD = currentDate.toString().split("-");
                document.add(new Paragraph("Company: " + Name + "              " + "Today's Date: "  + (cD[2] + "-" + cD[1] + "-" + cD[0]) + "\n\n"));
                document.add(table);
                document.add(new Paragraph("\nTotal Sell Profit: " + (t_price + "")));

                document.close();
                writer.close();


                File report_file = new File("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Seller\\"+Name+"_Sell_Report.pdf");
                Desktop desktop = Desktop.getDesktop();
                if(report_file.exists()){         //checks file exists or not
                    desktop.open(report_file);              //opens the specified file
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (isReport && array.size() == 0){
            JOptionPane.showMessageDialog(new JFrame(), "No Cars Sold Yet!");
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
}
