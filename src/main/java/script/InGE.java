package script;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;

import script.framework.Leaf;

public class InGE extends Leaf {

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int onLoop() {
		Logger.log("Arrived at destination of GE!");
		Sleep.sleepTick();
		return Calculations.random(500,1000);
	}

}
