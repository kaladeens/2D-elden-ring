package game.gamegrounds.sitesofgrace;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.managers.ResetManager;
import game.managers.SpawnManager;
import game.gameactions.RestAction;
import game.gamecapabilities.Status;

/**
 * Site of Lost Grace Environment element class
 * Created by:
 * @author Issac van der Vliet
 * Modify by: Sam Abdi
 */
public abstract class SiteOfLostGrace extends Ground {

    private ResetManager resetManager = ResetManager.getInstance();

    private SpawnManager spawnManager = SpawnManager.getInstance();

    private String name;
//TODO
    /**
     * Constructor:
     * @param name
     * @param displayChar Display 'U'
     */
    public SiteOfLostGrace(String name, char displayChar){
        super('U');
        this.name=name;
        this.addCapability(Status.RESETTABLE);
    }
//TODO
    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        actions.add(new RestAction(actor,this));
        return actions;
    }
//TODO
    /**
     *
     * @param actor
     * @param map
     */
    public void rest(Actor actor, GameMap map){
        spawnManager.run(actor,map);
        resetManager.run(actor, map);
    }

}
