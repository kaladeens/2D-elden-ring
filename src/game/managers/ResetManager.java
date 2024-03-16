package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characteristics.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private List<Resettable> resettables = new ArrayList<>();
    private static ResetManager instance = null;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {}

    /**
     * If instance is null will change instance to reset manager
     * @return instance
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
        //ResetManager resetManager = new ResetManager();
        //return resetManager;
    }

    /**
     *
     * @param actor
     * @param map
     */
    public void run(Actor actor, GameMap map) {
        for (Resettable resettable : resettables){
            resettable.reset(actor, map);
        }
    }

    /**
     *
     * @param resettable
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     *
     * @param resettable
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}
