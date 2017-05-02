// collision detections

package physics;

import entities.Entity;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Collision {

	public static boolean collision(Entity a, Entity b) {
        Vector3f aP = a.getPos();
        Vector3f bP = b.getPos();
        Vector3f aS = a.getSize();
        Vector3f bS = b.getSize();

        boolean collide = (Math.abs(aP.x-bP.x)<(aS.x+bS.x)) && (Math.abs(aP.y-bP.y)<(aS.y+bS.y)) && (Math.abs(aP.z-bP.z)<(aS.z+bS.z));

        if(collide){
            a.onCollide(b);
            b.onCollide(a);
        }
        return collide;
	}
}






