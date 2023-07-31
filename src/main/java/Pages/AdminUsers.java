package Pages;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import Backend.Manufacturer;
import Backend.Name;
import Backend.Person;
import Essentials.Text;
//import Essentials.TextPanel;
import Essentials.Button;
import Essentials.TextPanel;
import Essentials.Textfield;

// For .pdf
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class AdminUsers {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    private ArrayList<Person> c_array = new ArrayList<>();
    private ArrayList<Manufacturer> m_array = new ArrayList<>();
    private Text Admin;
    private String entity;
    protected Textfield searchField;
    public static Boolean searchCheck = false;
    private Button SearchOrCancel;
    public static Boolean isReport = false;
    private Button Download;

    // Argumented Constructor
    public AdminUsers(int width, int height, String title, String location, String entity, String Name) {

        String[] user_name;
        if(entity.contains(">")){
            user_name = entity.split(">");
            this.entity = user_name[0];
        }
        else{

            user_name = new String[1];
            user_name[0] = entity;
            this.entity = entity;
        }


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

        // _________________________________ //
        // Panel Report
        TextPanel report = new TextPanel(frame, 915, 60, 70, 40,"Report", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 14);
        //report.setRightAlignment();

        // Download Button
        this.Download = new Button(false);
        frame.add(this.Download.iconButton(982, 60, 40, 40, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\download.png"));
        this.Download.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\download.png", 12, 19);

        // Search text field
        this.searchField = new Textfield();

        if (entity.contains("Users")){

            if (searchCheck){
                this.frame.add(searchField.textField(1032, 60, 138, 40, user_name[1]));
                this.searchField.setFontSize(14f);
                this.searchField.setInvert();
                //this.searchField.setTextColor(184, 184, 184);
                this.searchField.disable();

            }
            else{
                this.frame.add(searchField.textField(1032, 60, 138, 40, "Search " + user_name[0] + "..." ));
                this.searchField.setFontSize(14f);
                this.searchField.setInvert();
                this.searchField.setTextColor(184, 184, 184);
            }
        }
        else{
            if (searchCheck){
                this.frame.add(searchField.textField(1032, 60, 138, 40, user_name[1]));
                this.searchField.setFontSize(14f);
                this.searchField.setInvert();
                //this.searchField.setTextColor(184, 184, 184);
                this.searchField.disable();

            }
            else{
                this.frame.add(searchField.textField(1032, 60, 138, 40, "Search Comp..." ));
                this.searchField.setFontSize(14f);
                this.searchField.setInvert();
                this.searchField.setTextColor(184, 184, 184);
            }
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


        // Adding Text Login
        Text Entity = new Text(this.frame, 350, 30, 300, 100, user_name[0], "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        Entity.setInvertColor();

        // Admin
        this.Admin = new Text(this.frame, 121, 331, 80, 30, "Admin", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 22f);

        Button backButton = new Button(false);
        frame.add(backButton.iconButton(216, 35, 50, 50, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\arrow-left.png"));
        backButton.setIconSize("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Logo\\arrow-left.png", 16, 28);
        backButton.setInvert();

        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 768);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();

            if(entity.contains("Users")){

                if (searchCheck){
                    System.out.println("hehehehe user");
                    ResultSet rs = stmt.executeQuery("SELECT distinct * FROM customer natural join name where upper(name.f_name) like upper('%" + searchField.getText() + "%') or upper(name.l_name) like upper('%" + searchField.getText() + "%')");

                    while (rs.next()){
                        c_array.add(new Backend.Person(rs.getString(1), new Name(rs.getString(3), rs.getString(4))));
                    }
                }
                else{

                    ResultSet rs = stmt.executeQuery("select name.f_name, name.l_name, cnic, person.email, person.phone from customer natural join name natural join Person\n");
                    // String company, int id, Picture carPicture
                    while (rs.next()){
                        c_array.add(new Backend.Person(rs.getString(3), new Name(rs.getString(1), rs.getString(2)), rs.getString(4), rs.getString(5)));
                    }
                }

            }
            //   m_array.add(new Backend.Manufacturer(rs.getString(3), rs.getString(2)));
            else{

                if (searchCheck){

                    ResultSet rs = stmt.executeQuery("SELECT distinct * FROM manufacturer  where upper(company_name) like upper('%" + searchField.getText() + "%')");

                    while (rs.next()){
                        m_array.add(new Backend.Manufacturer(rs.getString(3), rs.getString(2)));
                    }
                }
                else{
                    // select manufacturer.company_name, cnic, name.f_name, name.l_name, person.email from manufacturer natural join name natural join person
                    ResultSet rs = stmt.executeQuery("select manufacturer.company_name, cnic, name.f_name, name.l_name, person.email, person.phone from manufacturer natural join name natural join person");
                    // String company, int id, Picture carPicture
                    while (rs.next()){
                        m_array.add(new Backend.Manufacturer(rs.getString(1), rs.getString(2), rs.getString(3) + " " + rs.getString(4), rs.getString(5), rs.getString(6)));
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println("Error <Admin>....");
        }

        if (entity.contains("Users") && c_array.size() == 0){
            Text sorry = new Text(this.frame, 590+50, 350, 400, 40, "No User Exists : )", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 25f);
            sorry.setInvertColor();

        }
        if (entity.contains("Companies") && m_array.size() == 0){
            Text sorry = new Text(this.frame, 590+20, 350, 400, 40, "No Company Exists : )", "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 25f);
            sorry.setInvertColor();

        }

        // Use this as an array list
        int start_x = 0;
        int start_y = 0;

        System.out.println("Company Array -> " + m_array.size());
        if (entity.contains("Users")){
            for(int i = 1; i <= c_array.size(); i++){
                if (i % 2 == 0){
                    // Keep on adding in loop!
                    Button user = new Button(false);
                    frame.add(user.newButton(350 + start_x, 140 + start_y, 420, 88, c_array.get(i - 1).getName().getF_name() + " " + c_array.get(i - 1).getName().getL_name() + " - " + c_array.get(i - 1).getCnic(), "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
                    user.setLightColor();
                    start_x = 0;
                    start_y = start_y + 103;
                }
                else{
                    // Keep on adding in loop!
                    Button user = new Button(false);
                    frame.add(user.newButton(350 + start_x, 140 + start_y, 420, 88, c_array.get(i - 1).getName().getF_name() + " " + c_array.get(i - 1).getName().getL_name() + " - " + c_array.get(i - 1).getCnic(), "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
                    user.setLightColor();
                    start_x = start_x + 440;
                }
            }
        }
        else {
            for(int i = 1; i <= m_array.size(); i++){
                if (i % 2 == 0){
                    // Keep on adding in loop!
                    Button user = new Button(false);
                    frame.add(user.newButton(350 + start_x, 140 + start_y, 420, 88, m_array.get(i - 1).getCompany() + " - " + m_array.get(i - 1).getCnic(), "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
                    user.setLightColor();
                    start_x = 0;
                    start_y = start_y + 103;
                }
                else {
                    // Keep on adding in loop!
                    Button user = new Button(false);
                    frame.add(user.newButton(350 + start_x, 140 + start_y, 420, 88, m_array.get(i - 1).getCompany() + " - " + m_array.get(i - 1).getCnic(), "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 22f));
                    user.setLightColor();
                    start_x = start_x + 440;
                }
            }
        }


        if(isReport && entity.contains("Users")){
            System.out.println("?>>>");
            try{

                // Create a new PDF document
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Admin\\All_Users.pdf"));
                document.open();

                // Create a .pdf Table
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);

                // Add table headers
                table.addCell("Customer Full Name");
                table.addCell("Customer CNIC");
                table.addCell("Customer Email");
                table.addCell("Customer Phone#");
                // Get Current Date of Today
                LocalDate currentDate = LocalDate.now();

                for (int i = 0; i < c_array.size() ; i++) {

                    // Add elements into the table
                    table.addCell(c_array.get(i).getName().getF_name() + " " + c_array.get(i).getName().getL_name());
                    table.addCell(c_array.get(i).getCnic());
                    table.addCell(c_array.get(i).getEmail());
                    table.addCell(c_array.get(i).getPhoneNo());

                }
                // Close the document
                document.add(new Paragraph("Admin              " + "Today's Date: "  + currentDate.toString() + "\n\n"));
                document.add(table);

                document.close();
                writer.close();


                File report_file = new File("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Admin\\All_Users.pdf");
                Desktop desktop = Desktop.getDesktop();
                if(report_file.exists()){         //checks file exists or not
                    desktop.open(report_file);              //opens the specified file
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (isReport && c_array.size() == 0 && entity.contains("Users")){
            JOptionPane.showMessageDialog(new JFrame(), "No Customer Exist!");
        }
        if (isReport && entity.contains("Companies")){

            try{

                // Create a new PDF document
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Admin\\All_Companies.pdf"));
                document.open();

                // Create a .pdf Table
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);

                // Add table headers
                table.addCell("Company");
                table.addCell("Seller CNIC");
                table.addCell("Seller Full Name");
                table.addCell("Seller Email");
                table.addCell("Seller Phone#");

                // Get Current Date of Today
                LocalDate currentDate = LocalDate.now();

                for (int i = 0; i < m_array.size() ; i++) {

                    // Add elements into the table
                    table.addCell(m_array.get(i).getCompany());
                    table.addCell(m_array.get(i).getCnic());
                    table.addCell(m_array.get(i).getOwnerFullName());
                    table.addCell(m_array.get(i).getSellerEmail());
                    table.addCell(m_array.get(i).getSellerPhone());

                }
                // FOrmating current date
                String[] cD = currentDate.toString().split("-");
                document.add(new Paragraph("Admin              " + "Today's Date: "  + (cD[2] + "-" + cD[1] + "-" + cD[0]) + "\n\n"));
                document.add(table);

                // Close the document
                document.close();
                writer.close();


                File report_file = new File("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Reports\\Admin\\All_Companies.pdf");
                Desktop desktop = Desktop.getDesktop();
                if(report_file.exists()){         //checks file exists or not
                    desktop.open(report_file);              //opens the specified file
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (isReport && m_array.size() == 0 && entity.contains("Companies")){
            JOptionPane.showMessageDialog(new JFrame(), "No Company Exist");
        }

    }

    // Get Admin Text
    public String getAdmin(){
        return this.Admin.getText();
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

    public String getEntity() {
        return entity;
    }

    public Button getDownload() {
        return Download;
    }



    public String getSearchFieldText() {
        return searchField.getText();
    }
}
