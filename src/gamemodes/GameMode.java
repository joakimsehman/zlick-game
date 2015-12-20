package gamemodes;

import game.Model;
import game.Model.Team;


//TODO: SYNC THIS WITH HOSTS/CLIENTS!
public abstract class GameMode {
	
	private int bestOf;
	private int currentGame;
	
	private int greenWins, brownWins;
	
	public GameMode(){
		bestOf = 2;
		currentGame = 1;
		
		greenWins = 0;
		brownWins = 0;
	}
	
	public void update(int delta){
		if(hasFinished()){			
			if(bestOf == currentGame){
				onFinish();
				onGamesCompleted();
			}else{
				onFinish();
				currentGame++;
			}
			
		}
	}
	
	public void setGameAmount(int gameAmount){
		bestOf = gameAmount;
	}
	
	public abstract boolean hasFinished();
	
	
	//Called after hasFinished() returns true
	public abstract Team getWinningTeam();
	
	
	public void onFinish(){
		if(getWinningTeam() == Team.GREEN){
			greenWins++;
		}else{
			brownWins++;
		}
		
		Model.model.resetGame();
	}
	
	public void onGamesCompleted(){
		Model.model.gameCompleted();
	}

	public int getGreenWins(){
		return greenWins;
	}
	
	public int getBrownWins(){
		return brownWins;
	}
	
}
