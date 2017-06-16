import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.CompassHTSensor;

/**
 * RobotHelper.java
 * The main robot helper 
 * <p>
 * June 16 2017
 *
 * @author Rishab Bhatt
 */
public class RobotHelper {

	public static TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
	public static CompassHTSensor compass = new CompassHTSensor(SensorPort.S2);
	

	/**
	 * Set the motor speed
	 * @param speed Motor speed
	 */
	public static void setMotorSpeed(int speed) {
		
		// if speed is negative, go backwards
		boolean reverse = speed < 0;
		
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		
		if (reverse) {
			Motor.A.forward();
			Motor.B.forward();
		} else {
			Motor.A.backward();
			Motor.B.backward();
		}
	}
	
	/**
	 * Check if the touch sensor is pressed
	 * @return true 
	 */
	public static boolean isTouchSensorPressed() {
		return touchSensor.isPressed();
	}
	

	/**
	 * Stop all motors completely
	 */
	public static void stopMotors() {
		Motor.A.stop();
		Motor.B.stop();
	}
	
	/**
	 * Make the robot go forward
	 */
	public static void goForward() {
		Motor.A.forward();
		Motor.B.forward();
	}
	
	/**
	 * Make the robot go backward
	 */
	public static void goBackward() {
		Motor.A.backward();
		Motor.B.backward();
	}
	
	/**
	 * Rotate to a specific angle
	 * @param angle
	 */
	public static void rotateToAngle(int angle) {
		compass.resetCartesianZero();
		// todo angle for error	: change the range
		while (compass.getDegreesCartesian() != angle) {
			
		}
	}
	
}
