package game.gameactions.spawningrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.NPCs.allies.Ally;
import game.NPCs.enemies.Invader;
import game.Utils.RandomNumberGenerator;
import game.combattypes.*;
//TODO
public class SummonAction extends Action{
    CombatArcheTypes combatType;
    Actor guest;
    Location location;
    public SummonAction(Location loaction){
        this.location = loaction;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        int randomNum = RandomNumberGenerator.getRandomInt(1,100);
        if(randomNum<=25){
            combatType = new Astrologer();
        }else if(randomNum<=50){
            combatType = new Bandit();
        }else if(randomNum<=75){
            combatType = new Samurai();
        }else{
            combatType = new Wretch();
        }
        if (RandomNumberGenerator.getBool(50)){
            guest = new Ally(combatType);
        }else{
            guest = new Invader(combatType);
        }
        if (!location.containsAnActor()){
            map.addActor(guest, location);
            return actor + " spawns an " + guest;
        }
        for (Exit exit : location.getExits()){
            Location destination = exit.getDestination();
            if (destination.canActorEnter(guest)){
                map.addActor(guest, destination);
                return actor + " spawns an " + guest;
            }
        }
        return "There is nowhere for a guest to spawn";

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can summon a guest from another realm";
    }
}
