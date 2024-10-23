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

  CANSparkMax motorController  =  new CANSparkMax(5, MotorType.kBrushless);


  Pigeon2 gyro = new Pigeon2(sparkID); //Instantiate gyroscope with same ID as spark max
  Timer timer = new Timer(); //Instantiate a timer object 

  

  @Override
  public void robotInit() {

    yaw = gyro.getYaw().getValue(); // Thgis is how we get the value of the yaw from the gyroscope, but we will prob. have to move this to a periodic funcion

    timer.start(); // Start the timer when the robot is booted and ready
  }

  @Override
  public void robotPeriodic() {
    yaw = gyro.getYaw().getValue();
    roll = gyro.getRoll().getValue();

    SmartDashboard.putNumber("Yaw", yaw);
    SmartDashboard.putNumber("Pitch", pitch);
    SmartDashboard.putNumber("Roll", roll);
    System.out.println(timer.get());

    if(timer.get()>0){ // need to change this to make it where it only runs for five seconds then stops
      motorController.set(0.1);
    }
  }

  

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    timer.reset(); // Reset the timer anytime we enter teleop
    
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
