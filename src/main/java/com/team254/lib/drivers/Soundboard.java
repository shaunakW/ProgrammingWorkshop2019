package com.team254.lib.drivers;

import com.team254.frc2019.Constants;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Soundboard {
    private static Soundboard mInstance;

    private final boolean mOnValue = false;

    private DigitalOutput output = new DigitalOutput(Constants.kSoundboardChannel);

    private Soundboard() { }

    public static Soundboard getInstance() {
        if (mInstance == null) {
            mInstance = new Soundboard();
            return mInstance;
        } else {
            return mInstance;
        }
    }

    public void turnOn() {
        output.set(mOnValue);
    }

    public void turnOff() {
        output.set(!mOnValue);
    }

    public boolean isSendingOutput() {
        return output.get();
    }
}
