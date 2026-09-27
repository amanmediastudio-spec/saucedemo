package com.saucedemo.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to read configuration properties from config.properties
 * with fallback to System properties for CLI overrides.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("config.properties not found in classpath.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        // System property takes precedence over config.properties
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.trim().isEmpty()) {
            return systemProp.trim();
        }
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return value != null ? Boolean.parseBoolean(value.trim()) : defaultValue;
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        try {
            return value != null ? Integer.parseInt(value.trim()) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String getStandardUsername() {
        return getProperty("standardUsername", "standard_user");
    }

    public static String getPassword() {
        return getProperty("password", "secret_sauce");
    }

    public static String getLockedOutUsername() {
        return getProperty("lockedOutUsername", "locked_out_user");
    }

    public static String getInvalidPassword() {
        return getProperty("invalidPassword", "wrong_password");
    }
}
