package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.GiantDog;
import game.NPCs.enemies.LoneWolf;
import game.Utils.RandomNumberGenerator;
import game.gamecapabilities.LocationInMap;

/**
 * Gust of wind Environment element class
 * Created by:
 * @author Sam Abdi
 */
public class GustOfWind extends Environment{
    /**
     * Display '&' as Gust of Wind
     */
    public GustOfWind(){
        super('&',4,33);
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
                    location.addActor(new LoneWolf());
                }
            }
            else if (this.hasCapability(LocationInMap.EAST)){
                if (RandomNumberGenerator.getBool(eastChance)) {
                    location.addActor(new GiantDog());
                }
            }
        }
    }
}
