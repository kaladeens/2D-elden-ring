package game.gameitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;
import game.characteristics.Consumable;
import game.characteristics.Resettable;
import game.gameactions.ConsumeAction;

/**
 *  Class Flask of Crimson Tears as Item
 *  Healing Item
 *  Created by
 *  @author Issac van der Vliet
 */
public class FlaskOfCrimsonTears extends Item implements Resettable , Consumable {
    private final static int HEAL_AMOUNT=250;
    private int quantity;
    private ResetManager resetManager = ResetManager.getInstance();

    /**
     * Constructor:
     * @param actor who hold the Flask of Crimson Tears
     */
    public FlaskOfCrimsonTears(Actor actor){
        super("Flask Of Crimson Tears", '6', false);
        resetManager.registerResettable(this);
        this.addAction(new ConsumeAction(actor,this));
        this.quantity = 2;
    }

    /**
     * Using the Flask of Crimson Tears, add 250 on hit point, minus one holding number
     * @param actor the actor who use Flask of Crimson Tears
     */
    @Override
    public void consume(Actor actor){
        actor.heal(HEAL_AMOUNT);
        this.quantity = this.quantity - 1;
        if (this.quantity == 0){
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Reset quantity of Flask of Crimson Tears, default is 2
     * @param actor who use Flask of Crimson Tears
     * @param map location that actor are
     */
    public void reset(Actor actor, GameMap map){
        if (actor.getItemInventory().contains(this)){
            this.quantity = 2;
        }else{
            actor.addItemToInventory(this);
            this.quantity = 2;
        }
    }

    /**
     * Get quantity of Flask of Crimson Tears
     * @return number of Flask of Crimson Tears
     */
    public int getQuantity() {
        return quantity;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
