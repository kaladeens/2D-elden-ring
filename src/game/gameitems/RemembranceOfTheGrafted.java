package game.gameitems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.characteristics.Exchangable;
import game.characteristics.Sellable;
import game.gameactions.moneyrelated.ExchangeAction;
import game.gameactions.moneyrelated.SellAction;
import game.gamecapabilities.Monetary;
import game.gameweapons.AxeOfGodrick;
import game.gameweapons.GraftedDragon;

import java.util.ArrayList;

/**
 * Remembrance of the Grafted Class
 * Dropped when Boss Godrick the Grafted died
 * Created by:
 * @author Sam Abdi
 */
public class RemembranceOfTheGrafted extends Item implements Sellable ,Exchangable{
    private Action sellAction;
    private ArrayList<Action> exchangeAction=new ArrayList<>();
    private boolean near;
    private boolean check;

    /**
     * Constructor:
     */
    public RemembranceOfTheGrafted(){
        super("Remembrance of the grafted",'O',true);
        this.addCapability(Monetary.SELLABLE);
        this.addCapability(Monetary.EXCHANGEABLE);
        this.setExchangeActions();
        this.setSellAction();
    }

    /**
     * Remembrance of the Grafted price in runes
     * @return runes price
     */
    public int getSellPrice(){return 20000;}

    /**
     * set sell action
     */
    public void setSellAction(){
        this.sellAction=new SellAction(this.getSellPrice(), this);
    }

    /**
     * What item can Remembrance of the Grafted exchanged
     */
    public void setExchangeActions(){
        exchangeAction.add(new ExchangeAction(this,new GraftedDragon()));
        exchangeAction.add(new ExchangeAction(this,new AxeOfGodrick()));
    }
//TODO
    /**
     *
     * @param currentLocation
     * @param actor
     */
    public void doSelling(Location currentLocation, Actor actor ){
        if (check && near){}
        else  {
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor new_actor = exit.getDestination().getActor();
                    if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                        Actor trader=new_actor;
                        this.addAction(sellAction);
                        if (trader.hasCapability(Monetary.EXCHANGES)){
                            for (Action action: exchangeAction){
                                this.addAction(action);
                            }
                        }
                    }
                }
            }
        }
        check =false;
        for (Exit exit : currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()) {
                Actor new_actor = exit.getDestination().getActor();
                if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                    check=true;
                    near =true;
                }
            }
        }
        if (!check){
            near=false;
        }
    }

    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */

    @Override
    public void tick(Location currentLocation, Actor actor) {
        doSelling(currentLocation,actor);
        if (!near){
            if (this.getAllowableActions().contains(sellAction)) {
                this.removeAction(sellAction);
            }
            if (this.getAllowableActions().contains(exchangeAction.get(0))) {
                for (Action action : exchangeAction) {
                    this.removeAction(action);
                }
            }
        }
    }

}
