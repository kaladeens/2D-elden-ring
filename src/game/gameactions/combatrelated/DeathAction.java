package game.gameactions.combatrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.ResetManager;
import game.gameactions.moneyrelated.ReturnRunesAction;
import game.gamecapabilities.Monetary;
import game.gamecapabilities.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    private ResetManager resetManager = ResetManager.getInstance();

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }
    public DeathAction(){}

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        if (attacker==null){
            return target + " has died";
        }
        String result = "";
        if (target.hasCapability(Status.RESPAWNABLE)){
            //might need to downcast
            return "\n"+new DieReviveAction().execute(target,map);
        }



        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        Location location = map.locationOf(target);
        map.removeActor(target);

        if (attacker.hasCapability(Monetary.RECIEVES_RUNES) && target.hasCapability(Monetary.DROPS_RUNES)){ // then they must be player
            result+=new ReturnRunesAction().execute(target,map);

        }

        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
