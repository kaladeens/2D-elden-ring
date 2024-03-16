package game.gameweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactions.combatrelated.AreaAttack;
import game.gamecapabilities.AttackType;
import game.characteristics.Sellable;
import game.gamecapabilities.Monetary;
import game.gameactions.moneyrelated.SellAction;

/**
 * Grossmesser as weapon that can be used to attack the enemy.
 * Created by:
 * @author Sam Abdi
 * Modified by:
 *
 */
public class Grossmesser extends WeaponItem implements Sellable {
    private Action sellAction;
    boolean near =false;

    /**
     * Constructor:
     */
    public Grossmesser(){
        super("Grossmesser", '?', 115, "Strikes", 85);
        this.addCapability(AttackType.AREA);
        this.addCapability(Monetary.SELLABLE);
        this.addCapability(AttackType.SPECIAL);
    }

    /**
     * Special attack action
     * @param holder weapon holder
     * @return Area attack
     */
    @Override
    public Action getSkill(Actor holder){
        return new AreaAttack(this);
    }

    /**
     * Weapon sell price
     * @return int 100
     */
    public int getSellPrice(){return 100;}
//TODO
    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void doSelling(Location currentLocation, Actor actor ){
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

    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        doSelling(currentLocation,actor);
        if (!this.near && this.sellAction!=null){
            this.removeAction(sellAction);
            sellAction=null;
        }
    }
}
