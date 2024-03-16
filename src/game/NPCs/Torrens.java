package game.NPCs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.characteristics.Mountable;
import game.gameactions.DismountAction;
import game.gameactions.MountAction;
import game.gamecapabilities.Status;
import game.gamecapabilities.Type;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CREATIVE FUNCTION
 * Torrent (Horse) class for actor
 * Created by:
 * @author Sam Abdi
 * Modify by: Isaac van der Vliet
 */

public class Torrens extends Actor implements Mountable {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final static int FOLLOW_HASH=2;
    private boolean active;
    private boolean tamed;
    private boolean mounted = false;
    private Actor owner;
    private static Torrens instance = null;

    /**
     * Constructors:
     */
    private Torrens(){
        super("Torrens the three-leg horse",'♞',1);
        this.addCapability(Type.MOUNTABLE);
        behaviours.put(999,new WanderBehaviour());
    }
//TODO
    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        setOwner(otherActor);
        ArrayList<Location> exitList = new ArrayList<>();
        behaviours.put(FOLLOW_HASH, new FollowBehaviour(otherActor));
        ActionList actions = new ActionList();
        if(!isMounted()){
            actions.add(new MountAction(this));
        }else{
            int i = 0;
            for(Exit exits : map.locationOf(otherActor).getExits()){
                List<Exit> furtherExits = exits.getDestination().getExits();
                Location destination = furtherExits.get(i).getDestination();
                if(destination.canActorEnter(otherActor)){
                    if(!exitList.contains(destination)){
                        exitList.add(destination);
                        actions.add(new MoveActorAction(destination,furtherExits.get(i).getName()+" 2 spaces"));
//                        actions.add(destination.getMoveAction(otherActor, furtherExits.get(i).getName()+" more", furtherExits.get(i).getHotKey()));
                        }
                    }
                i++;
            }
            actions.add(new DismountAction(this));
        }

        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isMounted()){
            Location location=map.locationOf(owner);
            for (Exit exit: location.getExits()){
                if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(owner)){
                    map.moveActor(this,exit.getDestination());
                    return new DoNothingAction();
                }
            }

        }
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public static Torrens getInstance(){
        if(instance==null){
            instance=  new Torrens();
        }
        return instance;
    }

    public void setOwner(Actor owner){
        this.owner=owner;
    }


    @Override
    public boolean isConscious() {
        return true;
    }
    @Override
    public ActionList getActions() {
        return null;
    }

    @Override
    public Actor getMount() {
        return this;
    }
    @Override
    public void setActive(boolean bool){
        this.active=bool;
    }
    @Override
    public boolean isActive(){
        return this.active;
    }
    @Override
    public boolean isMounted() {
        return mounted;
    }
    @Override
    public void setMounted(boolean mounted) {
        this.mounted = mounted;
    }
}
