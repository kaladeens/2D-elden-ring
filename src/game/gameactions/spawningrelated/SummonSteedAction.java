package game.gameactions.spawningrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.characteristics.Mountable;
//TODO
public class SummonSteedAction extends Action {
    private Mountable mount;
    public SummonSteedAction(Mountable mount){
        this.mount=mount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location=map.locationOf(actor);
        for (Exit exit: location.getExits()){
            Location destination=exit.getDestination();
            if (!destination.containsAnActor() && destination.canActorEnter(actor)){
                destination.addActor(mount.getMount());
                return mount + " has been brought in at " +exit.getName();
            }
        }
        return mount + " cannot spawn safely";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" can spawn "+ mount;
    }
}
