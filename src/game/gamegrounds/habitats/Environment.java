package game.gamegrounds.habitats;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class for enviroment element in game
 * Created by:
 * @author Sam Abdi
 */
public abstract class Environment extends Ground {
    public int eastChance;
    public int westChance;
    public int chance;
    /**
     * Set up character shows in display
     * @param displayChar character for each environment element
     */

    public Environment(char displayChar, int eastChance, int westChance){
        super(displayChar);
        this.eastChance=eastChance;
        this.westChance=westChance;
    }
    public Environment(char displayChar,int chance){
        super(displayChar);
        this.chance=chance;
    }
    public abstract void spawnEnemies(Location location);

    /**
     * Environment Random spawning in game map
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        spawnEnemies(location);
    }
}
