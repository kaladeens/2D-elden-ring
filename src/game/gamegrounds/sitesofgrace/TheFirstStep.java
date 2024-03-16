package game.gamegrounds.sitesofgrace;
/**
 * Puddle of Water Environment element class
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class TheFirstStep extends SiteOfLostGrace{

    private String name = "The First Step";

    /**
     * Constructor:
     * Display 'U' as The First Step
     */
    public TheFirstStep(){
        super("The First Step",'U');
    }

    /**
     * Get the name
     * @return String name of The first step
     */
    @Override
    public String toString() {
        return name;
    }
}
