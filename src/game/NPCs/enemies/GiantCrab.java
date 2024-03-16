package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * Giant Crab Enemy class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class GiantCrab extends Enemy {
    /**
     * Constructor:
     */
    public GiantCrab(){
        super("Giant Crab",'C',407, Type.ISCRUSTACEAN);
        this.addCapability(AttackType.AREA);
        this.addCapability(AttackType.SPECIAL);
        this.getRunesManager().addEnemy(this);

    }

    /**
     * Get runes after kill this enemy
     * @return random int from 318-4961
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(318,4961);
        return output;
    }

    /**
     * Set up weapon for this enemy
     * @return special intrinsic weapon
     *          Damage 208, Verb "slams", Hit rate 90
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }
}
