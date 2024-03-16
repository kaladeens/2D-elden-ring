package game.gameitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.characteristics.Consumable;
import game.gameactions.ConsumeAction;
import game.gameactions.moneyrelated.PickUpRunesAction;

/**
 * GoldenRunes class
 * Created by:
 * @author Sam
 */
public class GoldenRunes extends Rune implements Consumable {
    /**
     * Constructor:
     * @param actor who held Golden runes
     */
    public GoldenRunes(Actor actor){
        super("Golden Rune", '*',true);
        this.setRuneBalance(200,10000);
        this.addAction(new ConsumeAction(actor,this));
    }

    /**
     * Consume action, exchange to runes
     * @param actor who held Golden runes
     */
    public void consume(Actor actor){
        actor.removeItemFromInventory(this);
        runeManager.addRunes(this.getRuneBalance());
    }

    /**
     * Get rune balance of Golden runes
     * @return string message
     */
    @Override
    public String toString(){
        return this.getRuneBalance()+ " Golden Runes";
    }

    @Override
    public Item getItem() {
        return this;
    }

}
