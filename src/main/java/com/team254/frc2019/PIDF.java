package com.team254.frc2019;

import edu.wpi.first.wpilibj.Timer;

public class PIDF {
    private final double kP, kI, kD, kF;
    private double mSetpoint;
    private double mPreviousError = 0;
    private double mPreviousTime = 0;
    private double mIntegral = 0;
    private final double kIZone = 25;

    public PIDF(double p, double i, double d, double f, double setpoint) {
        kP = p;
        kI = i;
        kD = d;
        kF = f;
        mSetpoint = setpoint;
    }

    public double update(double position) {
        double error = mSetpoint - position;
        double currentTime = Timer.getFPGATimestamp();
        double dt = currentTime - mPreviousTime;

        mIntegral += error * dt;
        double derivative = (error - mPreviousError) / dt;

        mPreviousError = error;
        mPreviousTime = currentTime;

        if (Math.abs(error) < kIZone) return kP * error + kI * mIntegral + kD * derivative + kF * mSetpoint;
        return kP * error + kD * derivative + kF * mSetpoint;
    }

    public double getSetpoint() {
        return mSetpoint;
    }

    public void setSetpoint(double setpoint) {
        mSetpoint = setpoint;
    }
}