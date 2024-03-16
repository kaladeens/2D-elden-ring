package game.gameactions.moneyrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.RunesManager;

/**
 * Actor dropped runes action
 * Created by:
 * @author Sam Abdi
 */
public class ReturnRunesAction extends Action {

    private RunesManager runesManager = RunesManager.getInstance();
    public ReturnRunesAction(){}

    /**
     * Drop the runes on the ground after actor get killed.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Message of Runes Dropped
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int amount=runesManager.addRunesFromEnemy(actor);
        return actor + " dropped "+amount+" many runes";
    }

    /**
     * Return menu selection
     * @param actor The actor performing the action.
     * @return
     */
    public String menuDescription(Actor actor){
        return "";
    }
}
