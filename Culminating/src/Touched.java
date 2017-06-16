
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

/**
 * Touched.java
 * Behaviour for the touch sensor
 * <p>
 * June 16 2017
 *
 * @author Rishab Bhatt
 */
public class Touched extends RobotHelper implements Behavior {
	
	boolean suppressed = false;

	@Override
	/**
	 * Take control of the method
	 */
	public boolean takeControl() {
		return isTouchSensorPressed();
	}

	/**
	 * Do the given action
	 */
	@Override
	public void action() {
		suppressed = false;
	
		Motor.A.stop();
		Motor.B.stop();
		
		Motor.A.rotate(-180, true);
		Motor.B.rotate(-360, true);

		while (Motor.B.isMoving() && !suppressed)
			Thread.yield();

		Motor.A.stop();
		Motor.B.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
