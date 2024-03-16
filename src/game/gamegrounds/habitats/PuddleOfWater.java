package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.GiantCrab;
import game.NPCs.enemies.GiantCrayfish;
import game.Utils.RandomNumberGenerator;
import game.gamecapabilities.LocationInMap;

/**
 * Puddle of Water Environment element class
 * Created by:
 * @author Sam Abdi
 */
public class PuddleOfWater extends Environment{
    /**
     * Display '~' as Puddle of Water
     */
    public PuddleOfWater(){
        super('~',1,2);

    }
    /**
     * Random spawn enemies
     * @param location which on the map
     */
    @Override
    public void spawnEnemies(Location location) {
        if (!location.containsAnActor()){
            if (this.hasCapability(LocationInMap.WEST)) {
                if (RandomNumberGenerator.getBool(westChance)) {
                    location.addActor(new GiantCrab());
                }
            }
            else if (this.hasCapability(LocationInMap.EAST)){
                if (RandomNumberGenerator.getBool(eastChance)) {
                    location.addActor(new GiantCrayfish());
                }
            }
        }
    }
}

