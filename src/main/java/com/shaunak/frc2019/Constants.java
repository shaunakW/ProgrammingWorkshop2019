package com.shaunak.frc2019;

//import com.team254.frc2019.subsystems.ServoMotorSubsystem.ServoMotorSubsystemConstants;
//import com.team254.frc2019.subsystems.ServoMotorSubsystem.TalonSRXConstants;
//import com.team254.frc2019.subsystems.Limelight.LimelightConstants;
//import com.team254.lib.geometry.Pose2d;
//import com.team254.lib.geometry.Rotation2d;
//import com.team254.lib.geometry.Translation2d;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * A list of constants used by the rest of the robot code. This includes physics
 * constants as well as constants determined through calibration.
 */
public class Constants {
    public static final double kLooperDt = 0.01;

    public static final int kDriveRightMasterId = 0;
    public static final int kDriveRightSlaveId = 1;
    public static final int kDriveLeftMasterId = 2;
    public static final int kDriveLeftSlaveId = 3;

    public static final int kThrottleJoystickPort = 0;
    public static final int kTurnJoystickPort = 1;
}
