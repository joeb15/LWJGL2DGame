package guis;

import core.Main;
import org.joml.Vector2f;

/**
 * Created by joeba on 5/6/2017.
 */
public class TestGui extends Gui{

    public TestGui(){
        super("./res/sun.png", new Vector2f(0, Main.HEIGHT-Main.WIDTH/20), new Vector2f(Main.WIDTH, Main.WIDTH/20));
    }

    @Override
    public boolean onClick(Vector2f pos, boolean isClicked) {
        return isClicked && pos.x < Main.WIDTH/2;
    }
}
