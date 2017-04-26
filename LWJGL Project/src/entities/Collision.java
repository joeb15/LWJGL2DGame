// collision detections

package entities;

import org.joml.Vector2f

public class Collision {

	public static boolean collision(Entity a, Entity b) {
		double maxXA = a.getPos().x + (a.getSize().x / 2);
		double minXA = maxXA - a.getSize().x;
		double maxXB = b.getPos().x + (b.getSize().x / 2);
		double minXB - maxYB - b.getSize().x;

		double maxYA = a.getPos().y + (a.getSize().y / 2);
		double minYA = maxYA - a.getSize().y;
		double maxYB = b.getPos().y + (b.getSize().y / 2);
		double minYB - maxYB - b.getSize().y;

		return (minXA < maxXB && maxXA > minXB && minYA > maxYB && maxYA < minYB)
	}
}






