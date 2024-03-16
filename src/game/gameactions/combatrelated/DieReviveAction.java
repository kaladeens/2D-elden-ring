package game.gameactions.combatrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.NPCs.enemies.PileOfBones;
//TODO
/**
 * An action to revive
 */
public class DieReviveAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        map.removeActor(actor);
        //other approach is to down cast but as of right now we oly have this so go spawn this
        actor.heal(1000);

        location.addActor(new PileOfBones(actor));
        Actor target=location.getActor();
        for (Item item : actor.getItemInventory())
            target.addItemToInventory(item);
        for (WeaponItem weapon : actor.getWeaponInventory())
            target.addWeaponToInventory(weapon);
        //pass on its stuff to it
        return actor + " has died and became " + target;
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed and became a pile of bones" ;
    }
}
