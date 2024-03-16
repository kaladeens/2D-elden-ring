package game.gameweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.characteristics.Sellable;
import game.gameactions.moneyrelated.SellAction;
import game.gamecapabilities.Monetary;

/**
 * Boss Weapon 2nd Stage
 * 89 Damage 90% hit rate
 * Created by:
 * @author Sam Abdi
 */
public class GraftedDragon extends WeaponItem implements Sellable{
    private Action sellAction;
    boolean near =false;
    private Action ExchangeAction;

    /**
     * Constructors:
     */
    public GraftedDragon(){
        super("Grafted Dragon",'N',89,"violates",90);
        this.addCapability(Monetary.SELLABLE);
        this.addCapability((Monetary.EXCHANGEABLE));
    }

    /**
     * Selling Price
     * @return 200 Int
     */
    public int getSellPrice(){return 200;}
//TODO
    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.hasCapability(Monetary.SELLABLE) && sellAction==null && !this.near ) {
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor new_actor = exit.getDestination().getActor();
                    if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                        Actor trader=new_actor;
                        this.sellAction=new SellAction(this.getSellPrice(), this);
                        this.addAction(sellAction);
                        this.near=true;
                    }
                }
            }
        }
        this.near=false;
        for (Exit exit : currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()) {
                Actor new_actor = exit.getDestination().getActor();
                if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                    this.near = true;
                }
            }
        }
        if (!this.near && this.sellAction!=null){
            this.removeAction(sellAction);
            sellAction=null;
        }
    }
}
