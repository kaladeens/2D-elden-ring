package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.GodrickSoldier;
import game.Utils.RandomNumberGenerator;

/**
 * Barrack Environment element class
 * Created by:
 * @author Sam Abdi
 */
public class Barrack extends Environment {
    /**
     * Display 'B' as Barrack
     * Chance 45%
     */
    public  Barrack(){
        super('B',45);
    }

    /**
     * Random spawn enemies on Barrack
     * @param location which on the map
     */
    @Override
    public void spawnEnemies(Location location) {
        if (!location.containsAnActor()){
            if (RandomNumberGenerator.getBool(45)){
                location.addActor(new GodrickSoldier());
            }
        }
    }
}
