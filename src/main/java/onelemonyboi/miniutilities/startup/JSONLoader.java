package onelemonyboi.miniutilities.startup;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.MiniUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static onelemonyboi.miniutilities.startup.JSONHelper.*;

public class JSONLoader {
    public static List<Item> oreList;

    public static void loadJSON() {
        Path JSONBasePath = FMLPaths.CONFIGDIR.get().resolve("miniutilitiescomplex");
        File JSONBaseFile = createDir(JSONBasePath);

        createFile(JSONBasePath.resolve("quantumquarryores.json"));

        Map<String, JsonObject> JSONFiles = JSONFinder(JSONBaseFile);
        for (Map.Entry<String, JsonObject> entry : JSONFiles.entrySet()) {
            switch (entry.getKey()) {
                case "quantumquarryores.json":
                    quantumQuarryOreLogic(entry.getValue());
            }
        }
    }

    public static void quantumQuarryOreLogic(JsonObject object) {
        /*
        {
          "ores": {
            "minecraft:iron_ore": {
              "weight": 2
            }
          }
        }
        */
        oreList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            if (entry.getValue().isJsonObject()) {
                for (Map.Entry<String, JsonElement> entryOre : entry.getValue().getAsJsonObject().entrySet()) {
                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entryOre.getKey()));
                    for (int i = 0; i < entryOre.getValue().getAsJsonObject().get("weight").getAsInt(); i++) {
                        oreList.add(item);
                    }
                }
            }
        }
    }
}
