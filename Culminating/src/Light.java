import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;

/**
 * Light.java
 * The main robot class
 * <p>
 * June 16 2017
 *
 * @author Rishab Bhatt
 */
public class Light implements Behavior {

	private static final int TURIT_SPEED = 150;
	boolean reversed = false;
	private boolean suppressed = false;
	private static final int FLASHT_LIGHT_VALUE = 500;

	LightSensor lightSensor = new LightSensor(SensorPort.S3);
	CompassHTSensor compass = new CompassHTSensor(SensorPort.S2);

	private float angleToTurn = 0;

	public boolean takeControl() {
		return true;
	}

	@Override
	/**
	 * The main action
	 */
	public void action() {

		compass.resetCartesianZero();
		Motor.C.resetTachoCount();
		Motor.C.setSpeed(100);
		Motor.C.rotateTo(reversed ? TURIT_SPEED : -TURIT_SPEED);
		Motor.A.stop();
		reversed = !reversed;

		while (!suppressed) {

			if (Robot.isTouchSensorPressed()) {
				Robot.stopMotors();
			}
			
			LCD.drawString("Current: " + compass.getDegreesCartesian(), 0, 0);

			int lightSensorValue = lightSensor.readNormalizedValue();

			// LCD.drawInt(lightSensorValue, 1, 2);
			// LCD.drawString(compass.getDegreesCartesian() + "", 1, 2);

			// Start rotating
			if (!Motor.C.isMoving() && lightSensorValue <= FLASHT_LIGHT_VALUE) {

				Motor.C.rotateTo(reversed ? TURIT_SPEED : -TURIT_SPEED, true);
				reversed = !reversed;
			} else if (lightSensorValue >= FLASHT_LIGHT_VALUE) {
				// Found the light
				Robot.stopMotors();

				// Get the angle
				angleToTurn = compass.getDegreesCartesian();
				LCD.drawString("To turn: " + compass.getDegreesCartesian(), 0, 0);

				Motor.C.stop();
				reversed = !reversed;

				// Turn the turit back to the center
				turnToAngleTurit(0);

				turnRobot();

				while (Motor.B.isMoving() && !(compass.getDegreesCartesian() >= angleToTurn - 5
						&& compass.getDegreesCartesian() <= angleToTurn + 5)) {
					// while (Motor.B.isMoving() &&
					// compass.getDegreesCartesian() != angleToTurn) {
					LCD.drawString("Current " + compass.getDegreesCartesian() + "", 0, 2);
					Thread.yield();
				}

				Motor.B.stop();

				// Robot has successfully rotated
				// Now drive towards the direction until it has touched
				// something

				Robot.setMotorSpeed(300);
				
				
				
				

			}

			Thread.yield();
		}

		Motor.C.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	/**
	 * Turn the robot to a given angle
	 */
	public void turnRobot() {

		turnToAngle(angleToTurn);

	}

	/**
	 * Turn to angle for the robot
	 * @param angle
	 */
	public void turnToAngle(float angle) {
		// compass.resetCartesianZero();
		float currentAngle = compass.getDegreesCartesian();

		Motor.B.setSpeed(100);
		if (currentAngle < angle) {
			// counterclockwise
			Motor.B.forward();
		} else {
			// clockwise
			Motor.B.backward();
		}

	}

	/**
	 * Turn to angle for the turit
	 * @param angle
	 */
	public void turnToAngleTurit(float angle) {
		// compass.resetCartesianZero();
		float currentAngle = compass.getDegreesCartesian();

		Motor.C.setSpeed(100);

		if (reversed) {
			// counterclockweise
			Motor.C.forward();
		} else {
			// clockwise
			Motor.C.backward();
		}
		reversed = !reversed;
		
		while (Motor.C.isMoving()
				&& !(compass.getDegreesCartesian() >= angle - 3 && compass.getDegreesCartesian() <= angle + 3)) {
			LCD.drawString(compass.getDegreesCartesian() + "", 0, 0);
			Thread.yield();
		}

		Motor.C.stop();
		

	}

}
