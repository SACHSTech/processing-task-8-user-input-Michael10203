import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

public class Sketch extends PApplet {
    //Image Variables 
    PImage ImgGabe;
    PImage ImgBackground;
    PImage ImgBug;
    PImage ImgFab;

    //Character Variables
    float fltcharacterX = 500;
    float fltcharacterY = 250;

    //Movement Variables 
    boolean boolUp = false;
    boolean boolRight = false;
    boolean boolDown = false;
    boolean boolLeft = false;
    boolean boolShift = false;

    public void settings() {
        size(1000, 800);
    }

    /*
    * Controls the Images 
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
   public void setup() {

    // Loading all the Images
    ImgBackground = loadImage("sky.jpg");
    ImgGabe = loadImage("Gabe.png");
    ImgFab = loadImage("Fab.png");
    ImgBug = loadImage("Bug.png");

     //Resizing The dimensions of the Image 
     resizeImage(ImgBackground, 1000, 800);
     resizeImage(ImgGabe, 400, 500);
     resizeImage(ImgFab, 50, 50); 
    }
    
    /*
    * Controls the Images Sizes
    * @author Michael liang
    * @param: None
    * @return: Void 
    */
    private void resizeImage(PImage img, int newWidth, int newHeight) {
        img.resize(newWidth, newHeight);
    }

    public void draw() {
    // Draw background image
        image(ImgBackground, 0, 0); 

        if (mousePressed) {
            // Draw the character image at the mouse position when mouse is clicked
            float mouseXPos = mouseX - ImgGabe.width / 2;
            float mouseYPos = mouseY - ImgGabe.height / 2;
            image(ImgGabe, mouseXPos, mouseYPos);
        } else {
            // Draw the character at its regular position
            image(ImgGabe, fltcharacterX, fltcharacterY);
            movement();
        }

        // Draw ImgFab when shift key is held down
        if (boolShift) {
            image(ImgFab, mouseX, mouseY);
        }
    }

    /*
    * Moves Character
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
    public void movement() {
        // Movement logic for the character
        if (boolUp) {
            fltcharacterY -= 3;
            fltcharacterY = max(0, fltcharacterY);
        }

        if (boolLeft) {
            fltcharacterX -= 3;
            fltcharacterX = max(0, fltcharacterX);
        }

        if (boolDown) {
            fltcharacterY += 3;
            fltcharacterY = min(height - ImgGabe.height, fltcharacterY);
        }

        if (boolRight) {
            fltcharacterX += 3;
            fltcharacterX = min(width - ImgGabe.width, fltcharacterX);
        }
    }

    /*
    * Checks if the key is Pressed  
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
    public void keyPressed() {
        // When Key is Pressed
        if (key == 'w') {
            boolUp = true;
        } else if (key == 'a') {
            boolLeft = true;
        } else if (key == 's') {
            boolDown = true;
        } else if (key == 'd') {
            boolRight = true;
        } else if (keyCode == SHIFT) {
            boolShift = true;
        }
    }

    /*
    * Check if the Key is Released  
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
    public void keyReleased() {
        // When Key is Released
        if (key == 'w') {
            boolUp = false;
        } else if (key == 'a') {
            boolLeft = false;
        } else if (key == 's') {
            boolDown = false;
        } else if (key == 'd') {
            boolRight = false;
        } else if (keyCode == SHIFT) {
            boolShift = false;
        }
    }

    /*
    * Check when the scroll wheel is in use
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
    public void mouseWheel(MouseEvent event) {
        // Handle mouse wheel to move character up and down
        float delta = event.getCount();
        fltcharacterY -= delta * 10; // Adjust the speed as needed
        fltcharacterY = constrain(fltcharacterY, 0, height - ImgGabe.height);
    }
    
    /*
    * When the Mouse is dragged  
    * @author Michael liang
    * @param: none
    * @return: Void 
    */
    public void mouseDragged() {
        image(ImgBug, ADD, AMBIENT);
    }

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
