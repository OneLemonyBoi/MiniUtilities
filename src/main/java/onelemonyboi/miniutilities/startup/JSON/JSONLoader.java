package onelemonyboi.miniutilities.startup.JSON;

import com.google.gson.JsonObject;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

import static onelemonyboi.miniutilities.startup.JSON.JSONHelper.*;
import static onelemonyboi.miniutilities.startup.JSON.QuantumQuarryJSON.*;

public class JSONLoader {
    public static void loadJSON() {
        Path JSONBasePath = FMLPaths.CONFIGDIR.get().resolve("miniutilitiescomplex");
        File JSONBaseFile = createDir(JSONBasePath);

        createQuantumQuarryJSON(JSONBasePath);

        Map<String, JsonObject> JSONFiles = JSONFinder(JSONBaseFile);
        for (Map.Entry<String, JsonObject> entry : JSONFiles.entrySet()) {
            switch (entry.getKey()) {
                case "quantumquarryores.json":
                    quantumQuarryOreLogic(entry.getValue());
            }
        }
    }
}
