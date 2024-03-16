package game.NPCs.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactions.spawningrelated.ReviveAction;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * Pile of Bones Enemy class
 * Created by:
 * @author Sam Abdi
 */

public class PileOfBones extends Enemy {
    private int counter=0;
    private Actor becomes;

    /**
     * Constructor:
     * @param becomes for when other enemy get killed
     */
    public PileOfBones(Actor becomes){
        super("Pile Of Bones",'X',1, Type.ISUNDEAD);
        this.getRunesManager().addEnemy(this);
        this.becomes=becomes;

    }

    /**
     * Get runes after destroy this bones
     * @return random int from 35-892
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(35,892);
        return output;
    }
//TODO:
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
        if (this.counter>=3){
            return new ReviveAction(becomes);
        }
        this.counter++;
        return new DoNothingAction();
    }


}
