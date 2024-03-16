package game.gameitems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.characteristics.Consumable;
import game.gameactions.ConsumeAction;
import game.gameactions.moneyrelated.ExchangeAction;
import game.gamecapabilities.Monetary;
import game.gamecapabilities.Status;


import java.util.ArrayList;

/**
 * Apple Item Class
 * Created by:
 * @author Sam Abdi
 */
public class Apple extends Item implements Consumable {
    private ArrayList<Action> exchangeAction=new ArrayList<>();
    private boolean near;
    private boolean check;

    /**
     * Constructor:
     * @param actor who will consume apple
     */
    public Apple(Actor actor){
        super("Apple",'σ',true);
        this.addCapability(Status.RIDES);
        this.addCapability(Monetary.EXCHANGEABLE);
        this.addAction(new ConsumeAction(actor,this));
        this.setExchangeActions();
    }

    /**
     * Add exchange action
     */
    public void setExchangeActions(){
        exchangeAction.add(new ExchangeAction(this,new SpectralSteedWhistle()));
    }

    /**
     * Use apple to heal 100 hp
     * @param actor who consume apple
     */
    @Override
    public void consume(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actor.heal(100);
            if (actor.getWeaponInventory().contains(this)) {
                actor.removeItemFromInventory(this);
            }
        }


    }
//TODO
    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation,Actor actor) {
        if (check && near){}
        else  {
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor new_actor = exit.getDestination().getActor();
                    if (new_actor.hasCapability(Monetary.EXCHANGES) && !new_actor.hasCapability(Monetary.BUYS_ITEMS)){
                        Actor trader=new_actor;
                        for (Action action: exchangeAction){
                            this.addAction(action);
                        }
                    }
                }
            }
        }
        check =false;
        for (Exit exit : currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()) {
                Actor new_actor = exit.getDestination().getActor();
                if (new_actor.hasCapability(Monetary.EXCHANGES) && !new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                    check=true;
                    near =true;
                }
            }
        }
        if (!check){
            near=false;
        }
        if (!near){
            if (this.getAllowableActions().contains(exchangeAction.get(0))) {
                for (Action action : exchangeAction) {
                    this.removeAction(action);
                }
            }
        }
    }

    @Override
    public Item getItem() {
        return this;
    }
}
