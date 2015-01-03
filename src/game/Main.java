package game;



import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	
	public static void main(String[] args){
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Application("Za Bleyzpleyz"));
			
			//when you remake ui, change screen size first here
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.start();
		}
		catch(SlickException ex)
		{
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
