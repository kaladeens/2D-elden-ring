package game.NPCs.enemies;

import game.gamecapabilities.Type;
import game.gameitems.Runes;
/**
 * Godrick Soldier Enemy Class
 * Created by:
 * @author Sam Abdi
 */
public class GodrickSoldier extends Enemy {
    /**
     * Constructor:
     */
    public GodrickSoldier(){
        super("Soldier of Godrick",'p',198, Type.FROMSTORMVEIL);
        this.getRunesManager().addEnemy(this);
//        addWeaponToInventory(new HeavyCrossbow());
    }

    /**
     * Get runes after kill this enemy
     * @return random int from 38-70
     */
    public Runes getNewRunes(){
        Runes output=new Runes();
        output.setRuneBalance(38,70);
        return output;
    }


}
