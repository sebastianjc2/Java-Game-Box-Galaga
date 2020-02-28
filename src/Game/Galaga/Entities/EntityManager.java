package Game.Galaga.Entities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 1/25/2020
 */
public class EntityManager {
	public boolean [] [] enemyPositions;
    public ArrayList<BaseEntity> entities;
    public PlayerShip playerShip;
    public EnemyBee enemyBee;
    public NewEnemy NewEnemy;

    public EntityManager(PlayerShip playerShip) {
        enemyPositions = new boolean[5][8];
    	entities = new ArrayList<>();
        this.playerShip = playerShip;
    }

    public void tick(){
        playerShip.tick();
        ArrayList<BaseEntity> toRemove = new ArrayList<>();
        for (BaseEntity entity: entities){
            if (entity.remove){
                toRemove.add(entity);
                continue;
            }
            entity.tick();
            if (entity.bounds.intersects(playerShip.bounds)){
                playerShip.damage(entity);
            }
        }
        for (BaseEntity toErase:toRemove){
            entities.remove(toErase);
        }

    }

    public void render(Graphics g){
        for (BaseEntity entity: entities){
            entity.render(g);
        }
        playerShip.render(g);

    }

}
