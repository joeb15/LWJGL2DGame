// collision detections

package entities;

import org.joml.Vector2f;

public class Collision {

	public static boolean collision(Entity a, Entity b) {
        Vector2f aS = a.getSize();
        Vector2f aP = a.getPos();
        Vector2f bS = b.getSize();
        Vector2f bP = b.getPos();
        return (Math.abs(aP.x - bP.x) < aS.x + bS.x) && (Math.abs(aP.y - bP.y) < aS.y + bS.y);
    }
}






