package utilities;

import game.Model;
import game.Model.Team;


public class Statistics {
	
	
	public int[] currentKillWatcher;
	
	
	public int[] damageDone;
	public int[] damageTaken;
	public int[] deathCount;
	public int[] killCount;
	
	public int gameTime;
	
	public int greenKills;
	public int brownKills;
	
	public Statistics(int playerAmount){
		
		gameTime = 0;
		
		greenKills = 0;
		brownKills = 0;
		
		currentKillWatcher = new int[playerAmount];
		deathCount = new int[playerAmount];
		killCount = new int[playerAmount];
		
		damageDone = new int[playerAmount];
		damageTaken = new int[playerAmount];
		
		for(int i = 0; i < playerAmount; i++){
			currentKillWatcher[i] = -1;
			deathCount[i] = 0;
			killCount[i] = 0;
			damageDone[i] = 0;
			damageTaken[i] = 0;
		}
	}
	
	public void registerDeath(int playerId){
		deathCount[playerId]++;
		killCount[currentKillWatcher[playerId]]++;
		
		if(Model.model.getPlayer(currentKillWatcher[playerId]).getTeam() == Team.GREEN){
			greenKills++;
		}else{
			brownKills++;
		}
	}
	
	public void registerDamage(int amount, int playerRecievedId, int playerDealtId){
		damageDone[playerDealtId] += amount;
		damageTaken[playerRecievedId] += amount;
		
		currentKillWatcher[playerRecievedId] = playerDealtId;
	}
	
	public void update(int delta){
		gameTime += delta;
	}
	
	public int getPlayerKillsById(int playerId){
		return killCount[playerId];
	}
	
	public int getPlayerDeathCountById(int playerId){
		return deathCount[playerId];
	}
	
	public int getDamageDoneById(int playerId){
		return damageDone[playerId];
	}
	
	public int getGameTime(){
		return gameTime;
	}
	
	public int getGreenKills(){
		return greenKills;
	}
	
	public int getBrownKills(){
		return brownKills;
	}
}
