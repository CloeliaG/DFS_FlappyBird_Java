import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Fenetre extends Canvas {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 600;

    Bird bird = new Bird();
    private ArrayList<Sprites> listeSprites = new ArrayList<>();
    private int score = 0;

    Fenetre() throws InterruptedException {

        JFrame fenetre = new JFrame("FlappyBird");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();

        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        setFocusable(true);

        KeyListener key = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { // if spacebar is pressed
                    bird.fly();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        addKeyListener(key);

        this.requestFocus();

        demarrer();

        panneau.removeAll();
        panneau.setLayout(new GridLayout(2, 1));
        panneau.add(new JLabel("GAME OVER",SwingConstants.CENTER));
        panneau.add(new JLabel("Score : " + score, SwingConstants.CENTER));

        panneau.revalidate();


    }


    public void demarrer() throws InterruptedException {
        Pipe pipe = new Pipe();
        listeSprites.add(bird);
        listeSprites.add(pipe);
        boolean notOver = true;

        while(notOver) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();
            //-----------------------------

            dessin.setColor(Color.white);
            dessin.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

            for (Sprites sprite : listeSprites){
                sprite.draw(dessin);
                sprite.move();
            }

            notOver = !bird.didItHit(pipe);
            score += pipe.hasPassed();

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Fenetre();
    }


}