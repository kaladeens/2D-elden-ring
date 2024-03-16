package game.NPCs.enemies;

import game.gamecapabilities.Type;
import game.gamecapabilities.Status;
import game.gameitems.Runes;
import game.gameweapons.Scimitar;

/**
 * Skeletel Bandit Enemy Class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class SkeletelBandit extends Enemy {
    /**
     * Constructor:
     */
    public SkeletelBandit(){
        super("Skeletel Bandit",'b',184, Type.ISUNDEAD);
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(Status.RESPAWNABLE);

        this.getRunesManager().addEnemy(this);
    }

    /**
     * Get runes after kill this enemy
     * @return random int from 35-892
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(35,892);
        return output;
    }

}
