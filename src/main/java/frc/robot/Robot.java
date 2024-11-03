// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  double yaw, pitch, roll;
  int sparkID = 5;
  int pigeonID = 1;

  double timerStorage;

  CANSparkMax motorController  =  new CANSparkMax(5, MotorType.kBrushless);


  Pigeon2 gyro = new Pigeon2(pigeonID); //Instantiate gyroscope with its corresponding ID
  Timer timer = new Timer(); //Instantiate a timer object 

  

  @Override
  public void robotInit() {
    timer.start(); // Start the timer when the robot is booted and ready
  }

  @Override
  public void robotPeriodic() {
    pitch = gyro.getPitch().getValue();
    yaw = gyro.getYaw().getValue();
    roll = gyro.getRoll().getValue();

    timerStorage = timer.get();

    // Send the data to smart dashboard
    SmartDashboard.putNumber("Yaw", yaw);
    SmartDashboard.putNumber("Pitch", pitch);
    SmartDashboard.putNumber("Roll", roll);
    System.out.println(timerStorage);

     if(timerStorage < 5){ // need to change this to make it where it only runs for five seconds then stops
       motorController.set(0.1);
     } else if(timerStorage < 10) {
       motorController.set(0);
     } else if(timerStorage < 15){
       motorController.set(-0.1);
     } else if(timerStorage < 20){
      motorController.set(0);
     }
  }

 

  

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    timer.reset(); // Reset the timer anytime we enter teleop
    
    if(timer.get() == 20.0){
      timer.reset();
    }
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
