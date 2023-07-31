package Essentials;

import Pages.*;
import Backend.*;
import com.itextpdf.text.DocumentException;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Button implements ActionListener {

    protected JButton button;
    protected Boolean animate;

    // Constructor
    public Button(Boolean animate) {
        //this.button = new JButton();
        //this.button.setBounds(100, 100, 100, 50);
        this.animate = animate;
    }

    // creating a custom button
    public JButton newButton(int button_x, int button_y, int buttonWidth, int buttonHeight, String buttonText, String fontLocation, float fontSize) {
        this.button = new JButton();
        this.button.setBounds(button_x, button_y, buttonWidth, buttonHeight);
        this.button.setForeground(Color.WHITE);
        this.button.setBackground(new Color(57, 57, 57));

        // Some settings to make button look awsome!
        this.button.setOpaque(true);
        this.button.setContentAreaFilled(true);
        this.button.setBorderPainted(false);
        this.button.setFocusPainted(false);

        // Added text with in the button
        this.button.setText(buttonText);
        this.button.setFont(Static.newFont(fontLocation, fontSize));
        this.button.setFocusable(false);
        this.button.setHorizontalTextPosition(JButton.CENTER);
        this.button.setVerticalTextPosition(JButton.CENTER);
        this.button.addActionListener(this);

        // Only Add Animation when needed!
        if (animate) {
            // Creating the hover state of the button
            this.button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    // We can certainly Customize this shit!
                    button.setBounds(button_x - 25, button_y, buttonWidth + 50, buttonHeight);

                }

                public void mouseExited(MouseEvent e) {
                    button.setBounds(button_x, button_y, buttonWidth, buttonHeight);
                }
            });
        }
        return this.button;
    }

    // creating a custom button
    public JButton iconButton(int x, int y, int width, int height, String img) {
        this.button = new JButton();
        this.button.setBounds(x, y, width, height);
        this.button.setBackground(new Color(57, 57, 57));
        //this.button.setForeground(new Color(203, 203, 203));

        //this.button.setBorder(BorderFactory.createLineBorder((new Color(255, 248, 238)), 0));
        //this.button.setBackground(Color.black);

        this.button.setIcon(Static.resizeImageIcon(new ImageIcon(img), 25, 25));

        // Some settings to make button look awsome!
        this.button.setOpaque(true);
        this.button.setContentAreaFilled(true);
        this.button.setBorderPainted(false);
        this.button.setFocusPainted(false);

        this.button.addActionListener(this);

        return this.button;
    }

    // set Enabled
    public void setEnabled(Boolean set) {
        this.button.setEnabled(set);
    }

    // setIconSize
    public void setIconSize(String img, int width, int height) {
        this.button.setIcon(Static.resizeImageIcon(new ImageIcon(img), width, height));

    }

    public void setText(String text) {
        this.button.setText(text);
        this.button.setFont(Static.newFont("E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Regular.ttf", 0f));

    }

    // Get text of the button
    public String getText() {
        return this.button.getText();
    }


    // set Color
    public void setInvert() {
        this.button.setForeground(new Color(57, 57, 57));
        this.button.setBackground(new Color(236, 236, 236));
    }

    // SetLightColor
    public void setLightColor() {
        this.button.setForeground(new Color(57, 57, 57));
        this.button.setBackground(new Color(203, 203, 203));
    }


    // ---------------------------------------------------------- //
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button && this.button.getX() == 216 && this.button.getY() == 35 && (Static.route.peek() instanceof Pages.AdminHome)) {
            Pages.Main.closeInstance(Static.route.peek());
            Login_Signup main_page = new Login_Signup(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location);
            main_page.setVisible(true);
            Static.route.push(main_page);
        } else if (e.getSource() == this.button && this.button.getX() == 216 && this.button.getY() == 35) {
            Pages.Main.closeInstance(Static.route.pop());
            AdminUsers.searchCheck = false;
            Pages.Main.openInstance(Static.route.peek());
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Back")) {

            ListCars.searchCheck = false;
            ListProducts.searchCheck = false;
            Store.searchCheck = false;

            AdminUsers.isReport = false;
            User_HomeScreen.isReport = false;
            Company_HomeScreen.isReport = false;


            Pages.Main.closeInstance(Static.route.pop());
            Pages.Main.openInstance(Static.route.peek());
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Logout")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Login_Signup main_page = new Login_Signup(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location);
            main_page.setVisible(true);
            Static.route.push(main_page);

            AdminUsers.isReport = false;
            User_HomeScreen.isReport = false;
            Company_HomeScreen.isReport = false;

        }
        // 230, 42
        else if (e.getSource() == this.button && this.button.getX() == 230 && this.button.getY() == 42) {
            boolean id_bool = true;
            boolean existing = true;
            boolean isSelected = true;
            int pic_id = 0;

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif", "jpeg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                isSelected = true;
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
            }
            else if (returnVal == JFileChooser.CANCEL_OPTION) {
                isSelected = false;
                JOptionPane.showMessageDialog(new Frame(), "Please Select a Picture");

                //Pages.Main.closeInstance(Static.route.peek());
                //System.out.println("CH" + chooser);
                //Pages.Main.openInstance(Static.route.peek());

            }

            if(isSelected){

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    // Connection Code!
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                    Statement stmt = con.createStatement();

                    ResultSet id = stmt.executeQuery("SELECT PICTURE_ID FROM manufacturer where company_name = '" + Static.current_user + "'");

                    while (id.next()) {
                        if (id.getInt(1) == 0) {
                            existing = false;
                            System.out.println("At False");
                        } else {
                            System.out.println(id.getInt(1));
                            pic_id = id.getInt(1);
                            System.out.println("Found it " + pic_id);
                        }
                    }

                    int picture_id = 0;
                    while (id_bool) {
                        picture_id = (int) (Math.random() * 1000);
                        ResultSet rs2 = stmt.executeQuery("SELECT PICTURE_ID FROM Picture where PICTURE_ID = " + picture_id);
                        while (rs2.next()) {
                            if (rs2.getInt(1) == picture_id) {
                                picture_id = (int) (Math.random() * 1000);
                                id_bool = false;
                            }
                        }
                        id_bool = !id_bool;
                    }
                    // stmt.executeQuery("Update car set color = '" + P_color + "', quantity = " + Integer.parseInt(P_qty) + ", coupe = " + 1 + ", price = " + Integer.parseInt(P_price) + " where name = '" + P_name + "'");
                    // update manufacturer set picture_id = 2 where company_name = 'Tesla'

                    if (existing) {

                        Pages.Main.closeInstance(Static.route.peek());
                        // update picture set name = '', address = '' where picture_id = 2
                        stmt.executeQuery("update picture set name = '" + chooser.getSelectedFile().getName() + "', address = '" + chooser.getSelectedFile().getAbsolutePath() + "' where picture_id = " + pic_id);
                        Pages.Main.openInstance(Static.route.peek());

                    } else {

                        Pages.Main.closeInstance(Static.route.peek());
                        stmt.executeUpdate("INSERT INTO PICTURE VALUES (" + picture_id + ",'" + chooser.getSelectedFile().getName() + "','" + chooser.getSelectedFile().getAbsolutePath() + "')");
                        stmt.executeQuery("update manufacturer set picture_id = " + picture_id + "where company_name = '" + Static.current_user + "'");
                        Pages.Main.openInstance(Static.route.peek());

                    }

                } catch (Exception e2) {
                    e2.printStackTrace();
                    System.out.println("Error <Add Picture> .................");
                }
            }


//
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Login")) {

            // Closes the previous screen and opens up the new screen
            // ------------------------------------------------------- //
            // Checking what person has access to [Customer, Company and Admin]
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Login login_page = new Pages.Login(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, "Abdullah");
            login_page.setVisible(true);
            Static.route.push(login_page);

        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Signup")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Signup signup_page = new Pages.Signup(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, "Abdullah");
            signup_page.setVisible(true);
            Static.route.push(signup_page);
        }
        else if (e.getSource() == this.button && this.button.getX() == 1144 && this.button.getY() == 576 && (Static.route.peek() instanceof Pages.Signup) && ((Signup) Static.route.peek()).getContinue().equalsIgnoreCase("Continue")) {
            // Closes the previous screen and opens up the new screen
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                Statement stmt = con.createStatement();

                System.out.println(((Signup) Static.route.peek()).getDateText());

                // All of them are ligned up as String
                String date = ((Signup) Static.route.peek()).getDateText();
                String month = ((Signup) Static.route.peek()).getMonthText();
                String year = ((Signup) Static.route.peek()).getYearText();
                String cnic = ((Signup) Static.route.peek()).getCNICText();
                String zipCode = ((Signup) Static.route.peek()).getZipText();
                String fName = ((Signup) Static.route.peek()).getF_NameText();
                String lName = ((Signup) Static.route.peek()).getL_NameText();
                String city = ((Signup) Static.route.peek()).getCityText();

                System.out.println(date + month + year + cnic + zipCode );

                ResultSet rs = stmt.executeQuery("SELECT CNIC FROM PERSON WHERE CNIC = '" + cnic + "'");
                boolean cnicValid = true;
                boolean cnV = true;
                boolean zipV = true;
                boolean ddV = true;
                boolean mmV = true;
                boolean yyV = true;
                boolean fnV = true;
                boolean lnV = true;
                boolean cityV = true;

                while (rs.next()) {
                    if (rs.getString(1).equalsIgnoreCase(cnic)) {
                        cnV = false;
                        ((Signup) Static.route.peek()).getCNIC().setBorder(Color.RED);
                    }
                }

                if(cnicValid){

                    if (cnic.matches(".*[a-zA-Z]+.*") || cnic.length() != 13) {
                        cnV = false;
                        ((Signup) Static.route.peek()).getCNIC().setBorder(Color.RED);
                    }
                    else{
                        cnV = true;
                        ((Signup) Static.route.peek()).getCNIC().setBorder(Color.GREEN);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "User has already as account");
                }

                if (date.matches(".*[a-zA-Z]+.*") || (!(Integer.parseInt(date) >= 1 && Integer.parseInt(date) <= 31))) {
                    ddV = false;
                    ((Signup) Static.route.peek()).getDate().setBorder(Color.RED);
                }
                else{
                    ddV = true;
                    ((Signup) Static.route.peek()).getDate().setBorder(Color.GREEN);
                }
                if (month.matches(".*[a-zA-Z]+.*") || (!(Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12))) {
                    mmV = false;
                    ((Signup) Static.route.peek()).getMonth().setBorder(Color.RED);
                }
                else{
                    mmV = true;
                    ((Signup) Static.route.peek()).getMonth().setBorder(Color.GREEN);
                }
                if (year.matches(".*[a-zA-Z]+.*") || (!(Integer.parseInt(year) >= 1950 && Integer.parseInt(year) <= 2004))) {
                    yyV = false;
                    ((Signup) Static.route.peek()).getYear().setBorder(Color.RED);
                }
                else{
                    yyV = true;
                    ((Signup) Static.route.peek()).getYear().setBorder(Color.GREEN);
                }
                if (zipCode.matches(".*[a-zA-Z]+.*") || (zipCode.length() != 5)) {
                    zipV = false;
                    ((Signup) Static.route.peek()).getZip().setBorder(Color.RED);
                }
                else {
                    zipV = true;
                    ((Signup) Static.route.peek()).getZip().setBorder(Color.GREEN);
                }

                if (fName.equalsIgnoreCase("First Name")) {
                    fnV = false;
                    ((Signup) Static.route.peek()).getF_name().setBorder(Color.RED);
                }
                else {
                    fnV = true;
                    ((Signup) Static.route.peek()).getF_name().setBorder(Color.GREEN);
                }

                if (lName.equalsIgnoreCase("Last Name")) {
                    lnV = false;
                    ((Signup) Static.route.peek()).getL_name().setBorder(Color.RED);
                }
                else {
                    lnV = true;
                    ((Signup) Static.route.peek()).getL_name().setBorder(Color.GREEN);
                }
                if (city.equalsIgnoreCase("abc")) {
                    cityV = false;
                    ((Signup) Static.route.peek()).getCity().setBorder(Color.RED);
                }
                else {
                    cityV = true;
                    ((Signup) Static.route.peek()).getCity().setBorder(Color.GREEN);
                }

                if (cnV && zipV && ddV && mmV && yyV && fnV && lnV && cityV) {
                    Pages.Main.closeInstance(Static.route.peek());
                    Pages.Signup signup_page = new Pages.Signup(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Abdullah");
                    signup_page.setVisible(true);
                    Static.route.push(signup_page);
                }
//                else{
//                    JOptionPane.showMessageDialog(new JFrame(), "Please Enter the Valid Details");
//                }

            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("Error Sign Up 1 .................");
            }
        }
        else if (e.getSource() == this.button && this.button.getX() == 1144 && this.button.getY() == 576 && (Static.route.peek() instanceof Pages.Signup) && ((Signup) Static.route.peek()).getFinalize().equalsIgnoreCase("Finalize")) {


            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");


                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                Statement stmt = con.createStatement();

                //System.out.println("oye hoyye");

                String f_name = ((Signup) Static.route.peek()).getF_NameText();
                String l_name = ((Signup) Static.route.peek()).getL_NameText();
                String city = ((Signup) Static.route.peek()).getCityText();
                int date = Integer.parseInt(((Signup) Static.route.peek()).getDateText());
                int month = Integer.parseInt(((Signup) Static.route.peek()).getMonthText());
                int year = Integer.parseInt(((Signup) Static.route.peek()).getYearText());
                String cnic = ((Signup) Static.route.peek()).getCNICText();
                String email = ((Signup) Static.route.peek()).getEmailText();
                int zipCode = Integer.parseInt(((Signup) Static.route.peek()).getZipText());

                //System.out.println(f_name);
                //System.out.println(l_name);
                //System.out.println(city);
                //System.out.println(date);
                //System.out.println(month);
                //System.out.println(year);
                //System.out.println(cnic);
                //System.out.println(email);
                //System.out.println(zipCode);
                //System.out.println(company);
                //System.out.println(phone);

                String company = ((Signup) Static.route.peek()).getCompanyText();
                String phone = ((Signup) Static.route.peek()).getPhoneText();

                char[] vp = ((Signup) Static.route.peek()).getVerifyPasswordText();
                char[] p = ((Signup) Static.route.peek()).getPasswordText();

                String pass = "";
                String Vpass = "";

                for (int i = 0; i < p.length; i++) {
                    pass += p[i];
                }
                System.out.println(pass);
                for (int i = 0; i < vp.length; i++) {
                    Vpass += vp[i];
                }

                // System.out.println(Vpass);


//                boolean check = true;
                boolean manufac = true;
                boolean emV = true, passV = true, phV = true;
                boolean companyCheck = true;
                System.out.println("oye hoyye2");


                ResultSet em = stmt.executeQuery("SELECT email FROM person WHERE email = '" + email + "'");
                while (em.next()) {
                    if (em.getString(1).equalsIgnoreCase(email)) {
                        emV = false;
                        JOptionPane.showMessageDialog(new Frame(), "Email Already Exists");
                        //((Signup) Static.route.peek()).getEmail().setBorder(Color.red);
                    }
                }
                /*if (emV){
                    ((Signup) Static.route.peek()).getEmail().setBorder(Color.green);

                }*/


                // Email
                if (!(email.contains("@") && email.contains(".com")) || !emV) {
                    emV = false;
                    ((Signup) Static.route.peek()).getEmail().setBorder(Color.RED);
                } else {

                    ((Signup) Static.route.peek()).getEmail().setBorder(Color.green);
                    emV = true;
                }

                System.out.println(email + emV);

                // Password
                if (!(p.length >= 8 && pass.equals(Vpass))) {
                    passV = false;
                    ((Signup) Static.route.peek()).getPasswordTF().setBorder(Color.RED);
                    ((Signup) Static.route.peek()).getPswdField().setBorder(Color.RED);
                } else {
                    ((Signup) Static.route.peek()).getPasswordTF().setBorder(Color.GREEN);
                    ((Signup) Static.route.peek()).getPswdField().setBorder(Color.GREEN);
                    passV = true;
                }
                //phone
                if (phone.length() != 11) {
                    phV = false;
                    ((Signup) Static.route.peek()).getPh().setBorder(Color.RED);
                } else {

                    ((Signup) Static.route.peek()).getPh().setBorder(Color.GREEN);
                    phV = true;
                }
                // manufacturer


                if ((company.equalsIgnoreCase("Manufacturers-Only"))) {
                    manufac = false;
                } else {


                    ResultSet comp = stmt.executeQuery("SELECT COMPANY_NAME FROM Manufacturer WHERE COMPANY_NAME = '" + company + "'");
                    while (comp.next()) {
                        if (comp.getString(1).equalsIgnoreCase(company)) {

                            companyCheck = false;
                            ((Signup) Static.route.peek()).getcTF().setBorder(Color.red);
                        }
                    }
                    if (companyCheck) {
                        ((Signup) Static.route.peek()).getcTF().setBorder(Color.green);

                    }


                }

                System.out.println("Aithee aagya ee " + emV + passV + phV + companyCheck);
                if (emV && passV && phV && companyCheck) {
                    // Putting CNIC there
                    Static.CNIC = cnic;
                    Static.current_user = f_name;
                    if (manufac) {
                        System.out.println("Manufac True");
                        int id = -1;
                        boolean uniqId = true;
                        while (uniqId) {

                            id = (int) (Math.random() * 1000);
                            System.out.println("Random ID" + id);

                            ResultSet rs = stmt.executeQuery("SELECT ID FROM Manufacturer WHERE ID = " + id);
                            // Gotta Make sure it doesn't do the shit in here
                            while (rs.next()) {
                                if (Integer.parseInt(rs.getString(1)) != id) {
                                    System.out.println("Found ID" + rs.getString(1));
                                    uniqId = false;
                                    break;
                                }
                            }
                            uniqId = !uniqId;

                        }
                        Manufacturer person1 = new Manufacturer(cnic, phone, email, pass, new Date(date, month, year), new Name(f_name, l_name), new Address(zipCode, city), company, id);
                        InsertSQL.insert(person1, stmt);
                        Pages.Main.closeInstance(Static.route.peek());
                        Static.current_user = company;
                        Pages.Company_HomeScreen company_home_page = new Pages.Company_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
                        company_home_page.setVisible(true);
                        Static.route.push(company_home_page);

                    } else {
                        int id = -1;
                        boolean uniqId = true;
                        while (uniqId) {

                            id = (int) (Math.random() * 1000);
                            System.out.println("Random ID" + id);

                            ResultSet rs = stmt.executeQuery("SELECT ID FROM CUSTOMER WHERE ID = " + id);
                            // Gotta Make sure it doesn't do the shit in here
                            while (rs.next()) {
                                if (Integer.parseInt(rs.getString(1)) != id) {
                                    System.out.println("Found ID" + rs.getString(1));
                                    uniqId = false;
                                    break;
                                }
                            }
                            uniqId = !uniqId;

                        }
                        Customer person1 = new Customer(cnic, phone, email, pass, new Date(date, month, year), new Name(f_name, l_name), new Address(zipCode, city), id);
                        InsertSQL.insert(person1, stmt);
                        Static.current_user = f_name;
                        // Closes the previous screen and opens up the new screen
                        Pages.Main.closeInstance(Static.route.peek());
                        Pages.User_HomeScreen user_home_page = new Pages.User_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
                        user_home_page.setVisible(true);
                        Static.route.push(user_home_page);
                    }

                }

            } catch (Exception e2) {
                System.out.println("Error Sign Up 2 .................");
            }
        } else if (e.getSource() == this.button && this.button.getX() == 1144 && this.button.getY() == 576 && (Static.route.peek() instanceof Pages.Login)) {
            int iden = 0;

            // Closes the previous screen and opens up the new screen

            // Check what person has access to [Customer, Company and Admin]

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");


                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                Statement stmt = con.createStatement();


                String email = ((Login) (Static.route.peek())).getEmailText();

                char[] p = ((Login) (Static.route.peek())).getPasswordText();
                String pass = "";
                for (int i = 0; i < p.length; i++) {
                    pass += p[i];
                }

                String cnic = "";
                String name = "";

                ResultSet rs = stmt.executeQuery("SELECT PERSON.EMAIL,PERSON.PASSWORD,PERSON.CNIC FROM PERSON WHERE EMAIL = '" + email + "'");

                boolean emailValid = false, passValid = false;
                while (rs.next()) {
                    if (rs.getString(1).equals(email)) {
                        System.out.println(rs.getString(1) + "  " + email);
                        emailValid = true;
                        ((Login) (Static.route.peek())).getFullName().setBorder(Color.green);

                        if (rs.getString(2).equals(pass)) {
                            System.out.println(rs.getString(2) + "  " + pass);
                            cnic = rs.getString(3);
                            ((Login) (Static.route.peek())).getPswdField().setBorder(Color.green);

                            passValid = true;

                        }

                    }

                }

                if (emailValid && passValid) {

                    System.out.println("Not in here");
                    // Putting CNIC there
                    Static.CNIC = cnic;

//                        System.out.println("In check");
                    ResultSet rs2 = stmt.executeQuery("SELECT CNIC FROM MANUFACTURER WHERE CNIC = '" + cnic + "'");
                    while (rs2.next()) {
                        if (rs2.getString(1).equals(cnic)) {
                            iden = 1;
                        }
                    }

                    rs2 = stmt.executeQuery("SELECT CNIC FROM CUSTOMER WHERE CNIC = '" + cnic + "'");
                    while (rs2.next()) {
                        if (rs2.getString(1).equals(cnic)) {
                            iden = 2;
                        }
                    }

                    rs2 = stmt.executeQuery("SELECT CNIC FROM ADMIN WHERE CNIC = '" + cnic + "'");
                    while (rs2.next()) {
                        if (rs2.getString(1).equals(cnic)) {
                            iden = 3;
                        }
                    }

                }
                if (!passValid) {
                    ((Login) (Static.route.peek())).getPswdField().setBorder(Color.red);
                }
                if (!emailValid) {
                    ((Login) (Static.route.peek())).getFullName().setBorder(Color.red);
                }


                // --------------------- Customer ---------------------- //

                if (iden != 0) {
                    Pages.Main.closeInstance(Static.route.peek());
                    System.out.println("Identity :: " + iden);

                    ResultSet rs3 = stmt.executeQuery("SELECT F_NAME FROM NAME WHERE CNIC = '" + cnic + "'");


                    System.out.println(iden);
//                System.out.println("hello");

//                    System.out.println(rs3.getString(1));
                    while (rs3.next()) {
                        name = rs3.getString(1);
                    }
                    Static.CNIC = cnic;
                    Static.current_user = name;

                }

                if (iden == 2) {
//                System.out.println("in-person");
                    Pages.User_HomeScreen user_home_page = new Pages.User_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, name);
                    user_home_page.setVisible(true);
                    Static.route.push(user_home_page);
                }

                // --------------------- Company ---------------------- //
                else if (iden == 1) {
                    ResultSet rs3 = stmt.executeQuery("SELECT Company_name FROM manufacturer where cnic = '" + cnic + "'");
//                System.out.println(iden);

                    while (rs3.next()) {
                        name = rs3.getString(1);
                    }
                    Static.current_user = name;

                    Pages.Company_HomeScreen company_home_page = new Pages.Company_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, name);
                    company_home_page.setVisible(true);
                    Static.route.push(company_home_page);
                }

                // --------------------- Admin ---------------------- //
                else if (iden == 3) {

                    Pages.AdminHome admin_home_page = new Pages.AdminHome(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, name);
                    admin_home_page.setVisible(true);
                    Static.route.push(admin_home_page);
                }
            } catch (Exception ex) {
                System.out.println("Error Login........");
            }
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Dashboard")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            User_HomeScreen user_home_page = new User_HomeScreen(Main.screen_Width, Main.screen_Height, Main.screen_Title, Main.screen_Image_Location, Main.screen_Image_Location, Static.current_user);
            user_home_page.setVisible(true);
            Static.route.push(user_home_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Store")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Store store_page = new Pages.Store(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
            store_page.setVisible(true);
            Static.route.push(store_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Booking")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Booking booking_page = new Pages.Booking(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
            booking_page.setVisible(true);
            Static.route.push(booking_page);
        } else if (e.getSource() == this.button && (Static.route.peek() instanceof Pages.Type)) {
            // Show the list of available cars according to the company type
            // Match the Company name with the existing companies available and show data accordingly
            // Use Companies.company to check what company has been clicked!
            if (this.button.getText().equalsIgnoreCase("SUV")) {

                // Closes the previous screen and opens up the new screen
                Pages.Main.closeInstance(Static.route.peek());
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "SUV", Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);
            } else if (this.button.getText().equalsIgnoreCase("Sports")) {

                // Closes the previous screen and opens up the new screen
                Pages.Main.closeInstance(Static.route.peek());
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Sports", Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);

            } else if (this.button.getText().equalsIgnoreCase("Sedan")) {

                // Closes the previous screen and opens up the new screen
                Pages.Main.closeInstance(Static.route.peek());
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Sedan", Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);

            } else if (this.button.getText().equalsIgnoreCase("Jeep")) {

                // Closes the previous screen and opens up the new screen
                Pages.Main.closeInstance(Static.route.peek());
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Jeep", Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);
            } else if (this.button.getText().equalsIgnoreCase("Hatch Back")) {
                // Closes the previous screen and opens up the new screen
                Pages.Main.closeInstance(Static.route.peek());
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Hatch Back", Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);
            }
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Book Now")) {
            // Closes the previous screen and opens up the new screen
            // Add current car to booking!
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");


                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                Statement stmt = con.createStatement();


                int id = -1;
                boolean uniqId = true;
                while (uniqId) {

                    id = (int) (Math.random() * 1000);
                    System.out.println("Random ID" + id);

                    ResultSet rs = stmt.executeQuery("SELECT ID FROM BOOKING WHERE ID = " + id);
                    // Gotta Make sure it doesn't do the shit in here
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString(1)) != id) {
                            System.out.println("Found ID" + rs.getString(1));
                            uniqId = false;
                            break;
                        }
                    }
                    uniqId = !uniqId;

                }


                int c_id = 0;
                System.out.println("CNIC" + Static.CNIC);
                ResultSet rs2 = stmt.executeQuery("SELECT ID FROM CUSTOMER WHERE CNIC='" + Static.CNIC + "'");
                while (rs2.next()) {
                    c_id = rs2.getInt(1);
                }
                System.out.println(c_id);


                ResultSet rs = stmt.executeQuery("SELECT * FROM CAR WHERE NAME = '" + ((Pages.Car) Static.route.peek()).getCarName() + "'");

                String car_id = "";
                String car_comp = "";
                while (rs.next()) {
//                    System.out.println(rs.getString(1));
//                    System.out.println(rs.getString(4));
                    car_comp = rs.getString(4);
                    car_id = rs.getString(1);
//                    stmt.executeUpdate("INSERT INTO BOOKING VALUES (" + id + ",'" + rs.getString(1) + "', NULL , 1,'" + rs.getString(4) + "'," + c_id + ")");
                    break;
                }
                boolean car_present = false;
                System.out.println("hehe");
                ResultSet rs3 = stmt.executeQuery("SELECT CAR FROM BOOKING WHERE CAR = '" + rs.getString(1) + "' and owner = " + c_id + "and pending = 1");
                while (rs3.next()) {
                    if (rs3.getString(1).equalsIgnoreCase(rs.getString(1))) {
                        JOptionPane.showMessageDialog(new JFrame(), "Car Already Exists! Purchase existing first");
                        car_present = true;
                    }
                    break;
                }
                if (!car_present) {
                    stmt.executeQuery("INSERT INTO BOOKING VALUES (" + id + ",'" + car_id + "',NULL,1,'" + car_comp + "'," + c_id + ")");
                    System.out.println("Hereee");
                }

            } catch (Exception ex) {
                System.out.println("Error <Booking>....");
            }

            System.out.println("Current car is: " + ((Pages.Car) Static.route.peek()).getCarName());
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Booking booking_page = new Pages.Booking(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, ((Pages.Car) Static.route.peek()).getCarName(), Static.current_user);
            booking_page.setVisible(true);
            Static.route.push(booking_page);
        } else if (e.getSource() == this.button && this.button.getX() == 870 && Static.route.peek() instanceof Pages.Booking) {
            System.out.println(this.button.getText());

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");
                Statement stmt = con.createStatement();

                stmt.executeQuery("DELETE FROM BOOKING WHERE CAR = '" + this.button.getText() + "' and pending = 1");
                JOptionPane.showMessageDialog(new JFrame(), "Car Has been removed! Refresh the page for changes");

            } catch (Exception ex) {
                System.out.println("Error <Delete Booking>....");
            }

        }
        // Proceed Button
        else if (e.getSource() == this.button && this.button.getX() == 964) {
            // Closes the previous screen and opens up the new screen
            //870 + 95
            System.out.println(this.button.getText());

            //System.out.println(((BookingTiles) Static.route.peek()).getCar_id());

            Pages.Main.closeInstance(Static.route.peek());
            Pages.Payment payment_page = new Pages.Payment(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, this.button.getText(), Static.current_user);
            payment_page.setVisible(true);
            Static.route.push(payment_page);
        } else if (e.getSource() == this.button && this.button.getX() == 1144 && this.button.getY() == 576 && (Static.route.peek() instanceof Pages.Payment)) {

            // Check the Credit Card Details -> Based on that Show a popup that your order has been placed!
            String carID = ((Pages.Payment) Static.route.peek()).getCarID();
            String creditCard = ((Pages.Payment) Static.route.peek()).getCreditText();
            int c_month = Integer.parseInt(((Pages.Payment) Static.route.peek()).getMonthText());
            int c_year = Integer.parseInt(((Pages.Payment) Static.route.peek()).getYearText());
            int cvc = Integer.parseInt(((Pages.Payment) Static.route.peek()).getCVVText());
            int dd = Integer.parseInt(((Pages.Payment) Static.route.peek()).getDDText());
            int mm = Integer.parseInt(((Pages.Payment) Static.route.peek()).getMMText());
            int yy = Integer.parseInt(((Pages.Payment) Static.route.peek()).getYYText());

            boolean card_bool = false;
            boolean c_mm_bool = false;
            boolean c_yy_bool = false;
            boolean cvc_bool = false;
            boolean date_bool = false;
            boolean month_bool = false;
            boolean year_bool = false;

            // Checks of all the textfields
            if (Static.isValid(Long.parseLong(creditCard))) {
                card_bool = true;
                ((Pages.Payment) Static.route.peek()).getCreditCard().setBorder(Color.green);
            } else {
                card_bool = false;
                // Change the color of the textfield
                ((Pages.Payment) Static.route.peek()).getCreditCard().setBorder(Color.RED);
            }

            if (c_month >= 1 && c_month <= 12) {
                c_mm_bool = true;
                ((Pages.Payment) Static.route.peek()).getMonth().setBorder(Color.green);
            } else {
                c_mm_bool = false;
                ((Pages.Payment) Static.route.peek()).getMonth().setBorder(Color.RED);
            }

            if (c_year >= 1950 && c_month <= 2040) {
                c_yy_bool = true;
                ((Pages.Payment) Static.route.peek()).getYear().setBorder(Color.green);
            } else {
                c_yy_bool = false;
                ((Pages.Payment) Static.route.peek()).getYear().setBorder(Color.RED);
            }

            if (cvc >= 100 && cvc <= 999) {
                cvc_bool = true;
                ((Pages.Payment) Static.route.peek()).getCVC().setBorder(Color.green);
            } else {
                cvc_bool = false;
                ((Pages.Payment) Static.route.peek()).getCVC().setBorder(Color.RED);
            }

            if (dd >= 1 && dd <= 31) {
                date_bool = true;
                ((Pages.Payment) Static.route.peek()).getDD().setBorder(Color.green);
            } else {
                date_bool = false;
                ((Pages.Payment) Static.route.peek()).getDD().setBorder(Color.RED);
            }

            if (mm >= 1 && mm <= 12) {
                month_bool = true;
                ((Pages.Payment) Static.route.peek()).getMM().setBorder(Color.green);
            } else {
                month_bool = false;
                ((Pages.Payment) Static.route.peek()).getMM().setBorder(Color.RED);
            }

            if (yy >= 1950 && yy <= 2023) {
                year_bool = true;
                ((Pages.Payment) Static.route.peek()).getYY().setBorder(Color.green);
            } else {
                year_bool = false;
                ((Pages.Payment) Static.route.peek()).getYY().setBorder(Color.RED);
            }


            if (card_bool && c_mm_bool && c_yy_bool && cvc_bool && date_bool && month_bool && year_bool) {

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    // Connection Code!
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");
                    Statement stmt = con.createStatement();

                    stmt.executeQuery("update booking set booking_date = TO_DATE('" + dd + "/" + mm + "/" + yy + "','dd/mm/yyyy'), pending = 0 where car = '" + carID + "'");


                    int sts_id = -1;
                    boolean uniqId = true;
                    while (uniqId) {

                        sts_id = (int) (Math.random() * 1000);
                        System.out.println("Random ID" + sts_id);

                        ResultSet rs = stmt.executeQuery("SELECT ID FROM stats WHERE ID = " + sts_id);
                        // Gotta Make sure it doesn't do the shit in here
                        while (rs.next()) {
                            if (rs.getInt(1) != sts_id) {
                                System.out.println("Found ID" + rs.getString(1));
                                uniqId = false;
                                break;
                            }
                        }
                        uniqId = !uniqId;

                    }
                    System.out.println("Stats id: " + sts_id);

                    ResultSet rs2 = stmt.executeQuery("SELECT ID FROM booking WHERE car = '" + carID + "'");
                    int b_id = 0;
                    while (rs2.next()) {
                        b_id = rs2.getInt(1);
                    }

                    stmt.executeUpdate("INSERT INTO stats values (" + sts_id + ", " + b_id + ")");

                    // Reduce the quantity
                    ResultSet rs3 = stmt.executeQuery("SELECT quantity FROM car WHERE car_id = '" + carID + "'");
                    int qty = 0;
                    while (rs3.next()) {
                        qty = rs3.getInt(1);
                    }

                    System.out.println(qty);

                    if (qty == 1) {
                        stmt.executeQuery("update car set quantity = quantity - 1 where car_id = '" + carID + "'");
                        JOptionPane.showMessageDialog(new JFrame(), "Thanks For Booking our Last Product!");
                    } else {
                        stmt.executeQuery("update car set quantity = quantity - 1 where car_id = '" + carID + "'");
                        JOptionPane.showMessageDialog(new JFrame(), "Thanks For Booking! Your Product will be delivered soon");
                    }

                } catch (Exception ex) {
                    System.out.println("Error <Payment!>....");
                }

                System.out.println(carID);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Fill All Boxes ");
            }
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Statistics")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Company_HomeScreen company_home_page = new Pages.Company_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
            company_home_page.setVisible(true);
            Static.route.push(company_home_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Products")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.ListProducts car_product_page = new Pages.ListProducts(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Products", Static.current_user);
            car_product_page.setVisible(true);
            Static.route.push(car_product_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("Modify")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.Modify modify_page = new Pages.Modify(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Modify", Static.current_user);
            modify_page.setVisible(true);
            Static.route.push(modify_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("+ Add")) {
            System.out.println("In here");

            boolean isSelected = true;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif", "jpeg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                isSelected = true;
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
            }
            else if (returnVal == JFileChooser.CANCEL_OPTION) {
                isSelected = false;
                JOptionPane.showMessageDialog(new Frame(), "Please Select a Picture");
            }

            if (isSelected){

                Pages.Main.closeInstance(Static.route.peek());
                ((Add_Product) Static.route.peek()).getSelectedPic().setPicture_address(chooser.getSelectedFile().getAbsolutePath());
                ((Add_Product) Static.route.peek()).getSelectedPic().setPicture_name(chooser.getSelectedFile().getName());

                ((Add_Product) Static.route.peek()).addPic();
                Pages.Main.openInstance(Static.route.peek());
            }

            System.out.println("pic added");

        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("+ Add New Product") || (Static.route.peek() instanceof Pages.Modify)) {


            if (this.button.getText().equalsIgnoreCase("+ Add New Product") && (Static.route.peek() instanceof Pages.Modify)) {

                Pages.Main.closeInstance(Static.route.peek());
                Pages.Add_Product add_product_page = new Pages.Add_Product(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Add Product", Static.current_user);
                //add_product_page.addPic();
                add_product_page.addCoupe();
                add_product_page.setVisible(true);
                Static.route.push(add_product_page);
            } else {
                Backend.Car c = new Backend.Car();
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");


                    // Connection Code!
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                    Statement stmt = con.createStatement();

                    String company = Static.current_user;

                    ResultSet rs = stmt.executeQuery("select * from car cross join picture where CAR.picture_id = picture.picture_id and car.name = '" + getText() + "'");

                    while (rs.next()) {
                        if (rs.getString(1).contains("SV"))
                            c = new Backend.Car(Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(1).substring(3, rs.getString(1).length())), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(2), "SUV", true, new Backend.Picture(Integer.parseInt(rs.getString(9)), rs.getString(11), rs.getString(10)));
                        else if (rs.getString(1).contains("SD"))
                            c = new Backend.Car(Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(1).substring(3, rs.getString(1).length())), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(2), "Sedan", true, new Backend.Picture(Integer.parseInt(rs.getString(9)), rs.getString(11), rs.getString(10)));
                        else if (rs.getString(1).contains("SP"))
                            c = new Backend.Car(Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(1).substring(3, rs.getString(1).length())), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(2), "Sports", true, new Backend.Picture(Integer.parseInt(rs.getString(9)), rs.getString(11), rs.getString(10)));
                        else if (rs.getString(1).contains("JP"))
                            c = new Backend.Car(Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(1).substring(3, rs.getString(1).length())), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(2), "Jeep", true, new Backend.Picture(Integer.parseInt(rs.getString(9)), rs.getString(11), rs.getString(10)));
                        else if (rs.getString(1).contains("HB"))
                            c = new Backend.Car(Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(1).substring(3, rs.getString(1).length())), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(2), "Hatch Back", true, new Backend.Picture(Integer.parseInt(rs.getString(9)), rs.getString(11), rs.getString(10)));

                    }

                } catch (Exception ex) {
                    System.out.println("Error  Car Products....");
                }

                if (this.button.getX() == 1145) {
                    System.out.println("Tapped on that");

                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        // Connection Code!
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");
                        Statement stmt = con.createStatement();

                        // DELETE FROM picture WHERE picture_id = (SELECT picture_id FROM car where name = 'Tesla Model Y')
                        if (c.getType().equalsIgnoreCase("SUV")) {

                            Pages.Main.closeInstance(Static.route.peek());
                            stmt.executeQuery("DELETE FROM SUV WHERE suv.car_id = '" + c.getCar_id() + "'");
                            stmt.executeQuery("DELETE FROM car WHERE name = '" + getText() + "'");
                            stmt.executeQuery("DELETE FROM picture WHERE picture_id = " + c.getCar_picture().getPicture_id());
                            Pages.Main.openInstance(Static.route.peek());

                            JOptionPane.showMessageDialog(new JFrame(), "Car Deleted! Please Refresh the page");
//
                        } else if (c.getType().equalsIgnoreCase("Sports")) {

                            System.out.println("Before");
                            Pages.Main.closeInstance(Static.route.peek());
                            stmt.executeQuery("DELETE FROM sports WHERE sports.car_id = '" + c.getCar_id() + "'");
                            stmt.executeQuery("DELETE FROM car WHERE name = '" + getText() + "'");
                            stmt.executeQuery("DELETE FROM picture WHERE picture_id = " + c.getCar_picture().getPicture_id());
                            System.out.println("After");
                            Pages.Main.openInstance(Static.route.peek());

                            JOptionPane.showMessageDialog(new JFrame(), "Car Deleted! Please Refresh the page");
//
                        } else if (c.getType().equalsIgnoreCase("Jeep")) {

                            Pages.Main.closeInstance(Static.route.peek());
                            stmt.executeQuery("DELETE FROM jeep WHERE jeep.car_id = '" + c.getCar_id() + "'");
                            stmt.executeQuery("DELETE FROM car WHERE name = '" + getText() + "'");
                            stmt.executeQuery("DELETE FROM picture WHERE picture_id = " + c.getCar_picture().getPicture_id());
                            Pages.Main.openInstance(Static.route.peek());

                            JOptionPane.showMessageDialog(new JFrame(), "Car Deleted! Please Refresh the page");
//
                        } else if (c.getType().equalsIgnoreCase("Hatch Back")) {

                            Pages.Main.closeInstance(Static.route.peek());
                            stmt.executeQuery("DELETE FROM hatch_back WHERE hatch_back.car_id = '" + c.getCar_id() + "'");
                            stmt.executeQuery("DELETE FROM car WHERE name = '" + getText() + "'");
                            stmt.executeQuery("DELETE FROM picture WHERE picture_id = " + c.getCar_picture().getPicture_id());
                            Pages.Main.openInstance(Static.route.peek());

                            JOptionPane.showMessageDialog(new JFrame(), "Car Deleted! Please Refresh the page");
//
                        } else if (c.getType().equalsIgnoreCase("Sedan")) {

                            Pages.Main.closeInstance(Static.route.peek());
                            stmt.executeQuery("DELETE FROM Sedan WHERE sedan.car_id = '" + c.getCar_id() + "'");
                            stmt.executeQuery("DELETE FROM car WHERE name = '" + getText() + "'");
                            stmt.executeQuery("DELETE FROM picture WHERE picture_id = " + c.getCar_picture().getPicture_id());
                            Pages.Main.openInstance(Static.route.peek());

                            JOptionPane.showMessageDialog(new JFrame(), "Car Deleted! Please Refresh the page");
//
                        }


                    } catch (Exception ex) {
                        System.out.println("Error <delete car Products> ....");
                    }
                } else {
                    System.out.println(c.getCar_id());
                    System.out.println(c.getType());
                    //System.out.println("Just Tapped that!");
                    System.out.println(c.getCar_picture().getPicture_address());
                    Pages.Main.closeInstance(Static.route.peek());
                    Pages.Add_Product add_product_page = new Pages.Add_Product(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Modify Product", Static.current_user);
                    add_product_page.getP_name().setText(c.getCar_name());
                    add_product_page.getP_name().disable();

                    add_product_page.getP_price().setText(c.getCar_price() + "");
                    add_product_page.getP_color().setText(c.getCar_color());
                    add_product_page.getP_qty().setText(c.getCar_quantity() + "");
                    add_product_page.getP_type().setText(c.getType());
                    add_product_page.setSelectedPic(c.getCar_picture());
                    add_product_page.addPic();
                    add_product_page.getAdd().setEnabled(false);
                    add_product_page.addCoupe();
                    Add_Product.pg_modify = true;
                    add_product_page.setVisible(true);
                    Static.route.push(add_product_page);
                }
            }
        } else if (e.getSource() == this.button && this.button.getX() == 1144 && this.button.getY() == 576 && (Static.route.peek() instanceof Pages.Add_Product)) {

            // Closes the previous screen and opens up the new screen
            // private Textfield p_name, p_qty, p_type, p_color, p_price;
            String P_name = ((Add_Product) Static.route.peek()).getProductNameText();
            String P_qty = ((Add_Product) Static.route.peek()).getProducQuantityText();
            String P_type = ((Add_Product) Static.route.peek()).getProducTypeText();
            String P_color = ((Add_Product) Static.route.peek()).getProducColorText();
            String P_price = ((Add_Product) Static.route.peek()).getProducPriceText();
            boolean isCoupe = ((Add_Product) Static.route.peek()).isCoupe();
            Backend.Picture p_picture = ((Add_Product) Static.route.peek()).getSelectedPic();


            // Gonna find those
            int p_type_id = 0;
            String P_id = "";
            String P_company = null;
            // Checking to whom we want to add or modify the products!
            //String manu_cnic = null;

            boolean bool_suv = true, bool_sedan = true, bool_sports = true, bool_jeep = true, bool_hatch = true;

            // SELECT company_name FROM manufacturer where cnic = '3123456789012'

            // Check Car type
            Boolean carType = true, qty = true;
            if (!(P_type.equalsIgnoreCase("SUV") || P_type.equalsIgnoreCase("Sedan") || P_type.equalsIgnoreCase("Sports") || P_type.equalsIgnoreCase("Jeep") || P_type.equalsIgnoreCase("Hatch Back"))) {
                carType = false;
                JOptionPane.showMessageDialog(new JFrame(), "Enter one of following choices : SUV, SEDAN, SPORTS, HATCH BACK, JEEP");
            }

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");


                // Connection Code!
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

                Statement stmt = con.createStatement();

                // Checks for SUV
                while (bool_suv) {
                    p_type_id = (int) (Math.random() * 1000);
                    ResultSet rs = stmt.executeQuery("SELECT id FROM suv where id = " + p_type_id);
                    while (rs.next()) {
                        if (rs.getInt(1) == p_type_id) {
                            p_type_id = (int) (Math.random() * 1000);
                            bool_suv = false;
                        }
                    }
                    bool_suv = !bool_suv;
                }
                // Checks for Sedan
                while (bool_sedan) {
                    p_type_id = (int) (Math.random() * 1000);
                    ResultSet rs = stmt.executeQuery("SELECT id FROM suv where id = " + p_type_id);
                    while (rs.next()) {
                        if (rs.getInt(1) == p_type_id) {
                            p_type_id = (int) (Math.random() * 1000);
                            bool_sedan = false;
                        }
                    }
                    bool_sedan = !bool_sedan;
                }
                // Checks for Sports
                while (bool_sports) {
                    p_type_id = (int) (Math.random() * 1000);
                    ResultSet rs = stmt.executeQuery("SELECT id FROM suv where id = " + p_type_id);
                    while (rs.next()) {
                        if (rs.getInt(1) == p_type_id) {
                            p_type_id = (int) (Math.random() * 1000);
                            bool_sports = false;
                        }
                    }
                    bool_sports = !bool_sports;
                }
                // Checks for Hatch
                while (bool_hatch) {
                    p_type_id = (int) (Math.random() * 1000);
                    ResultSet rs = stmt.executeQuery("SELECT id FROM suv where id = " + p_type_id);
                    while (rs.next()) {
                        if (rs.getInt(1) == p_type_id) {
                            p_type_id = (int) (Math.random() * 1000);
                            bool_hatch = false;
                        }
                    }
                    bool_hatch = !bool_hatch;
                }
                // Checks for Jeeps
                while (bool_jeep) {
                    p_type_id = (int) (Math.random() * 1000);
                    ResultSet rs = stmt.executeQuery("SELECT id FROM suv where id = " + p_type_id);
                    while (rs.next()) {
                        if (rs.getInt(1) == p_type_id) {
                            p_type_id = (int) (Math.random() * 1000);
                            bool_jeep = false;
                        }
                    }
                    bool_jeep = !bool_jeep;
                }
                // If it is one of those 5 car types
                if (P_qty.matches(".*[a-zA-Z]+.*") || !(Integer.parseInt(P_qty) >= 0 && Integer.parseInt(P_qty) <= 100)) {
                    ((Add_Product) (Static.route.peek())).getP_qty().setBorder(Color.RED);
                    qty = false;
                } else {
                    ((Add_Product) (Static.route.peek())).getP_qty().setBorder(Color.GREEN);
                }

                boolean car_name_c = true;

                if (!((Add_Product) Static.route.peek()).pg_modify) {

                    ResultSet comp = stmt.executeQuery("SELECT NAME FROM CAR WHERE NAME = '" + P_name + "'");
                    while (comp.next()) {
                        if (comp.getString(1).equalsIgnoreCase(P_name)) {

                            car_name_c = false;
                            ((Add_Product) (Static.route.peek())).getP_name().setBorder(Color.RED);
                        }
                    }
                    if (car_name_c) {
                        ((Add_Product) (Static.route.peek())).getP_name().setBorder(Color.GREEN);

                    }
                }


                if (carType && qty && car_name_c) {

                    ((Add_Product) (Static.route.peek())).getP_type().setBorder(Color.GREEN);
                    ((Add_Product) (Static.route.peek())).getP_qty().setBorder(Color.GREEN);
                    ((Add_Product) (Static.route.peek())).getP_name().setBorder(Color.GREEN);

                    // Means an existing product is being modified!
                    if (((Add_Product) Static.route.peek()).pg_modify) {
                        //  Update car set color = 'Grey', quantity = 7, coupe = 1, price = 10000 where name = 'Tesla Model X'
                        if (isCoupe) {
                            stmt.executeQuery("Update car set color = '" + P_color + "', quantity = " + Integer.parseInt(P_qty) + ", coupe = " + 1 + ", price = " + Integer.parseInt(P_price) + " where name = '" + P_name + "'");
                            JOptionPane.showMessageDialog(new JFrame(), P_name + " has been Modified");
                            ((Add_Product) Static.route.peek()).pg_modify = false;
                        } else {
                            stmt.executeQuery("Update car set color = '" + P_color + "', quantity = " + Integer.parseInt(P_qty) + ", coupe = " + 0 + ", price = " + Integer.parseInt(P_price) + " where name = '" + P_name + "'");
                            JOptionPane.showMessageDialog(new JFrame(), P_name + " has been Modified");
                            ((Add_Product) Static.route.peek()).pg_modify = false;
                        }
                    } else {

                        System.out.println("CNIC" + Static.CNIC);
                        // Checking if Manufacturer has that CNIC
                        ResultSet rs = stmt.executeQuery("SELECT Company_name FROM manufacturer where cnic = '" + Static.CNIC + "'");
                        //                  System.out.println(iden);

                        while (rs.next()) {
                            P_company = rs.getString(1);
                        }

                        System.out.println("Company " + P_company);

                        if (P_company != null) {

                            if (P_type.equalsIgnoreCase("SUV")) {
                                P_id = "SV-" + p_type_id;

                            } else if (P_type.equalsIgnoreCase("Sedan")) {
                                P_id = "SD-" + p_type_id;

                            } else if (P_type.equalsIgnoreCase("Sports")) {
                                P_id = "SP-" + p_type_id;

                            } else if (P_type.equalsIgnoreCase("Jeep")) {
                                P_id = "JP-" + p_type_id;

                            } else if (P_type.equalsIgnoreCase("Hatch Back")) {
                                P_id = "HB-" + p_type_id;

                            }

                            // public Car(int car_price, int car_quantity, int carTypeID, String car_id, String car_color, String car_company, String car_name, String type, Boolean isCoupe, Picture car_picture) {
                            //

                            Boolean id_bool = true;
                            int picture_id = 0;
                            while (id_bool) {
                                picture_id = (int) (Math.random() * 1000);
                                ResultSet rs2 = stmt.executeQuery("SELECT PICTURE_ID FROM Picture where PICTURE_ID = " + picture_id);
                                while (rs2.next()) {
                                    if (rs2.getInt(1) == picture_id) {
                                        picture_id = (int) (Math.random() * 1000);
                                        id_bool = false;
                                    }
                                }
                                id_bool = !id_bool;
                            }
                            System.out.println("before insertion");
                            p_picture.setPicture_id(picture_id);
                            Backend.Car product = new Backend.Car(Integer.parseInt(P_price), Integer.parseInt(P_qty), p_type_id, P_id, P_color, P_company, P_name, P_type, isCoupe, p_picture);
                            InsertSQL.insert(product, stmt);

                            JOptionPane.showMessageDialog(new JFrame(), "Car Added!");
//                            Pages.Main.closeInstance(Static.route.peek());
//                            Pages.Company_HomeScreen company_home_page = new Pages.Company_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, Static.current_user);
//                            company_home_page.setVisible(true);
//                            Static.route.push(company_home_page);

                        }
                    }
                } else if (carType) {
                    ((Add_Product) (Static.route.peek())).getP_type().setBorder(Color.GREEN);
                } else if (!carType) {
                    ((Add_Product) (Static.route.peek())).getP_type().setBorder(Color.RED);
                    //((Add_Product)(Static.route.peek())).getP_qty().setBorder(Color.RED);
                } else if (!qty) {
                    ((Add_Product) (Static.route.peek())).getP_qty().setBorder(Color.RED);

                } else {
                    ((Add_Product) (Static.route.peek())).getP_name().setBorder(Color.RED);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Complete All Credentials");
                System.out.println("Error Add Product........");
            }
            // get the data confirm and show a popup menu added and return to the stats page

            /*// Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            Pages.User_HomeScreen user_home_page = new Pages.User_HomeScreen(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, Pages.Main.screen_Image_Location, "Abdullah");
            user_home_page.setVisible(true);
            Static.route.push(user_home_page);*/
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("+ View All Users")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            AdminUsers.isReport = false;

            AdminUsers admin_users_page = new AdminUsers(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Users", Static.current_user);
            admin_users_page.setVisible(true);
            Static.route.push(admin_users_page);
        } else if (e.getSource() == this.button && this.button.getText().equalsIgnoreCase("+ View All Companies")) {
            // Closes the previous screen and opens up the new screen
            Pages.Main.closeInstance(Static.route.peek());
            AdminUsers.isReport = false;

            AdminUsers admin_users_page = new AdminUsers(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, "Companies", Static.current_user);
            admin_users_page.setVisible(true);
            Static.route.push(admin_users_page);
        }
        else if (e.getSource() == this.button && this.button.getX() == 1170 && this.button.getY() == 60 &&(Static.route.peek() instanceof Pages.ListCars)) {
            System.out.println("HERERERE");

            if (this.button.getText().equalsIgnoreCase("C")){

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Pop the searched Page
                Static.route.pop();
                ListCars.searchCheck = false;

                // open orignal (unsearched page)
                Pages.Main.openInstance(Static.route.peek());

            }
            else{
                // Get Searched Text
                Pages.ListCars c = (Pages.ListCars) Static.route.peek();
                String searchCar = c.getSearchFieldText();
                String carText = c.getCarText();

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Make the static variable true for state changes
                ListCars.searchCheck = true;

                // Create a new instance of the page
                Pages.ListCars list_cars_page = new Pages.ListCars(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, carText + "-" + searchCar, Static.current_user);
                list_cars_page.setVisible(true);
                Static.route.push(list_cars_page);
            }

        }
        else if (e.getSource() == this.button && this.button.getX() == 1170 && this.button.getY() == 60 &&(Static.route.peek() instanceof Pages.ListProducts)) {
            System.out.println("HERERERE prod");

            if (this.button.getText().equalsIgnoreCase("C")){

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Pop the searched Page
                Static.route.pop();
                ListProducts.searchCheck = false;

                // open orignal (unsearched page)
                Pages.Main.openInstance(Static.route.peek());

            }
            else{
                // Get Searched Text
                Pages.ListProducts c = (Pages.ListProducts) Static.route.peek();
                String searchCar = c.getSearchFieldText();
                String carText = c.getCarText();

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Make the static variable true for state changes
                ListProducts.searchCheck = true;

                // Create a new instance of the page
                Pages.ListProducts list_product_page = new Pages.ListProducts(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, carText + "-" + searchCar, Static.current_user);
                list_product_page.setVisible(true);
                Static.route.push(list_product_page);
            }

        }
        else if(e.getSource() == this.button && this.button.getX() == 982 && this.button.getY() == 60){
            System.out.println("here");
            Pages.Main.closeInstance(Static.route.peek());
            Pages.AdminUsers c = (Pages.AdminUsers) Static.route.peek();
            String personName = c.getEntity();

            // Pop the searched Page
            Static.route.pop();
            AdminUsers.isReport = true;

            AdminUsers adminUsers = new AdminUsers(Main.screen_Width, Main.screen_Height, Main.screen_Title, Main.screen_Image_Location, personName, Static.current_user);
            adminUsers.setVisible(true);
            Static.route.push(adminUsers);

            Pages.Main.openInstance(Static.route.peek());

            System.out.println(AdminUsers.isReport);

        }
        else if (e.getSource() == this.button && this.button.getX() == 1170 && this.button.getY() == 60 &&(Static.route.peek() instanceof Pages.Store)) {
            System.out.println("HERERERE Store");

            if (this.button.getText().equalsIgnoreCase("C")){

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Pop the searched Page
                Static.route.pop();
                Store.searchCheck = false;

                // open orignal (unsearched page)
                Pages.Main.openInstance(Static.route.peek());

            }
            else{
                // Get Searched Text
                Pages.Store c = (Pages.Store) Static.route.peek();
                String searchCar = c.getSearchFieldText();
                String carText = c.getText();

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Make the static variable true for state changes
                Store.searchCheck = true;

                // Create a new instance of the page
                Pages.Store store_page = new Pages.Store(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, carText + "-" + searchCar, Static.current_user);
                store_page.setVisible(true);
                Static.route.push(store_page);
            }

        }
        else if (e.getSource() == this.button && this.button.getX() == 1170 && this.button.getY() == 60 &&(Static.route.peek() instanceof Pages.AdminUsers)) {
            System.out.println("HERERERE Store");

            if (this.button.getText().equalsIgnoreCase("C")){

                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Pop the searched Page
                Static.route.pop();
                AdminUsers.searchCheck = false;
                AdminUsers.isReport = false;


                // open orignal (unsearched page)
                Pages.Main.openInstance(Static.route.peek());

            }
            else{
                // Get Searched Text
                Pages.AdminUsers c = (Pages.AdminUsers) Static.route.peek();
                String searchEntity = c.getSearchFieldText();
                String personName = c.getEntity();


                // Close the page
                Pages.Main.closeInstance(Static.route.peek());

                // Make the static variable true for state changes
                AdminUsers.searchCheck = true;
                AdminUsers.isReport = false;




                // Create a new instance of the page
                Pages.AdminUsers store_page = new Pages.AdminUsers(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, personName + ">" + searchEntity, Static.current_user);
                store_page.setVisible(true);
                store_page.getDownload().setEnabled(false);
                Static.route.push(store_page);
            }

        }
        else if(e.getSource() == this.button && button.getX() == 1170 && button.getY() == 60 && (Static.route.peek() instanceof User_HomeScreen)){
            System.out.println("here");
            Pages.Main.closeInstance(Static.route.peek());

            // Pop the searched Page
            Static.route.pop();
            User_HomeScreen.isReport = true;

            User_HomeScreen user_home_page = new User_HomeScreen(Main.screen_Width, Main.screen_Height, Main.screen_Title, Main.screen_Image_Location, Main.screen_Image_Location, Static.current_user);
            user_home_page.setVisible(true);
            Static.route.push(user_home_page);

            Pages.Main.openInstance(Static.route.peek());

            System.out.println(User_HomeScreen.isReport);

        }
        else if(e.getSource() == this.button && button.getX() == 1170 && button.getY() == 60 && (Static.route.peek() instanceof Company_HomeScreen)){
            System.out.println("here");
            Pages.Main.closeInstance(Static.route.peek());

            // Pop the searched Page
            Static.route.pop();
            Company_HomeScreen.isReport = true;

            Company_HomeScreen companyHomeScreen = new Company_HomeScreen(Main.screen_Width, Main.screen_Height, Main.screen_Title, Main.screen_Image_Location, Main.screen_Image_Location, Static.current_user);
            companyHomeScreen.setVisible(true);
            Static.route.push(companyHomeScreen);

            Pages.Main.openInstance(Static.route.peek());

            System.out.println(Company_HomeScreen.isReport);

        }
        // Make sure this gets kept at last!
        else if (e.getSource() == this.button && (Static.route.peek() instanceof AdminUsers)) {
            // Closes the previous screen and opens up the new screen
            System.out.println(this.button.getText());
            String entity = ((AdminUsers) Static.route.peek()).getEntity();
            System.out.println(entity);
            String cnic = this.button.getText().split(" - ")[1];
            String name = this.button.getText().split(" - ")[0];
            Pages.Main.closeInstance(Static.route.peek());
            Pages.AdminStats admin_stats_page = new Pages.AdminStats(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location, name, cnic, entity);
            admin_stats_page.setVisible(true);
            Static.route.push(admin_stats_page);
        }

    }
}
