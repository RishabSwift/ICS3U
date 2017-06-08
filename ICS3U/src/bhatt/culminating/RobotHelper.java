package bhatt.culminating;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public interface RobotHelper {

	boolean DEBUG_MODE = true;
	
	/**
	 * Get the color sensor value
	 * @return
	 */
	public static int getColorSensorValue() {
		LightSensor light = new LightSensor(SensorPort.S4);
		
		if (DEBUG_MODE) {
			LCD.drawInt(light.readNormalizedValue(), 4, 0, 1);
		}
		
		return light.readNormalizedValue();
	}
	

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
	 * @return boolean
	 */
	public static boolean isTouchSensorPressed() {
		TouchSensor touchSensor = new TouchSensor(SensorPort.S2);
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
}
