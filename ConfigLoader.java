import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("config.properties.txt")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("CRÍTICO: O arquivo config.txt não foi encontrado!");
            System.out.println("Verifique se ele está na mesma pasta onde está o arquivo package.bluej");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}