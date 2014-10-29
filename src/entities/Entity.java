package entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {

	private float xPos;
	private float yPos;
	private Vector2f vector;
	private Shape boundingBox;
	private Image image;
	private float speedModifyer;

	public Entity(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.vector = vector;
		this.boundingBox = boundingBox;
		this.image = image;
		speedModifyer = 1;
	}

	public float getXPos() {
		return xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public Vector2f getVector() {
		return vector;
	}
	
	public float getVectorX(){
		return vector.getX();
	}
	
	public float getVectorY(){
		return vector.getY();
	}
	
	public void setVectorWithoutSpeedModifyer(float x, float y){
		vector.set(x, y);
	}

	public void setVectorByDegree(float length, float degree) {
		length = length*speedModifyer;
		float dx = (float) (length * Math.cos(Math.toRadians(degree)));
		float dy = (float) (length * Math.sin(Math.toRadians(degree)));
		vector.set(dx, dy);
	}
	
	public float getSpeedModifyer(){
		return speedModifyer;
	}
	
	//enter float value, speed = speedModifyer x speed
	public void setSpeedModifyer(float speedModifyer){
		this.speedModifyer = speedModifyer;
	}

	public void update(int delta, ArrayList<Entity> entities) {
		if (vector != null) {
			float newXPos = xPos + vector.getX() * delta / 100f;
			float newYPos = yPos + vector.getY() * delta / 100f;
			boundingBox.setX(newXPos);
			boundingBox.setY(newYPos);
			boolean moveForbidden = false;
			if (entities != null) {
				for (Entity e : entities) {
					if (boundingBox.intersects(e.getBoundingBox())) {
						moveForbidden = true;
					}
				}
			}

			if (moveForbidden) {
				boundingBox.setX(xPos);
				boundingBox.setY(yPos);
			} else {
				xPos = newXPos;
				yPos = newYPos;
			}
		}
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		if (image != null) {
			g.drawImage(image, getXPos() - cameraX, getYPos() - cameraY);
		}
	}

	public Shape getBoundingBox() {
		return boundingBox;
	}

	public boolean intersects(Entity entity) {
		if (this.getBoundingBox() == null) {
			return false;
		}
		return getBoundingBox().intersects(entity.getBoundingBox());
	}
	
	public Image getImage(){
		return image;
	}
	
	public void setPos(float xPos, float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}

}
