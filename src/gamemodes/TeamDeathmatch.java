package gamemodes;

import utilities.Statistics;
import game.Model;
import game.Model.Team;

public class TeamDeathmatch extends GameMode{

	private int killLimit;
	
	public TeamDeathmatch(){
		super();
		killLimit = 20;
	}
	
	public void setKillLimit(int killLimit){
		this.killLimit = killLimit;
	}
	
	public int getKillLimit(){
		return killLimit;
	}
	
	@Override
	public boolean hasFinished() {
		Statistics statistics = Model.model.getStatistics();
		
		if(statistics.getGreenKills() >= killLimit){
			return true;
		}else if(statistics.getBrownKills() >= killLimit){
			return true;
		}
		
		return false;
	}
	
	@Override
	public Team getWinningTeam() {
		if(Model.model.getStatistics().getGreenKills() > Model.model.getStatistics().getBrownKills()){
			return Team.GREEN;
		}else{
			return Team.BROWN;
		}
	}

	
	public void update(int delta){
		super.update(delta);
	}

	
}
