package game.gameactions.moneyrelated;
/**
 * Actor pick up runes action
 * Created by:
 * @author Sam Abdi
 */

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameitems.Rune;
import game.managers.RunesManager;

public class PickUpRunesAction extends PickUpAction {
    private RunesManager runesManager=RunesManager.getInstance();
    private int price;

    /**
     * Get the runes from ground
     * @param runes the runes on the ground balance
     */
    public PickUpRunesAction(Rune runes){
        super(runes);
        price=runes.getRuneBalance();
    }

    /**
     * Add runes to actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result=super.execute(actor, map);
        runesManager.addRunes(price);
        result+="";
        return result;
    }

    /**
     * Show menu of picking up runes
     * @param actor The actor performing the action.
     * @return Message to menu
     */
    @Override
    public String menuDescription(Actor actor){
        return super.menuDescription(actor);
    }
}
