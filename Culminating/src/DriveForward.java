import lejos.nxt.*;
import lejos.robotics.subsumption.*;

/**
 * DriveForward.java
 * Keep driving forward 
 * 
 * June 16 2017
 *
 * @author Rishab Bhatt
 */
public class DriveForward  implements Behavior {
   private boolean suppressed = false;
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
     suppressed = false;
     Motor.A.forward();
     Motor.B.forward();
     while( !suppressed )
        Thread.yield();
     Motor.A.stop(); // clean up
     Motor.B.stop();
   }
}