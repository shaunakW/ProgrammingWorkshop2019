package com.team254.frc2019;

//import com.team254.frc2019.subsystems.ServoMotorSubsystem.ServoMotorSubsystemConstants;
//import com.team254.frc2019.subsystems.ServoMotorSubsystem.TalonSRXConstants;
//import com.team254.frc2019.subsystems.Limelight.LimelightConstants;
//import com.team254.lib.geometry.Pose2d;
//import com.team254.lib.geometry.Rotation2d;
//import com.team254.lib.geometry.Translation2d;

//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.util.Enumeration;

/**
 * A list of constants used by the rest of the robot code. This includes physics
 * constants as well as constants determined through calibration.
 */
public class Constants {
    // Programming workshop constants
    public static final int kDriveRightMasterId = 0;
    public static final int kDriveRightSlaveId = 1;
    public static final int kDriveLeftMasterId = 2;
    public static final int kDriveLeftSlaveId = 3;

    public static final int kXboxControllerPort = 0;

    public static final double kLooperDt = 0.01;





    // FRC-2019 constants (for lib)

    // wheels
    // Tuned 3/26/19
    public static final double kDriveWheelTrackWidthInches = 25.42;
    public static final double kDriveWheelDiameterInches = 3.938;
    public static final double kDriveWheelRadiusInches = kDriveWheelDiameterInches / 2.0;
    public static final double kDriveWheelTrackRadiusWidthMeters = kDriveWheelTrackWidthInches / 2.0 * 0.0254;
    public static final double kTrackScrubFactor = 1.0469745223;

    // tuned dynamics
    public static final double kDriveLinearVIntercept = 0.1801; // V
    public static final double kDriveLinearKv = 0.0919; // V per rad/s
    public static final double kDriveLinearKa = 0.03344; // V per rad/s^2
    public static final double kDriveAngularKa = 0.02897 * 2.0; // V per rad/s^2
    public static final double kRobotLinearInertia = 63.9565; // kg
    public static final double kRobotAngularInertia = kDriveAngularKa / kDriveLinearKa *
            kDriveWheelTrackRadiusWidthMeters * kDriveWheelTrackRadiusWidthMeters * kRobotLinearInertia;  // kg m^2
    public static final double kRobotAngularDrag = 0.0; // N*m / (rad/sec)

    public static final int kCANTimeoutMs = 10; // use for important on the fly updates
    public static final int kLongCANTimeoutMs = 100; // use for constructors

    public static final double kBumperHeight = 6.6 + 2.0; // inches to ground + 2 in buffer

    // Do not change anything after this line unless you rewire the robot and
    // update the spreadsheet!
    // Port assignments should match up with the spreadsheet here:
    // https://docs.google.com/spreadsheets/d/1w9V3_tqQ0npdc9U8WPD-6zJkKouunKzHvPXLbHEWwxk/edit#gid=0

    // drive
    public static final int kLeftDriveMasterId = 11;
    public static final int kLeftDriveSlaveId = 12;
    public static final int kRightDriveMasterId = 13;
    public static final int kRightDriveSlaveId = 14;

    public static final int kLeftDriveEncoderA = 0;
    public static final int kLeftDriveEncoderB = 1;
    public static final int kRightDriveEncoderA = 2;
    public static final int kRightDriveEncoderB = 3;

    public static final double kDriveEncoderPPR = 1000.0;

    public static final double kMinLookAhead = 12.0; // inches
    public static final double kMinLookAheadSpeed = 12.0; // inches per second
    public static final double kMaxLookAhead = 48.0; // inches
    public static final double kMaxLookAheadSpeed = 120.0; // inches per second
    public static final double kDeltaLookAhead = kMaxLookAhead - kMinLookAhead;
    public static final double kDeltaLookAheadSpeed = kMaxLookAheadSpeed - kMinLookAheadSpeed;

    public static final double kInertiaSteeringGain = 0.0; // angular velocity command is multiplied by this gain *
    // our speed
    // in inches per sec
    public static final double kPathFollowingMaxAccel = 80.0;  // inches per second ^ 2
    public static final double kPathFollowingMaxVel = 120.0; // inches per second
    public static final double kPathFollowingProfileKp = 0.3 / 12.0;  // % throttle per inch of error
    public static final double kPathFollowingProfileKi = 0.0;
    public static final double kPathFollowingProfileKv = 0.01 / 12.0;  // % throttle per inch/s of error
    public static final double kPathFollowingProfileKffv = 0.003889;  // % throttle per inch/s
    public static final double kPathFollowingProfileKffa = 0.001415;  // % throttle per inch/s^2
    public static final double kPathFollowingProfileKs = 0.1801 / 12.0;  // % throttle
    public static final double kPathFollowingGoalPosTolerance = 3.0;
    public static final double kPathFollowingGoalVelTolerance = 12.0;
    public static final double kPathStopSteeringDistance = 12.0;
    public static final double kDriveVoltageRampRate = 0.0;
    public static final int kDriveCurrentThrottledLimit = 30; // amps
    public static final int kDriveCurrentUnThrottledLimit = 80; // amps

    public static final double kFollowTargetSamplingDistance = 1.0;
    public static final double kFollowTargetLookahead = 30.0;
    public static final double kFollowTargetTolerance = 0.1;
    public static final double kFollowTargetSteeringScale = 0.05 * 24;
    public static final double kFollowTargetMaxThrottle = 0.8;
    public static final double kFollowTargetExtraWaypointDistance = 30.0;
    public static final double kPathKX = 4.0; // units/s per unit of error
    public static final double kPathLookaheadTime = 0.4; // seconds to look ahead along the path for steering
    public static final double kPathMinLookaheadDistance = 24.0; // inches

    // soundboard
    public static final int kSoundboardChannel = 5;

    // limelight
    public static final double kMaxTrackerDistance = 9.0;
    public static final double kMaxGoalTrackAge = 2.5;
    public static final double kMaxGoalTrackAgeNotTracking = 0.1;
    public static final double kMaxGoalTrackSmoothingTime = 0.5;
    public static final double kTrackStabilityWeight = 0.0;
    public static final double kTrackAgeWeight = 10.0;
    public static final double kTrackSwitchingWeight = 100.0;

    public static final double kCameraFrameRate = 90.0;
}
