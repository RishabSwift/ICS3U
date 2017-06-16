import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


/**
 * Robot.java
 * The main robot class
 * <p>
 * June 16 2017
 *
 * @author Rishab Bhatt
 */
public class Robot extends RobotHelper{

	public static CompassHTSensor compass = new CompassHTSensor(SensorPort.S2);
	public static LightSensor lightSensor = new LightSensor(SensorPort.S3);
	
	public static void main(String[] args) {
		
		Behavior touch = new Touched();
		Behavior drive = new DriveForward();
		Behavior light = new Light();
		Behavior[] allBehaviours = {drive, touch, light};
		Arbitrator arbitrator = new Arbitrator(allBehaviours);
		arbitrator.start();
		

//		compass.resetCartesianZero();
//		
//		while (true) {
//			LCD.drawString(compass.getDegreesCartesian() + "", 0, 0);
////			LCD.drawInt(lightSensor.getNormalizedLightValue(), 0, 0);
//			if (Button.ENTER.isDown()) {
//				break;
//			}
//		}
//
//		turnToAngleTurit(90);
//	
//		Button.waitForAnyPress();
		
	}
	
	
	
	public static void turnToAngleTurit(float angle) {
		// compass.resetCartesianZero();
		float currentAngle = compass.getDegreesCartesian();

		Motor.C.setSpeed(350);
		if (currentAngle < angle) {
			// counterclockwise
			Motor.C.forward();
		} else {
			// clockwise
			Motor.C.backward();
		}
		
		while (Motor.C.isMoving() && compass.getDegreesCartesian() != angle) {
			LCD.drawString(compass.getDegreesCartesian() + "", 0, 0);
			Thread.yield();
		}
		
		Motor.C.stop();

	}
	public static void turnToAngle(float angle) {
//		compass.resetCartesianZero();
		float currentAngle = compass.getDegreesCartesian();
		
		Motor.B.setSpeed(100);
		if (currentAngle < angle) {
			//counterclockwise
			Motor.B.forward();	
		} else {
			//clockwise
			Motor.B.backward();
		}
		
		while (Motor.B.isMoving() && compass.getDegreesCartesian() != 60) {
			LCD.drawString(compass.getDegreesCartesian() + "", 0, 0);
			Thread.yield();
		}
		
		Motor.B.stop();
	}
	

}
