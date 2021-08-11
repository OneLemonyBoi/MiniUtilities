package onelemonyboi.miniutilities.startup.JSON;

import onelemonyboi.miniutilities.MiniUtilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        }
        return null;
    }
}
