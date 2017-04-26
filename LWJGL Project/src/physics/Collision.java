// collision detections

package physics;

import entities.Entity;
import org.joml.Vector2f;

public class Collision {

	public static boolean collision(Entity a, Entity b) {
        Vector2f aP = a.getPos();
        Vector2f bP = b.getPos();
        Vector2f aS = a.getSize();
        Vector2f bS = b.getSize();
        boolean collide = (Math.abs(aP.x-bP.x)<(aS.x+bS.x)) && (Math.abs(aP.y-bP.y)<(aS.y+bS.y));
        if(collide){
            a.onCollide(b);
            b.onCollide(a);
        }
        return collide;
	}
}






