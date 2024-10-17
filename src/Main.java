import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class ConfigurationManager {
    private static ConfigurationManager instance;
    private static final Object lock = new Object();

    private Map<String, String> settings;
    private ConfigurationManager() {
        settings = new HashMap<>();
    }
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public void loadSettings(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                settings.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("Error loading settings: " + e.getMessage());
        }
    }

    public void saveSettings(String filePath) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            Properties properties = new Properties();
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            properties.store(output, null);
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

    public Map<String, String> getAllSettings() {
        return Collections.unmodifiableMap(settings);
    }
}


public class Main {
    public static void main(String[] args) {
        ConfigurationManager configManager1 = ConfigurationManager.getInstance();
        ConfigurationManager configManager2 = ConfigurationManager.getInstance();

        System.out.println("Are both instances equal? " + (configManager1 == configManager2));
        configManager1.setSetting("username", "admin");
        configManager1.setSetting("password", "Mukha_a_s_d");
        configManager1.saveSettings("config.properties");
        ConfigurationManager configManager3 = ConfigurationManager.getInstance();
        configManager3.loadSettings("config.properties");
        System.out.println("Username: " + configManager3.getSetting("username"));
        System.out.println("Password: " + configManager3.getSetting("password"));
    }
}
