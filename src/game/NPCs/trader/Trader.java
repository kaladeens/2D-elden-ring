package game.NPCs.trader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characteristics.Purchasable;
import game.gamecapabilities.Monetary;

import java.util.ArrayList;
import java.util.List;

public abstract class Trader extends Actor{
    private List<Purchasable> forPlayer = new ArrayList<>();

    /**
     * Constructor:
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super( name,  displayChar,  hitPoints);
    }

    // if it is selling action it is the action of the player selling to trader
    //and purchase the player purchasing from trader
//TODO
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
        return new DoNothingAction();
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
        ActionList actions = new ActionList();
        for (Purchasable purchasable : this.forPlayer) {
            actions.add(purchasable.getPurchaseAction(this));
        }

        return actions;
    }
//TODO
    /**
     *
     * @param weapon
     */
    public void addPurchasable(Purchasable purchase){
        forPlayer.add(purchase);
    }
//TODO
    /**
     *
     * @return
     */
    @Override
    public boolean isConscious(){
        return true;
    }
}
