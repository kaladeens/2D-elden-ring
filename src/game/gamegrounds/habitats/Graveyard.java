package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.HeavySkeletelSwordsman;
import game.NPCs.enemies.SkeletelBandit;
import game.Utils.RandomNumberGenerator;
import game.gamecapabilities.LocationInMap;

/**
 * Graveyard Environment element class
 * Created by:
 * @author Sam Abdi
 */
public class Graveyard extends Environment{
    /**
     * Display 'n' as Graveyard
     */
    public Graveyard(){
        super('n',27,27);
    }

    /**
     * Random spawn enemies in Graveyard
     * @param location on the map
     */
    @Override
    public void spawnEnemies(Location location) {
        if (!location.containsAnActor()){
            if (this.hasCapability(LocationInMap.WEST)) {
                if (RandomNumberGenerator.getBool(westChance)) {
                    location.addActor(new HeavySkeletelSwordsman());
                }
            }
            else if (this.hasCapability(LocationInMap.EAST)){
                if (RandomNumberGenerator.getBool(eastChance)) {
                    location.addActor(new SkeletelBandit());
                }
            }
        }
    }

}
