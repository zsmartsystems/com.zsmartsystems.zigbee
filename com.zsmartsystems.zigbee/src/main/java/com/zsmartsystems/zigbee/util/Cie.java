package com.zsmartsystems.zigbee.util;

/**
 * Provides methods to convert colours from RGB to CIE colour space.
 * <p>
 * The CIE XYZ color space encompasses all color sensations that an average
 * person can experience. It serves as a standard reference against which many
 * other color spaces are defined.
 */
public class Cie {
    public double x;
    public double y;
    public double Y;

    public static Cie rgb2cie2(double R, double G, double B) {
        double X = 0.412315f * R + 0.357600f * G + 0.180500f * B;
        double Y = 0.212600f * R + 0.715200f * G + 0.072200f * B;
        double Z = 0.019327f * R + 0.119200f * G + 0.950633f * B;

        Cie cie = new Cie();
        cie.x = X / (X + Y + Z);
        cie.y = Y / (X + Y + Z);
        cie.Y = Y;
        return cie;
    }

    public static Cie rgb2cie(double r, double g, double b) {

        double X, Y, Z;

        if (r <= 0.04045)
            r = r / 12;
        else
            r = (float) Math.pow((r + 0.055) / 1.055, 2.4);

        if (g <= 0.04045)
            g = g / 12;
        else
            g = (float) Math.pow((g + 0.055) / 1.055, 2.4);

        if (b <= 0.04045)
            b = b / 12;
        else
            b = (float) Math.pow((b + 0.055) / 1.055, 2.4);

        X = 0.436052025f * r + 0.385081593f * g + 0.143087414f * b;
        Y = 0.222491598f * r + 0.71688606f * g + 0.060621486f * b;
        Z = 0.013929122f * r + 0.097097002f * g + 0.71418547f * b;

        double x;
        double y;

        double sum = X + Y + Z;
        if (sum != 0) {
            x = X / sum;
            y = Y / sum;
        } else {
            float Xr = 0.964221f; // reference white
            float Yr = 1.0f;
            float Zr = 0.825211f;

            x = Xr / (Xr + Yr + Zr);
            y = Yr / (Xr + Yr + Zr);
        }
        Cie cie = new Cie();
        cie.x = x;
        cie.y = y;
        cie.Y = Y;
        return cie;
    }

    public static Cie rgb2cie3(double R, double G, double B) {
        double X = 0.436052025f * R + 0.385081593f * G + 0.143087414f * B;
        double Y = 0.222491598f * R + 0.71688606f * G + 0.060621486f * B;
        double Z = 0.013929122f * R + 0.097097002f * G + 0.71418547f * B;

        Cie cie = new Cie();
        cie.x = X / (X + Y + Z);
        cie.y = Y / (X + Y + Z);
        cie.Y = Y;
        return cie;
    }

}
