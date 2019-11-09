package com.shaunak.frc2019.subsystems;

import com.shaunak.frc2019.Constants;
import com.shaunak.lib.util.CheesyDriveHelper;
import com.shaunak.lib.util.DriveSignal;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive extends Subsystem {
    private TalonSRX mRightMaster, mRightSlave, mLeftMaster, mLeftSlave;

    private PeriodicIO mPeriodicIO = new PeriodicIO();
    private CheesyDriveHelper mCheesyDriveHelper = new CheesyDriveHelper();

    private static Drive mInstance = null;

    private Drive() {
        mRightMaster = new TalonSRX(Constants.kDriveLeftMasterId);
        mRightSlave = new TalonSRX(Constants.kDriveLeftSlaveId);
        mLeftMaster = new TalonSRX(Constants.kDriveRightMasterId);
        mLeftSlave = new TalonSRX(Constants.kDriveRightSlaveId);

        mRightSlave.set(ControlMode.Follower, Constants.kDriveRightMasterId);
        mLeftSlave.set(ControlMode.Follower, Constants.kDriveLeftMasterId);

        mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
        mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
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

    public void setOpenLoop(double throttle, double turn) {
        DriveSignal driveSignal = mCheesyDriveHelper.cheesyDrive(throttle, turn, false);
        mPeriodicIO.right_demand = driveSignal.getRight();
        mPeriodicIO.left_demand = driveSignal.getLeft();
    }

    public boolean isHighGear() {
        return false;
    }

    @Override
    public void writePeriodicOutputs() {
        mRightMaster.set(ControlMode.PercentOutput, mPeriodicIO.right_demand);
        mLeftMaster.set(ControlMode.PercentOutput, mPeriodicIO.left_demand);
    }

    @Override
    public void stop() {
        mRightMaster.setNeutralMode(NeutralMode.Brake);
        mLeftMaster.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {

    }
}