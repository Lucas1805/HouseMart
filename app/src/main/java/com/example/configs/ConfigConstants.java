package com.example.configs;

/**
 * Created by Megurine Lucas on 29-03-2016.
 */
public class ConfigConstants {
    public static final String ipAddress = "192.168.230.102";
    //public static final String ipAddress = "10.82.131.49";
    public static final String port = "3000";
    public static final String DEFAULT_IMAGE_URL = "http://"+ConfigConstants.ipAddress + ":" + ConfigConstants.port + "/assets/shared/imgs/home-icon.png";

    private ConfigConstants() {
        //Nothing
    }
}
