package game.NPCs.enemies;

import game.combattypes.CombatArcheTypes;
import game.gamecapabilities.Type;
import game.gameitems.Runes;
/**
 * Invader Enemy Class
 * Created by:
 * @author Issac van der Vliet
 * Modify by: Sam Abdi
 */
public class Invader extends Enemy{
    /**
     * Constructor:
     * @param combatType of invader
     */
    public Invader(CombatArcheTypes combatType){
        super("Invader",'ඞ',1, Type.FROMANOTHERREALM);
        this.getRunesManager().addEnemy(this);
        combatType.run(this);

    }

    /**
     * Get runes after kill this enemy
     * @return random int from 1358-5578
     */
    @Override
    public Runes getNewRunes() {
        Runes output = new Runes();
        output.setRuneBalance(1358,5578);
        return output;
    }
}
