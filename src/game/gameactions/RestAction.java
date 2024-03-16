package game.gameactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gamegrounds.sitesofgrace.SiteOfLostGrace;

/**
 * Rest action class
 * Created by:
 * @author Issac van der Vliet
 */
public class RestAction extends Action {

    private Actor actor;
    private SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor:
     * @param actor who will have a rest
     * @param siteOfLostGrace location
     */
    public RestAction(Actor actor, SiteOfLostGrace siteOfLostGrace){
        this.actor = actor;
        this.siteOfLostGrace = siteOfLostGrace;
    }

    /**
     * The actor doing rest action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return rest message
     */
    @Override
    public String execute(Actor actor, GameMap map){
        siteOfLostGrace.rest(actor, map);
        String result = actor + " rests at " + siteOfLostGrace + " and restores to full health.";
        return result;
    }

    /**
     * Show message in menu
     * @param actor The actor performing the action.
     * @return Menu selection
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " rests at " + siteOfLostGrace;
    }
}
