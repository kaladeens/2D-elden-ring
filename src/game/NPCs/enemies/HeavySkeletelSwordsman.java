package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.Type;
import game.gamecapabilities.Status;
import game.gameitems.Runes;
import game.gameweapons.Grossmesser;

/**
 * Heavy Skeletel Swordsman Enemy Class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class HeavySkeletelSwordsman extends Enemy  {
    //has the grossmesser

    /**
     *Constructor:
     */
    public HeavySkeletelSwordsman() {
        super("Heavy Skeletel Swordsman", 'q', 153, Type.ISUNDEAD);
        this.addWeaponToInventory(new Grossmesser());
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

    /**
     * Set up weapon for this enemy
     * @return special intrinsic weapon
     *          Damage 50, Verb "punches like mad ", Hit rate 95
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "punches like mad ", 95);
    }
}
