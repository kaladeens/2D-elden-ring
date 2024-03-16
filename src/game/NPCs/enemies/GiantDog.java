package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * Giant Dog Enemy Class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class GiantDog extends Enemy {
    /**
     * Constructor:
     */
    public GiantDog(){
        super("Giant Dog", 'G', 693, Type.ISCANINE);
        this.getRunesManager().addEnemy(this);
        this.addCapability(AttackType.AREA);
        this.addCapability(AttackType.SPECIAL);
    }

    /**
     * Get runes after kill this enemy
     * @return random int from 313-1808
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(313,1808);
        return output;
    }

    /**
     * Set up weapon for this enemy
     * @return special intrinsic weapon
     *          Damage 314, Verb "slams head", Hit rate 90
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams head", 90);
    }

}
