package script;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.utilities.Sleep;

import script.framework.Leaf;

public class Waiting extends Leaf {

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
    public int onLoop()
    {
		if(Client.getGameState() == GameState.LOADING || 
        		Client.getGameState() == GameState.GAME_LOADING)
		{
			Sleep.sleepUntil(() -> (Client.getGameState() != GameState.LOADING && 
        		Client.getGameState() != GameState.GAME_LOADING), 10000);
			int sleep = (int) Calculations.nextGaussianRandom(1000, 500);
			if(sleep > 0) return sleep;
			else return 1000;
		}
		else
		{
			Sleep.sleepUntil(() -> Client.getGameState() == GameState.LOGGED_IN, 7000);
	    	int sleep = (int) Calculations.nextGaussianRandom(1000, 500);
			if(sleep > 0) return sleep;
			else return 1000;
		}
    }

}
