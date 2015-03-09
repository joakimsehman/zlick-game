package entities;

import java.util.ArrayList;

import game.Model;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {

	public enum Direction {
		NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
	};

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
		this.direction = Direction.EAST;
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

	public float getVectorX() {
		return vector.getX();
	}

	public float getVectorY() {
		return vector.getY();
	}

	public void setVectorWithoutSpeedModifier(float x, float y) {
		vector.set(x, y);
		if (x == 0 && y == 0) {
			setIsMoving(false);
		} else {
			setIsMoving(true);
			checkAndSetDirection();
		}
	}

	protected void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void setVectorByDegree(float length, float degree) {
		length = length * speedModifier;
		float dx = (float) (length * Math.cos(Math.toRadians(degree)));
		float dy = (float) (length * Math.sin(Math.toRadians(degree)));
		vector.set(dx, dy);
		if (length == 0) {
			setIsMoving(false);
		} else {
			setIsMoving(true);
			checkAndSetDirection();
		}
	}

	private void checkAndSetDirection() {
		if ((int) vector.getX() == 0) {
			if ((int) vector.getY() == 0) {
				//entity is still, direction unchanged
			} else if ((int) vector.getY() > 0) {
				direction = Direction.SOUTH;
			} else {
				direction = Direction.NORTH;
			}
		} else if ((int) vector.getX() > 0) {
			if ((int) vector.getY() == 0) {
				direction = Direction.EAST;
			} else if ((int) vector.getY() > 0) {
				direction = Direction.SOUTHEAST;
			} else {
				direction = Direction.NORTHEAST;
			}
		} else {
			if ((int) vector.getY() == 0) {
				direction = Direction.WEST;
			} else if ((int) vector.getY() > 0) {
				direction = Direction.SOUTHWEST;
			} else {
				direction = Direction.NORTHWEST;
			}
		}
	}

	public float getSpeedModifier() {
		return speedModifier;
	}

	//enter float value, speed = speedModifier x speed
	public void setSpeedModifier(float speedModifier) {
		this.speedModifier = speedModifier;
	}

	public void update(int delta, ArrayList<Entity> entities,
			boolean collidesWithTerrain) {
		if (vector != null) {
			float newXPos = xPos + vector.getX() * delta / 100f;
			float newYPos = yPos + vector.getY() * delta / 100f;
			boolean moveForbidden = false;

			if (boundingBox != null) {
				boundingBox.setX(newXPos);
				boundingBox.setY(newYPos);

				if (collidesWithTerrain) {
					if (vector.getX() < 0) {
						if (vector.getY() < 0) {
							moveForbidden = checkNeededCollisionPoints(true,
									true, true, false, newXPos, newYPos);
						} else if (vector.getY() > 0) {
							moveForbidden = checkNeededCollisionPoints(true,
									false, true, true, newXPos, newYPos);
						} else {
							moveForbidden = checkNeededCollisionPoints(true,
									false, true, false, newXPos, newYPos);
						}
					} else if (vector.getX() > 0) {
						if (vector.getY() < 0) {
							moveForbidden = checkNeededCollisionPoints(true,
									true, false, true, newXPos, newYPos);
						} else if (vector.getY() > 0) {
							moveForbidden = checkNeededCollisionPoints(false,
									true, true, true, newXPos, newYPos);
						} else {
							moveForbidden = checkNeededCollisionPoints(false,
									true, false, true, newXPos, newYPos);
						}

					} else if (vector.getY() > 0) {
						moveForbidden = checkNeededCollisionPoints(false,
								false, true, true, newXPos, newYPos);
					} else if (vector.getY() < 0) {
						moveForbidden = checkNeededCollisionPoints(true, true,
								false, false, newXPos, newYPos);
					}
				}

				if (entities != null) {
					for (Entity e : entities) {
						if (boundingBox.intersects(e.getBoundingBox())) {
							moveForbidden = true;
						}
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
			g.drawImage(image, xPos - cameraX, yPos - cameraY);
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

	public Image getImage() {
		return image;
	}

	public void setPos(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	private boolean checkNeededCollisionPoints(boolean upperLeft,
			boolean upperRight, boolean lowerLeft, boolean lowerRight,
			float newXPos, float newYPos) {
		if (upperLeft) {
			if (Model.model.getLevel().getTileAtPos(newXPos, newYPos).isSolid()) {
				return true;
			}
		}
		if (upperRight) {
			if (Model.model.getLevel()
					.getTileAtPos(newXPos + boundingBox.getWidth(), newYPos)
					.isSolid()) {
				return true;
			}
		}
		if (lowerLeft) {
			if (Model.model.getLevel()
					.getTileAtPos(newXPos, newYPos + boundingBox.getHeight())
					.isSolid()) {
				return true;
			}
		}
		if (lowerRight) {
			if (Model.model
					.getLevel()
					.getTileAtPos(newXPos + boundingBox.getWidth(),
							newYPos + boundingBox.getHeight()).isSolid()) {
				return true;
			}
		}

		return false;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public Direction getDirection() {
		return direction;
	}

	public Direction getDirectionToPoint(float x, float y) {
		double angle = getAngleToPoint(x, y);
		double eigthOfPi = Math.PI / 8;
		if (angle < -eigthOfPi * 7 || angle > eigthOfPi * 7) {
			return Direction.WEST;
		} else if (angle > eigthOfPi * 5) {
			return Direction.SOUTHWEST;
		} else if (angle > eigthOfPi * 3) {
			return Direction.SOUTH;
		} else if (angle > eigthOfPi) {
			return Direction.SOUTHEAST;
		} else if (angle > -eigthOfPi) {
			return Direction.EAST;
		} else if (angle > -eigthOfPi * 3) {
			return Direction.NORTHEAST;
		} else if (angle > -eigthOfPi * 5) {
			return Direction.NORTH;
		} else {
			return Direction.NORTHWEST;
		}
	}

	//returns as radians
	public double getAngleToPoint(float x, float y) {
		float deltaY = y - yPos;
		float deltaX = x - xPos;
		return Math.atan2(deltaY, deltaX);
	}

}
