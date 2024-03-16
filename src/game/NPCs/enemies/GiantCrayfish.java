package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * Giant Crayfish Enemy Class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class GiantCrayfish extends Enemy {
    /**
     * Constructor:
     */
    public GiantCrayfish(){
        super("Giant Crayfish", 'R', 4803, Type.ISCRUSTACEAN);
        this.getRunesManager().addEnemy(this);
        this.addCapability(AttackType.AREA);
        this.addCapability(AttackType.SPECIAL);
    }

    /**
     * Get runes after kill this enemy
     * @return random int from 500-2374
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(500,2374);
        return output;
    }

    /**
     * Set up weapon for this enemy
     * @return special intrinsic weapon
     *          Damage 527, Verb "slams with giant pincer", Hit rate 100
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams with giant pincer", 100);
    }

}
