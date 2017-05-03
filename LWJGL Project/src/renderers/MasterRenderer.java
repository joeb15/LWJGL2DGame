package renderers;

import core.Camera;
import entities.Entity;
import guis.Gui;
import world.World;

import java.util.List;

/**
 * Created by joeba on 4/21/2017.
 */
public class MasterRenderer {

    private GuiRenderer guiRenderer;
    private EntityRenderer entityRenderer;
    private WorldRenderer worldRenderer;

    public MasterRenderer(){
        guiRenderer = new GuiRenderer();
        entityRenderer = new EntityRenderer();
        worldRenderer = new WorldRenderer();
    }

    public void render(World world, List<Entity> entities, List<Gui> guis, Camera camera) {
        worldRenderer.render(world, camera);
        entityRenderer.render(entities, camera);
        guiRenderer.render(guis);
    }
}
