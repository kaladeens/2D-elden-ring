package game.gameitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.Utils.RandomNumberGenerator;
import game.gameactions.moneyrelated.PickUpRunesAction;
import game.managers.RunesManager;

/**
 * Runes class
 * Created by:
 * @author Sam Abdi
 */
public abstract class Rune extends Item {
    private int runeBalance;
    protected RunesManager runeManager =RunesManager.getInstance();

    public Rune (String name, char displayChar, boolean portable){
        super(name,displayChar,portable);

    }
    /**
     * Request how many runes left
     * @return the balance of runes
     */
    public int getRuneBalance(){
        return this.runeBalance;
    }

    /**
     * Random set the runes that actor hold (lower - higher)
     * @param lowerBound random number generator lower bound
     * @param upperBound random number generator higher bound
     */
    public void setRuneBalance(int lowerBound,int upperBound){
        this.runeBalance= RandomNumberGenerator.getRandomInt(lowerBound,upperBound);
    }

    /**
     * Add runes to the balance
     * @param amount the runes provide by other ways like kill enemy
     */
    public void addRunes(Runes amount){
        this.runeBalance+=amount.getRuneBalance();
    }

    /**
     * Add runes to the balance
     * @param amount int control by code
     */
    public void addRunes(int amount){this.runeBalance+=amount;}

    /**
     * Minus runes from the balance
     * @param amount lose from other ways like get killed
     */
    public void minusRunes(Runes amount){
        this.runeBalance-= amount.getRuneBalance();
    }

    /**
     * Minus runes from the balance
     * @param amount int control by code
     */
    public void minusRunes(int amount){
        this.runeBalance-= amount;
    }


}
