package main;

import java.net.URI;

public class Settings {
    /* Van loading <-> max value equals 1 */
    public static float ordinaryQualityCoeff = 0.5F;
    public static float higherQualityCoeff = 0.3F;
    public static float specialtyQualityCoeff = 0.2F;

    /* Logging */
    public static String logFilePath = "lib/files/logs.txt";

    /* Files */
    public static String coffeesFilePath = "lib/files/coffees.txt";
    public static String vansFilePath = "lib/files/vans.txt";
    public static String vanGoodsFilePath = "lib/files/vanGoods.txt";
}
