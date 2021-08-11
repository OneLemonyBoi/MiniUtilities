package onelemonyboi.miniutilities.startup.JSON;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;

import static onelemonyboi.miniutilities.startup.JSON.JSONHelper.*;
import static onelemonyboi.miniutilities.startup.JSON.QuantumQuarryJSON.*;

public class JSONLoader {
    public static void loadJSON() {
        Path JSONBasePath = FMLPaths.CONFIGDIR.get().resolve("miniutilitiescomplex");
        File JSONBaseFile = createDir(JSONBasePath);

        createQuantumQuarryJSON(JSONBasePath);
        readQuantumQuarryJSON(JSONBasePath);
    }
}
