import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pipe extends Sprites{

    public static final int DEFAULT_GAP = 150;
    public static final int DEFAULT_PIPE_WIDTH = 100;
    public static final int DEFAULT_PIPE_SPEED = -5;
    public static final int DEFAULT_PIPE_POINT = 100;

    private int gap;
    private int height;
    private int width;
    private int secondY;
    private int point;

    public Pipe(){
        this.color = new Color(100,100,100);
        this.gap = DEFAULT_GAP;
        this.height = Fenetre.WINDOW_HEIGHT/2 - gap/2;
        this.y = 0;
        this.secondY = y+height+gap;
        this.x = Fenetre.WINDOW_WIDTH - DEFAULT_PIPE_WIDTH - 20;
        this.width = DEFAULT_PIPE_WIDTH;
        this.speed = DEFAULT_PIPE_SPEED;
        this.point = DEFAULT_PIPE_POINT;
    }
    @Override
    public void draw(Graphics2D dessin) {
        dessin.setColor(color);
        dessin.fillRect(x,y,width,height);
        dessin.fillRect(x,secondY,width,Fenetre.WINDOW_HEIGHT-(secondY));
    }

    @Override
    public void move() {
        this.x += speed;

        if (x<=-width){
            reinit_pipe();
        }
    }

    private void reinit_pipe() {
        this.x = Fenetre.WINDOW_WIDTH;
        Random rand = new Random();

        this.height = rand.nextInt(30,400);
        this.gap = rand.nextInt(75,200);
        this.secondY = y+height+gap;
    }

    public ArrayList<Rectangle> giveRectangle() {
        ArrayList<Rectangle> listRectangle = new ArrayList<>();
        listRectangle.add(new Rectangle(x,y,width,height));
        listRectangle.add(new Rectangle(x,secondY,width,Fenetre.WINDOW_HEIGHT-secondY));
        return listRectangle;
    }

    public int hasPassed() {
        if (x+width<= 5){
            return point;
        }
        return 0;
    }
}
