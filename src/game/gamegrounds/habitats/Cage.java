package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.Dog;

import game.Utils.RandomNumberGenerator;

/**
 * Cage Environment element class
 * Created by:
 * @author Sam Abdi
 */
public class Cage extends Environment {
    /**
     * Display '<' as Cage
     * Chance 37%
     */
    public Cage(){
        super('<',37);
    }
    /**
     * Random spawn enemies around cage
     * @param location which on the map
     */
    @Override
    public void spawnEnemies(Location location) {
        if (!location.containsAnActor()){
            if (RandomNumberGenerator.getBool(chance)){
                location.addActor(new Dog());
            }
        }
    }
}
