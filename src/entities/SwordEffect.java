package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import entities.Entity.Direction;
import entities.Player.EffectAnimation;

public class SwordEffect {
	
	private static final int damage = 10;
	// because of the polygon hitbox, all spellareaofeffect constructors recieves corrected values depending on the polygons lowest x and y values, 
	// this phenomenom is in fact true and should be considered for all enteties
	
	public static class WestSword extends SpellAreaOfEffect{

		private static final int effectId = 7;
		
		public WestSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos-50, yPos-25, vector, new Polygon(new float[]{xPos, yPos, xPos-50, yPos-25, xPos-50, yPos+25}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
			
			
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
		
	}
	
	public static class SouthWestSword extends SpellAreaOfEffect{

		private static final int effectId = 8;
		
		public SouthWestSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos-44.93f, yPos-33.5f, vector, new Polygon(new float[]{xPos, yPos, xPos-44.93f, yPos+33.5f, xPos-33.5f, yPos+44.93f}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
		
	}
	
	public static class SouthSword extends SpellAreaOfEffect{

		private static final int effectId = 9;
		
		public SouthSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos-25, yPos, vector, new Polygon(new float[]{xPos, yPos, xPos-25, yPos+50, xPos+25, yPos+50f}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	public static class SouthEastSword extends SpellAreaOfEffect{
		
		private static final int effectId = 10;
		
		public SouthEastSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos, yPos, vector, new Polygon(new float[]{xPos, yPos, xPos+44.93f, yPos+33.5f, xPos+33.5f, yPos+44.93f}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	public static class EastSword extends SpellAreaOfEffect{
		
		private static final int effectId = 11;
		
		public EastSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos, yPos-25, vector, new Polygon(new float[]{xPos, yPos, xPos+50, yPos+25, xPos+50, yPos-25}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	public static class NorthEastSword extends SpellAreaOfEffect{
		
		private static final int effectId = 12;
		
		public NorthEastSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos, yPos-44.93f, vector, new Polygon(new float[]{xPos, yPos, xPos+44.93f, yPos-33.5f, xPos+33.5f, yPos-44.93f}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public void draw(Graphics g, float cameraX, float cameraY){
			
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	public static class NorthSword extends SpellAreaOfEffect{
		
		private static final int effectId = 13;
		
		public NorthSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos-25, yPos-50, vector, new Polygon(new float[]{xPos, yPos, xPos+25, yPos-50, xPos-25, yPos-50}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	public static class NorthWestSword extends SpellAreaOfEffect{
		
		private static final int effectId = 14;
		
		public NorthWestSword(float xPos, float yPos, Vector2f vector,
				int duration,
				int playerId, int spellEffectId) {
			super(xPos-44.93f, yPos-44.93f, vector, new Polygon(new float[]{xPos, yPos, xPos-44.93f, yPos-33.5f, xPos-33.5f, yPos-44.93f}), null, duration, true,
					playerId, spellEffectId);
			// TODO Auto-generated constructor stub
		}
		
		public void applyEffect(Player player){
			onApply(player);
		}
		
		public static int getEffectId(){
			return effectId;
		}
	}
	
	private static void onApply(Player player){
		player.applyDamage(-damage, EffectAnimation.BLOOD);
	}
	
	public static int getIdFromDirection(Direction direction){
		if(direction == Direction.WEST){
			return 7;
		}else if(direction == Direction.SOUTHWEST){
			return 8;
		}else if(direction == Direction.SOUTH){
			return 9;
		}else if(direction == Direction.SOUTHEAST){
			return 10;
		}else if(direction == Direction.EAST){
			return 11;
		}else if(direction == Direction.NORTHEAST){
			return 12;
		}else if(direction == Direction.NORTH){
			return 13;
		}else if(direction == Direction.NORTHWEST){
			return 14;
		}else{
			return 0;
		}
	}
	
}
