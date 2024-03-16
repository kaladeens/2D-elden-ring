package game.NPCs.trader;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gamecapabilities.Monetary;

/**
 * Finger Reader Enia Trader Class
 * Allow exchange and buy items
 * Created by:
 * @author Sam Abdi
 */
public class FingerReaderEnia extends Trader{
    public FingerReaderEnia(){
        super("Finger Reader Enia",'E',10);
        this.addCapability(Monetary.EXCHANGES);
        this.addCapability(Monetary.BUYS_ITEMS);
    }
}
