package com.example.configs;

/**
 * Created by Megurine Lucas on 29-03-2016.
 */
public class ConfigConstants {
    public static final String IP_ADDRESS = "192.168.1.12";
    //public static final String IP_ADDRESS = "10.82.131.49";
    public static final String PORT = "3000";
    public static final String DEFAULT_IMAGE_URL = "http://"+ConfigConstants.IP_ADDRESS + ":" + ConfigConstants.PORT + "/assets/shared/imgs/home-icon.png";

    private ConfigConstants() {
        //Nothing
    }
}
