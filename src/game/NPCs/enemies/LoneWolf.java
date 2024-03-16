package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Sam Abdi
 *
 */
public class LoneWolf extends Enemy {
    /**
     * Constructor:
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, Type.ISCANINE);
        this.getRunesManager().addEnemy(this);
    }

    /**
     * Get runes after kill this enemy
     * @return random int from 55-1470
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(55,1470);
        return output;
    }

    /**
     * Set up weapon for this enemy
     * @return special intrinsic weapon
     *          Damage 97, Verb "bites", Hit rate 95
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
