package onelemonyboi.miniutilities.startup;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.MiniUtilities;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

import static onelemonyboi.miniutilities.startup.JSONHelper.*;

public class JSONLoader {
    public static List<MutableTriple<Item, List<ResourceLocation>, List<ResourceLocation>>> oreList;

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
        MiniUtilities.LOGGER.info("Quantum Quarry Info: ");

        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            if (!entry.getValue().isJsonObject()) {
                return;
            }
            for (Map.Entry<String, JsonElement> entryOre : entry.getValue().getAsJsonObject().entrySet()) {

                int weight = 0;
                List<ResourceLocation> dims = new ArrayList<ResourceLocation>(){};
                List<ResourceLocation> biomes = new ArrayList<ResourceLocation>(){};

                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entryOre.getKey()));

                for (Map.Entry<String, JsonElement> entryValues : entryOre.getValue().getAsJsonObject().entrySet()) {
                    if (entryValues.getKey().equals("weight")) {
                        weight = entryValues.getValue().getAsInt();
                    }
                    else if (entryValues.getKey().equals("dimensions")) {
                        for (String str : new Gson().fromJson(entryValues.getValue(), String[].class)) {
                            dims.add(new ResourceLocation(str));
                        }
                    }
                    else if (entryValues.getKey().equals("biomes")) {
                        for (String str : new Gson().fromJson(entryValues.getValue(), String[].class)) {
                            biomes.add(new ResourceLocation(str));
                        }
                    }
                }
                MiniUtilities.LOGGER.info(item.getName().getString() + ", " + dims.toString() + ", " + biomes.toString());
                for (int i = 0; i < weight; i++) {
                    oreList.add(MutableTriple.of(item, dims, biomes));
                }
            }
        }
    }
}
