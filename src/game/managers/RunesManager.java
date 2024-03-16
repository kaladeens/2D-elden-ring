package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.enemies.Enemy;
import game.Player;
import game.gameitems.Runes;

import java.util.ArrayList;

/**
 *  Runes Manager Class
 *  Created by
 * @author Sam Abdi
 */
public class RunesManager {
    private static RunesManager instance =null;
    private Player player;
    private ArrayList<Enemy> enemies= new ArrayList<>();
    private Location locationOfRunes=null;

    /**
     * Constructor:
     */
    private RunesManager(){}

    /**
     * Get instance stage
     * @return instance stage
     */
    public static RunesManager getInstance(){
        if (instance ==null ){
            instance=new RunesManager();
        }
        return instance;
    }


    /**
     * Add runes to player
     * @param price that player should be given
     */
    public void addRunes(int price){
        player.getRunes().addRunes(price);
    }

    /**
     * Add runes from enemy to user
     * @param actor
     * @return get correct runes balance
     */
    public int addRunesFromEnemy(Actor actor){
        Runes amount=new Runes();
        for (Enemy enemy: enemies){
            if (enemy.getClass().getSimpleName().equals(actor.getClass().getSimpleName())){
                amount= enemy.getNewRunes();
                player.getRunes().addRunes(amount);
                return amount.getRuneBalance();
            }
        }
        return amount.getRuneBalance();
    }

    /**
     * Minus runes from Player
     * @param price that player should be taken away
     */
    public void minusRunes(int price){
        player.getRunes().minusRunes(price);
    }

    /**
     * Minus runes from Player
     * @param price that player should be taken away by the other constructors
     */
    public void minusRunes(Runes price){
        player.getRunes().minusRunes(price);
    }

    /**
     * Set runes location to spawn
     * @param location that we want
     */
    public void setRunesSpawn(Location location){this.locationOfRunes=location;}

    /**
     * Get location of runes
     * @return location
     */
    public Location getLocationOfRunes() {
        return locationOfRunes;
    }

    /**
     * Get player's runes balance
     * @return int player rune balance
     */
    public int getPlayerRunesBalance(){
        return this.player.getBalance();
    }

    /**
     * Set correct player
     * @param player correct actor
     */
    public void setPlayer(Player player){
        this.player=player;
    }

    /**
     * Set correct enemy
     * @param enemy correct enemy
     */
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
