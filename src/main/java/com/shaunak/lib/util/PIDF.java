package com.shaunak.lib.util;

import edu.wpi.first.wpilibj.Timer;

public class PIDF {
    private double kP, kI, kD, kF, setpoint;
    private double mPreviousError = 0;
    private double mPreviousTime = 0;
    private double mIntegral = 0;

    public PIDF(double kP, double kI, double kD, double kF, double setpoint) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.setpoint = setpoint;
    }

    public double update(double position) {
        double error = setpoint - position;
        double currentTime = Timer.getFPGATimestamp();
        double dt = currentTime - mPreviousTime;

        mIntegral += error * dt;
        double derivative = (error - mPreviousError) / dt;

        mPreviousError = error;
        mPreviousTime = currentTime;

        return kP * error + kI * mIntegral + kD * derivative + kF * setpoint;
    }

    public double getkP() {
        return kP;
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public double getkI() {
        return kI;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    public double getkD() {
        return kD;
    }

    public void setkD(double kD) {
        this.kD = kD;
    }

    public double getkF() {
        return kF;
    }

    public void setkF(double kF) {
        this.kF = kF;
    }

    public double getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }
}
