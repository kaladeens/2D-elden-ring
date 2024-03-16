package game.NPCs.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gamecapabilities.Type;
import game.gameitems.Runes;

/**
 * Dog enemy class
 * Created by:
 * @author Sam Abdi
 */
public class Dog extends Enemy{
    /**
     * Constructors:
     */
    public Dog(){
        super("Dog",'a',104, Type.FROMSTORMVEIL);
        this.getRunesManager().addEnemy(this);
    }

    /**
     * Runes drop from died dog
     * @return Random number between 52-1390
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(52,1390);
        return output;
    }

    /**
     * Attack weapon using
     * Damage 101 Hit rate 93%
     * @return Separate attack weapon Damage 101 Hit rate 93%
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }
}
