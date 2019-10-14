package com.shaunak.programmingworkshop2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive {
    private static final int rightMasterID = 0;
    private static final int rightSlaveID = 1;
    private static final int leftMasterID = 2;
    private static final int leftSlaveID = 3;

    private TalonSRX rightMaster, rightSlave, leftMaster, leftSlave;

    private static Drive instance = null;

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
        if (instance == null) {
            instance = new Drive();
        }
        return instance;
    }

    public void setOpenLoop(double throttle, double turn) {
        rightMaster.set(ControlMode.PercentOutput, throttle + turn);
        leftMaster.set(ControlMode.PercentOutput, throttle - turn);
    }

    public void stop() {
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftMaster.setNeutralMode(NeutralMode.Brake);
    }
}