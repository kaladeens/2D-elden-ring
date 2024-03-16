package game.gameitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.gameactions.moneyrelated.PickUpRunesAction;
import game.managers.ResetManager;

/**
 * Runes as Item in the game (money)
 * Created by:
 * @author Sam Abdi
 *
 */
public class Runes extends Rune {

    private ResetManager resetManager = ResetManager.getInstance();

    /**
     * Constructor:
     * As item, shows '$', not portable
     */
    public Runes(){
        super("Runes",'$',false);
    }

    //TODO
    /**
     *
     * @param actor
     * @return
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new PickUpRunesAction(this);
    }



    /**
     * Read rune balance for check
     * @return message of rune balance
     */
    @Override
    public String toString(){
        return this.getRuneBalance() + " Runes";
    }

}
