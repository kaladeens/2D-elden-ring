package game.characteristics;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Mountable Interface
 * Created by:
 * @author Sam Abdi
 */

public interface Mountable {

    public void setActive(boolean bool);

    public boolean isActive();

    public void setOwner(Actor owner);

    public ActionList getActions();
    public Actor getMount();
    public void setMounted(boolean mounted);
    public boolean isMounted();
}
