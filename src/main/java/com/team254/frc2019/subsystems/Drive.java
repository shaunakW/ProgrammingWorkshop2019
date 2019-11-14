package com.team254.frc2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.team254.frc2019.Constants;
import com.team254.frc2019.Kinematics;
import com.team254.lib.geometry.Twist2d;
import com.team254.lib.util.DriveSignal;
import com.team254.lib.util.Util;

import edu.wpi.first.wpilibj.Joystick;

public class Drive extends Subsystem {
    private PeriodicIO mPeriodicIO = new PeriodicIO();

    private static Drive mInstance = null;

    private final Joystick mThrottleJoystick, mTurnJoystick;
    private TalonSRX mRightMaster, mRightSlave, mLeftMaster, mLeftSlave;

    private Drive() {
        mThrottleJoystick = new Joystick(Constants.kThrottleJoystickPort);
        mTurnJoystick = new Joystick(Constants.kTurnJoystickPort);

        mRightMaster = new TalonSRX(Constants.kDriveLeftMasterId);
        mRightSlave = new TalonSRX(Constants.kDriveLeftSlaveId);
        mLeftMaster = new TalonSRX(Constants.kDriveRightMasterId);
        mLeftSlave = new TalonSRX(Constants.kDriveRightSlaveId);

        mRightSlave.set(ControlMode.Follower, Constants.kDriveRightMasterId);
        mLeftSlave.set(ControlMode.Follower, Constants.kDriveLeftMasterId);

//        mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
//        mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
    }

    public static Drive getInstance() {
        if (mInstance == null) {
            mInstance = new Drive();
        }
        return mInstance;
    }

    private static class PeriodicIO {
        double right_demand;
        double left_demand;
    }

    public void setOpenLoop(DriveSignal signal) {
        mPeriodicIO.right_demand = signal.getRight();
        mPeriodicIO.left_demand = signal.getLeft();
    }

    public boolean isHighGear() {
        return false;
    }

    @Override
    public void readPeriodicInputs() {
        double throttle = mThrottleJoystick.getRawAxis(1);
        double turn = mTurnJoystick.getRawAxis(0);
        setCheesyishDrive(throttle, turn, false);
    }

    public synchronized void setCheesyishDrive(double throttle, double wheel, boolean quickTurn) {
        if (Util.epsilonEquals(throttle, 0.0, 0.04)) {
            throttle = 0.0;
        }

        if (Util.epsilonEquals(wheel, 0.0, 0.035)) {
            wheel = 0.0;
        }

        final double kWheelGain = 0.05;
        final double kWheelNonlinearity = 0.05;
        final double denominator = Math.sin(Math.PI / 2.0 * kWheelNonlinearity);
        // Apply a sin function that's scaled to make it feel better.
        if (!quickTurn) {
            wheel = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * wheel);
            wheel = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * wheel);
            wheel = wheel / (denominator * denominator) * Math.abs(throttle);
        }

        wheel *= kWheelGain;
        DriveSignal signal = Kinematics.inverseKinematics(new Twist2d(throttle, 0.0, wheel));
        double scaling_factor = Math.max(1.0, Math.max(Math.abs(signal.getLeft()), Math.abs(signal.getRight())));
        setOpenLoop(new DriveSignal(signal.getLeft() / scaling_factor, signal.getRight() / scaling_factor));
    }

    @Override
    public void writePeriodicOutputs() {
        mRightMaster.set(ControlMode.PercentOutput, mPeriodicIO.right_demand);
        mLeftMaster.set(ControlMode.PercentOutput, mPeriodicIO.left_demand);
    }

    @Override
    public void stop() {
        mRightMaster.set(ControlMode.PercentOutput, 0.0);
        mLeftMaster.set(ControlMode.PercentOutput, 0.0);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {

    }
}