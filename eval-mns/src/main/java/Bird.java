import java.awt.*;
import java.util.ArrayList;

public class Bird extends Sprites{

    public static final int DEFAULT_BIRD_SIZE = 30;
    public static final int DEFAULT_BIRD_SPEED = 2;

    private int rayon;

    public Bird(){
        this.color = new Color(255,120,150);
        this.x = 20;
        this.y = Fenetre.WINDOW_HEIGHT/2;
        this.rayon = DEFAULT_BIRD_SIZE;
        this.speed = DEFAULT_BIRD_SPEED;
    }
    @Override
    public void draw(Graphics2D dessin) {
        dessin.setColor(color);
        dessin.fillOval(x,y,rayon,rayon);
    }

    @Override
    public void move() {
        this.y += speed;
        speed += 0.2;

    }

    public void fly() {
        if (y >=5) {
            this.speed = -5;
        }
    }

    public boolean didItHit(Pipe pipe) {
        boolean hit = false;
        Rectangle rectangleBird = new Rectangle(x,y,rayon,rayon);
        ArrayList<Rectangle> rectanglePipe = pipe.giveRectangle();
        for (Rectangle rectangle : rectanglePipe){
            hit = rectangle.intersects(rectangleBird);
            if (hit){
                return hit;
            }
        }
        return (hit || y>=Fenetre.WINDOW_HEIGHT);
    }
}
