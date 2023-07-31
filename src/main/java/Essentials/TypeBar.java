package Essentials;
import javax.swing.*;

public class TypeBar {
    protected Button button;
    
    public TypeBar(JFrame frame, int x, int y, String imgName, String typeName){
        
        // Adding Side Image

        // Creating a new button
        this.button = new Button(false);
        if (typeName.equalsIgnoreCase("Hatch Back")){
            frame.add(button.newButton(x+20, y+97, 170, 47, typeName, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        }
        else{
            frame.add(button.newButton(x+20, y+97, 110, 47, typeName, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        }
        new Picture(frame, "E:\\School Projects\\DB Projects\\DB Mongo\\src\\main\\Cars\\"+imgName, x, y, 425, 164);
        button.setLightColor();
        

    }

    // Get Text of Company
    public String getText() {
        return this.button.getText();        
    }  
}
