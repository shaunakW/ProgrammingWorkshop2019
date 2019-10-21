package com.shaunak.programmingworkshop2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive extends Subsystem {
    private static final int rightMasterID = 0;
    private static final int rightSlaveID = 1;
    private static final int leftMasterID = 2;
    private static final int leftSlaveID = 3;

    private TalonSRX rightMaster, rightSlave, leftMaster, leftSlave;

    private PeriodicIO mPeriodicIO = new PeriodicIO();

    private static Drive mInstance = null;

    private Drive() {
        rightMaster = new TalonSRX(rightMasterID);
        rightSlave = new TalonSRX(rightSlaveID);
        leftMaster = new TalonSRX(leftMasterID);
        leftSlave = new TalonSRX(leftSlaveID);

        rightSlave.set(ControlMode.Follower, rightMasterID);
        leftSlave.set(ControlMode.Follower, leftMasterID);

        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
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
        mPeriodicIO.right_demand = throttle + turn;
        mPeriodicIO.left_demand = throttle - turn;
    }

    @Override
    public void writePeriodicOutputs() {
        rightMaster.set(ControlMode.PercentOutput, mPeriodicIO.right_demand);
        leftMaster.set(ControlMode.PercentOutput, mPeriodicIO.left_demand);
    }

    @Override
    public void stop() {
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftMaster.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public boolean checkSystem() {
        return true;
    }

    @Override
    public void outputTelemetry() {

    }
}