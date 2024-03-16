package game.Utils;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gamecapabilities.LocationInMap;
import game.gamecapabilities.Status;

import java.awt.*;

/**
 * Map Category utility
 * Created by:
 * @author Sam Abdi
 */
public class CategoriseMap {

    /**
     * Sort Maps
     * @param map which want to know
     * @return map category
     */
    public static Location sortMap(GameMap map){
        int x_length=map.getXRange().max();
        int mid=x_length/2;
        Location output=null;
        for( int y: map.getYRange()){
            for( int x : map.getXRange()){
                Location location = map.at(x, y);
                if (location.getGround()==null){
                    continue;
                }
                if (location.getGround().hasCapability(Status.RESETTABLE)){
                    output=location;
                }
                if (x<=mid){
                    location.getGround().addCapability(LocationInMap.WEST);
                }
                else{
                    location.getGround().addCapability(LocationInMap.EAST);
                }
            }
        }
        return output;
    }
}
