package entities;

import java.util.ArrayList;

import game.Model;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {

    public enum Direction{NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST};

	private float xPos;
	private float yPos;
	private Vector2f vector;
	private Shape boundingBox;
	private Image image;
	private float speedModifier;
    private boolean isMoving;
    private Direction direction;



	public Entity(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
        direction = Direction.EAST;
		this.vector = vector;
		this.boundingBox = boundingBox;
		this.image = image;
		speedModifier = 1;
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
	
	public void setVectorWithoutSpeedModifier(float x, float y){
        vector.set(x, y);
        if(x == 0 && y == 0){
            isMoving = false;
        }else{
            isMoving = true;
            checkAndSetDirection();
        }
	}

	public void setVectorByDegree(float length, float degree) {
		length = length* speedModifier;
		float dx = (float) (length * Math.cos(Math.toRadians(degree)));
		float dy = (float) (length * Math.sin(Math.toRadians(degree)));
		vector.set(dx, dy);
        if(length == 0){
            isMoving = false;
        }else{
            isMoving = true;
            checkAndSetDirection();
        }
	}

    //implement this shit yooo
    private void checkAndSetDirection(){
        if(vector.getX() == 0){
            if(vector.getY() == 0){

            }else if(vector.getY() > 0){

            }else{

            }
        }else if(vector.getX() > 0){

        }else{
            if(vector.getY() == 0){

            }else if(vector.getY() > 0){

            }else{

            }
        }
    }
	
	public float getSpeedModifier(){
		return speedModifier;
	}
	
	//enter float value, speed = speedModifier x speed
	public void setSpeedModifier(float speedModifier){
		this.speedModifier = speedModifier;
	}

	public void update(int delta, ArrayList<Entity> entities, boolean collidesWithTerrain) {
		if (vector != null) {
			float newXPos = xPos + vector.getX() * delta / 100f;
			float newYPos = yPos + vector.getY() * delta / 100f;
			boundingBox.setX(newXPos);
			boundingBox.setY(newYPos);
			boolean moveForbidden = false;


            if(collidesWithTerrain){
                if(vector.getX() < 0){
                    if(vector.getY() < 0){
                        moveForbidden = checkNeededCollisionPoints(true, true, true, false, newXPos, newYPos);
                    }else if(vector.getY() > 0){
                        moveForbidden = checkNeededCollisionPoints(true, false, true, true, newXPos, newYPos);
                    }else{
                        moveForbidden = checkNeededCollisionPoints(true, false, true, false, newXPos, newYPos);
                    }
                }else if(vector.getX() > 0){
                    if(vector.getY() < 0){
                        moveForbidden = checkNeededCollisionPoints(true, true, false, true, newXPos, newYPos);
                    }else if(vector.getY() > 0){
                        moveForbidden = checkNeededCollisionPoints(false, true, true, true, newXPos, newYPos);
                    }else{
                        moveForbidden = checkNeededCollisionPoints(false, true, false, true, newXPos, newYPos);
                    }

                }else if(vector.getY() > 0){
                    moveForbidden = checkNeededCollisionPoints(false, false, true, true, newXPos, newYPos);
                }else if(vector.getY() < 0){
                    moveForbidden = checkNeededCollisionPoints(true, true, false, false, newXPos, newYPos);
                }
            }

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

    private boolean checkNeededCollisionPoints(boolean upperLeft, boolean upperRight, boolean lowerLeft, boolean lowerRight, float newXPos, float newYPos){
        if(upperLeft){
            if(Model.model.getLevel().getTileAtPos(newXPos, newYPos).isSolid()){
                return true;
            }
        }
        if(upperRight){
            if(Model.model.getLevel().getTileAtPos(newXPos + boundingBox.getWidth(), newYPos).isSolid()){
                return true;
            }
        }
        if(lowerLeft){
            if(Model.model.getLevel().getTileAtPos(newXPos, newYPos + boundingBox.getHeight()).isSolid()){
                return true;
            }
        }
        if(lowerRight){
            if(Model.model.getLevel().getTileAtPos(newXPos + boundingBox.getWidth(), newYPos + boundingBox.getHeight()).isSolid()){
                return true;
            }
        }

        return false;
    }

    public boolean isMoving(){
        return isMoving;
    }

}
