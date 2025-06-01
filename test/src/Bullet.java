public class Bullet extends Sprite{
    
    public Bullet(int xPosition, int yPosition){
        super(xPosition, yPosition, 20, "bullet.png");
    }

    public boolean hit(Player p){
        int playerX = p.getX();
        int playerY = p.getY();
        //finish this code pls

        return false; //ignore this
    }

}
