package Game.GameStates;

import Game.Galaga.Entities.EntityManager;
import Game.Galaga.Entities.PlayerShip;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GalagaState extends State {

    public EntityManager entityManager;
    public String Mode = "Menu";
    private Animation titleAnimation;
    public int selectPlayers = 1;
    public int startCooldown = 60*7;//seven seconds for the music to finish

    public GalagaState(Handler handler){
        super(handler);
        refresh();
        int row = random.nextInt(4);
        int
        entityManager = new EntityManager(new PlayerShip(handler.getWidth()/2-64,handler.getHeight()- handler.getHeight()/7,64,64,Images.galagaPlayer[0],handler));
        titleAnimation = new Animation(256,Images.galagaLogo);
        entityManager = new EntityManager(new EnemyBee((handler.getWidth()/4) + (handler.getWidth()/2)/(8))+8,(handler.getHeight()/10)+8, 64, 64 ,handler, random.nextInt(4),random.nextINt(4)));
    }


    @Override
    public void tick() {
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
                entityManager.tick();
            }else{
                startCooldown--;

            }
        }else{
            titleAnimation.tick();
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selectPlayers=1;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selectPlayers=2;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Stage";
                handler.getMusicHandler().playEffect("Galaga.wav");

            }


        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(handler.getWidth()/4,0,handler.getWidth()/2,handler.getHeight());
        Random random = new Random(System.nanoTime());

        for (int j = 1;j < random.nextInt(15)+60;j++) {
            switch (random.nextInt(8)) {
                case 0:
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.BLUE);
                    break;
                case 2:
                    g.setColor(Color.YELLOW);
                    break;
                case 3:
                    g.setColor(Color.GREEN);
                    break;
                case 4:
                	g.setColor(Color.WHITE);
                	break;
                case 5:
                	g.setColor(Color.MAGENTA);
                	break;
                case 6:
                    g.setColor(Color.CYAN);
                    break;
                case 7:
                    g.setColor(Color.ORANGE);
                    break;
            }
            int randX = random.nextInt(handler.getWidth() - handler.getWidth() / 2) + handler.getWidth() / 4;
            int randY = random.nextInt(handler.getHeight());
            g.fillRect(randX, randY, 2, 2);

        }
        if (Mode.equals("Stage")) {
            g.setColor(Color.CYAN); // Changes in game High Score word color
            g.setFont(new Font("TimesRoman", Font.PLAIN, 62));
            g.drawString("HIGH",handler.getWidth()-handler.getWidth()/4,handler.getHeight()/16);
            g.drawString("SCORE",handler.getWidth()-handler.getWidth()/4+handler.getWidth()/48,handler.getHeight()/8);
            g.setColor(Color.WHITE); // Changes the in game High Score counter color
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()-handler.getWidth()/4+handler.getWidth()/48,handler.getHeight()/5);
            for (int i = 0; i< entityManager.playerShip.getHealth();i++) {
                g.drawImage(Images.galagaPlayer[0], (handler.getWidth() - handler.getWidth() / 4 + handler.getWidth() / 48) + ((entityManager.playerShip.width*2)*i), handler.getHeight()-handler.getHeight()/4, handler.getWidth() / 18, handler.getHeight() / 18, null);
            }
            if (startCooldown<=0) {
                entityManager.render(g);
            }else{
                g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
                g.setColor(Color.CYAN);// Sets the color of "Start" word when the game starts
                g.drawString("START!",handler.getWidth()/2-handler.getWidth()/18,handler.getHeight()/2);
                //Try to implement a ready set go fashion
            }
        }else{

            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));

            g.setColor(Color.BLUE);// Sets the Color for the High School in the game menu
            g.drawString("HIGH-SCORE:",handler.getWidth()/2-handler.getWidth()/18,32);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(handler.getScoreManager().getGalagaHighScore()),handler.getWidth()/2-32,64);

            g.drawImage(titleAnimation.getCurrentFrame(),handler.getWidth()/2-(handler.getWidth()/12),handler.getHeight()/2-handler.getHeight()/3,handler.getWidth()/6,handler.getHeight()/7,null);

            g.drawImage(Images.galagaCopyright,handler.getWidth()/2-(handler.getWidth()/8),handler.getHeight()/2 + handler.getHeight()/3,handler.getWidth()/4,handler.getHeight()/8,null);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            g.drawString("1   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2);
            g.drawString("2   PLAYER",handler.getWidth()/2-handler.getWidth()/16,handler.getHeight()/2+handler.getHeight()/12);
            if (selectPlayers == 1){
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2-handler.getHeight()/32,32,32,null);
            }else{
                g.drawImage(Images.galagaSelect,handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2+handler.getHeight()/18,32,32,null);
            }


        }
    }

    @Override
    public void refresh() {



    }
}
