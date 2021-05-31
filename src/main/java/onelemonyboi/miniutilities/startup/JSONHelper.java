package onelemonyboi.miniutilities.startup;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import onelemonyboi.miniutilities.MiniUtilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// TODO: MOVE TO LEMONLIB
public class JSONHelper {
    public static File createDir(Path path) {
        if (Files.exists(path)) {
            return path.toFile();
        }
        try {
            return Files.createDirectory(path).toFile();
        }
        catch (IOException e) {
            MiniUtilities.LOGGER.error("I/O Error! Either that or the config folder is nonexistent!");
            e.printStackTrace();
        }
        return null;
    }

    public static File createFile(Path path, String str) {
        if (Files.exists(path)) {
            return path.toFile();
        }

        try {
            File file = Files.createFile(path).toFile();
            FileWriter reader = new FileWriter(file);
            reader.write(str);
            reader.close();
            return Files.createFile(path).toFile();
        }
        catch (IOException e) {
            MiniUtilities.LOGGER.error("I/O Error! Either that or the config folder is nonexistent!");
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, JsonObject> JSONFinder(File file) {
        Map<String, JsonObject> output = new HashMap<>();
        if (file == null || file.listFiles() == null) {
            return output;
        }

        for (File files : file.listFiles()) {
            if (!files.exists()) {
                MiniUtilities.LOGGER.error(":thinkies:");
                continue;
            }
            try {
                FileReader reader = new FileReader(files);
                JsonObject object = new JsonParser().parse(reader).getAsJsonObject();
                output.put(files.getName(), object);
            }
            catch (Exception e) {
                MiniUtilities.LOGGER.error("What TF how did this happen? Your computer is clearly cursed and you should burn it ASAP.");
            }
        }

        return output;
    }
}
