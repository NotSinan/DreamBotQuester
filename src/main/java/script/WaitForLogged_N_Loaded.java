package script;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import script.framework.Branch;

public class WaitForLogged_N_Loaded extends Branch
{

    @Override
    public boolean isValid() {
        return Client.getGameState() == GameState.LOADING || 
        		Client.getGameState() == GameState.GAME_LOADING || 
        		Client.getGameState() != GameState.LOGGED_IN;
    }

}
