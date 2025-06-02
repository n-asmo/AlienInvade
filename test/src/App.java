import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/* THE MAIN APP */
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Alien Invade");
        frame.setSize(600, 600);
        frame.setLocation(0, 0);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new GamePanel());
        frame.setVisible(true);
    }
}

class GamePanel extends JPanel implements KeyListener{
    /* initial player settings */
    private int x = 275;
    private int y = 500;
    private int speed = 3;
    private int diagonalSpeed = 2;

    /* bullet settings */
    private int bulletSpeed = 10;

    /* initial game statistics stuff ig */
    private int score = 0;
    private int hp = 100;

    /* keyPressed booleans */
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean spacePressed = false;

    /* the thing that stores all the sprites yeah */
    ArrayList<Sprite> Sprites = new ArrayList<Sprite>();
    
    public GamePanel() {
        setBackground(Color.BLACK);
        Player p = new Player(x, y);
        Sprites.add(p);

        /* TEST (delete this part) */
        Alien a = new Alien(100, 300);
        Sprites.add(a);
        Bullet b = new Bullet(100, 400);
        Sprites.add(b);

        //dont delete this
        setFocusable(true);
        addKeyListener(this);

        //Update movement ~60 FPS
        Timer timer = new Timer(16, e -> {
            if (leftPressed){
                if (upPressed){
                    changeCoordinate("x", diagonalSpeed*-1);
                    changeCoordinate("y", diagonalSpeed*-1);
                } else if (downPressed){
                    changeCoordinate("x", diagonalSpeed*-1);
                    changeCoordinate("y", diagonalSpeed);
                } else changeCoordinate("x", speed*-1);
            }
            else if (rightPressed){
                if (upPressed){
                    changeCoordinate("x", diagonalSpeed);
                    changeCoordinate("y", diagonalSpeed*-1);
                } else if (downPressed){
                    changeCoordinate("x", diagonalSpeed);
                    changeCoordinate("y", diagonalSpeed);
                } else changeCoordinate("x", speed);
            }
            else if (upPressed) changeCoordinate("y", speed*-1);
            else if (downPressed) changeCoordinate("y", speed);
            if (spacePressed){
                Bullet bul = new Bullet(x, y);
                Sprites.add(bul);
            }

            p.updateX(x);
            p.updateY(y);

            repaint();
        });

        Timer timer2 = new Timer(300, e -> {
            for (int i=0; i<Sprites.size(); i++){
                Sprite s = Sprites.get(i);
                if (s.getClass() == Bullet.class) s.updateY(bulletSpeed*-1);
            }
        });

        timer.start();
        timer2.start();
    }

    private void changeCoordinate(String XorY, double amount){
        if (XorY.equals("x")){
            if (0 <= x+amount && x+amount <= 560) x += amount;
        }
        else if (0 <= y+amount && y+amount <= 520) y += amount;
    }

    /* RENDERING */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //background
        g.drawImage(new ImageIcon("src/background.gif").getImage(), 0, 0, 600, 600, this);
        //score shit
        g.setColor(Color.CYAN);
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("Score: "+score, 0, 20);
        //hp shit
        g.setColor(Color.GREEN);
        g.drawString("HP: "+hp, 0, 40);

        for (int i=0; i<Sprites.size(); i++){
            Sprite s = Sprites.get(i);
            g.drawImage(s.getSpriteImage(), s.getX(), s.getY(), s.getSize(), s.getSize(), this);
        }

    }

    /* When keys are pressed */

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) spacePressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) spacePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}
