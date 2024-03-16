package game.gameactions.spawningrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Despawned another actor action
 * Created by:
 * @author Sam Abdi
 */
public class DespawnAction extends Action {
    private Actor other;

    public DespawnAction(){}

    /**
     * Constructors:
     * @param other the target that
     */
    public DespawnAction(Actor other){
        this.other=other;
    }

    /**
     * Despawn other actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message to display with success message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (other !=null){
            actor=other;
        }
        map.removeActor(actor);
        return actor + " despawned";
    }

    /**
     * Show which actor could be despawned on menu
     * @param actor The actor performing the action.
     * @return menu message
     */
    @Override
    public String menuDescription(Actor actor){
        return other +" can be despawned";
    }
}
