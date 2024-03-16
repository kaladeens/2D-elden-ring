package game.NPCs.trader;

import game.gamecapabilities.Monetary;
import game.gameitems.SpectralSteedWhistle;

/**
 * Melenia Trader Class
 * Allow exchange and buy Spectral Steed Whistle
 * Created by:
 * @author Sam Abdi
 */
public class Melenia extends Trader{
    public Melenia() {
        super("Maiden Melenia",'M',1);
        this.addPurchasable(new SpectralSteedWhistle());
        this.addCapability(Monetary.EXCHANGES);

    }
}
