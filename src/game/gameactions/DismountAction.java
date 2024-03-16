package game.gameactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.NPCs.Torrens;
import game.characteristics.Mountable;

public class DismountAction extends Action {
    private Mountable mount;
    public DismountAction(Torrens mount){
        this.mount = mount;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        mount.setMounted(false);
        return actor + " has dismounted " + mount;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can dismount " + mount;
    }
}
