package game.gameitems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.Torrens;
import game.characteristics.Mountable;
import game.characteristics.Purchasable;
import game.gameactions.spawningrelated.DespawnAction;
import game.gameactions.spawningrelated.SummonSteedAction;
import game.gameactions.moneyrelated.PurchaseAction;
import game.gamecapabilities.Status;
//TODO
/**
 *
 */
public class SpectralSteedWhistle extends Item implements Purchasable {

    private Mountable mount = Torrens.getInstance();
    private Action summonAction=new SummonSteedAction(mount);
    private Action despwanSteedAction=null;

    public SpectralSteedWhistle(){
        super("Spectral Steed Whistle",'‡',true);
        this.addAction(summonAction);
    }

    @Override
    public int getPurchasePrice() {
        return 20000;
    }

    @Override
    public Action getPurchaseAction(Actor trader) {
        return new PurchaseAction(trader,getPurchasePrice(),this);
    }

    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation,Actor actor) {
        if (actor.hasCapability(Status.RIDES)){
            mount.setOwner(actor);
        }
        if (!currentLocation.map().contains(mount.getMount()) && mount.isActive()){
            new DespawnAction(mount.getMount()).execute(mount.getMount(),currentLocation.map());
            summonAction=new SummonSteedAction(mount);
            this.addAction(summonAction);
        }
        if (currentLocation.map().contains(mount.getMount()) && summonAction==null){}
        else if(currentLocation.map().contains(mount.getMount()) && summonAction!=null){
            despwanSteedAction=new DespawnAction(mount.getMount());
            this.addAction(despwanSteedAction);
            mount.setActive(true);
            this.removeAction(summonAction);
            summonAction=null;

        }
        else{
            if (summonAction==null){
                summonAction=new SummonSteedAction(mount);
                this.addAction(summonAction);
            }
            if (despwanSteedAction!=null) {
                this.removeAction(despwanSteedAction);
            }
            despwanSteedAction=null;
            mount.setActive(false);
        }
    }
}
