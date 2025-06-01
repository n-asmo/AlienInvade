import java.awt.*;
import javax.swing.ImageIcon;

public class Sprite {
    private int xPosition;
    private int yPosition;
    private int size;
    private Image spriteImage;

    public Sprite(int xPosition, int yPosition, int size, String imageName){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.spriteImage = new ImageIcon("src/"+imageName).getImage();
    }

    public int getX(){
        return xPosition;
    }
    public int getY(){
        return yPosition;
    }
    public int getSize(){
        return size;
    }
    public Image getSpriteImage(){
        return spriteImage;
    }

    public void updateX(int xPosition){
        this.xPosition = xPosition;
    }
    public void updateY(int yPosition){
        this.yPosition = yPosition;
    }
    public void updateSpriteImage(String imageName){
        this.spriteImage = new ImageIcon("src/"+imageName).getImage();
    }
}