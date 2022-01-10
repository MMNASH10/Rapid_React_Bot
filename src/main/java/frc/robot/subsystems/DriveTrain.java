// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final WPI_TalonFX leftFront;
  private final WPI_TalonFX rightFront;
  private final WPI_TalonFX leftBack;
  private final WPI_TalonFX rightBack;

  private final DifferentialDrive difDrive;
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftFront = new WPI_TalonFX(Constants.LEFT_FRONT);
    rightFront = new WPI_TalonFX(Constants.RIGHT_FRONT);
    leftBack = new WPI_TalonFX(Constants.LEFT_BACK);
    rightBack = new WPI_TalonFX(Constants.RIGHT_BACK);

    difDrive = new DifferentialDrive(leftFront, rightFront);

    configDriveTrain();
  }

  public void drive(XboxController controller, double speed) {
    difDrive.arcadeDrive(((controller.getRawAxis(Constants.RIGHT_TRIGGER))-(controller.getRawAxis(Constants.LEFT_TRIGGER)))*speed, controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS)*-speed);
  }

  public void driveInverted(XboxController controller, double speed) {
    difDrive.arcadeDrive(((controller.getRawAxis(Constants.RIGHT_TRIGGER))-(controller.getRawAxis(Constants.LEFT_TRIGGER)))*-speed, controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS)*-speed);
  }

  public void stop() {
    difDrive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void configDriveTrain() {
    /* factory default values */
    leftFront.configFactoryDefault();
    rightFront.configFactoryDefault();
    leftBack.configFactoryDefault();
    rightBack.configFactoryDefault();

    /* set up followers*/
    leftBack.follow(leftFront);
    rightBack.follow(rightFront);
    
    
    leftFront.setSensorPhase(true);
    rightFront.setSensorPhase(true);
  }
}
