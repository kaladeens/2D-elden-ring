package game.gameactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.Torrens;
import game.characteristics.Mountable;

import java.util.ArrayList;

public class MountAction extends Action {
    private Mountable mount;
    public MountAction(Torrens mount){
        this.mount = mount;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        mount.setMounted(true);
        return actor + " has mounted " + mount;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can mount " + mount;
    }
}
