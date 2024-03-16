package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characteristics.Sleepable;

/**
 * A manager for spawn management
 * Created by:
 * @author Isaac van der Vliet
 */
public class SpawnManager {
    private Sleepable sleepable;
    private static SpawnManager instance = null;

    private SpawnManager(){}
//TODO
    /**
     *
     * @return
     */
    public static SpawnManager getInstance(){
        if (instance == null){
            instance = new SpawnManager();
        }
        return instance;
    }

    /**
     *
     * @param actor
     * @param map
     */
    public void run(Actor actor, GameMap map){
        sleepable.sleep(actor, map);
    }

    public void registerSleepbale(Sleepable sleepable){
        this.sleepable = sleepable;
    }
}
