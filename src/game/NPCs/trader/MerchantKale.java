package game.NPCs.trader;

import game.gamecapabilities.Monetary;
import game.gameweapons.Club;
import game.gameweapons.GreatKnife;
import game.gameweapons.Scimitar;
import game.gameweapons.Uchigatana;

/**
 * Merchant Kale aka seller in game
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class MerchantKale extends Trader {

    /**
     * Constructor:
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 10);
        this.addCapability(Monetary.BUYS_ITEMS);
        this.addPurchasable(new Club());
        this.addPurchasable(new GreatKnife());
        this.addPurchasable(new Scimitar());
        this.addPurchasable(new Uchigatana());
    }
}
