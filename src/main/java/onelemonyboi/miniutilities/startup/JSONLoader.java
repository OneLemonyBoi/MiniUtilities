package onelemonyboi.miniutilities.startup;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.datastorage.Quadruple;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

import static onelemonyboi.miniutilities.startup.JSONHelper.*;
import static onelemonyboi.miniutilities.datastorage.Quadruple.*;

public class JSONLoader {
    public static List<MutableTriple<Item, List<ResourceLocation>, List<ResourceLocation>>> oreList;

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

    public static void quantumQuarryOreLogic(JsonObject object) {
        oreList = new ArrayList<>();

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
                    switch (entryValues.getKey()) {
                        case "weight":
                            weight = entryValues.getValue().getAsInt();
                            break;
                        case "dimensions":
                            for (String str : new Gson().fromJson(entryValues.getValue(), String[].class)) {
                                dims.add(new ResourceLocation(str));
                            }
                            break;
                        case "biomes":
                            for (String str : new Gson().fromJson(entryValues.getValue(), String[].class)) {
                                biomes.add(new ResourceLocation(str));
                            }
                            break;
                    }
                }
                for (int i = 0; i < weight; i++) {
                    oreList.add(MutableTriple.of(item, dims, biomes));
                }
            }
        }
    }

    public static void createQuantumQuarryJSON(Path JSONBasePath) {
        JsonObject ores = new JsonObject();

        List<Quadruple<String, Integer, String[], String[]>> oreMap = new LinkedList<>();
        oreMap.add(from("minecraft:coal_ore", 4, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:iron_ore", 3, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:gold_ore", 1, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:diamond_ore", 1, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:lapis_ore", 2, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:redstone_ore", 4, new String[]{"minecraft:overworld"}, new String[]{}));
        oreMap.add(from("minecraft:emerald_ore", 1, new String[]{"minecraft:overworld"}, new String[]{}));

        for (Quadruple<String, Integer, String[], String[]> quad : oreMap) {
            JsonObject ore = new JsonObject();
            JsonArray dimensions = new JsonArray(), biomes = new JsonArray();
            for (String dim : quad.three) {dimensions.add(dim);}
            for (String biome : quad.four) {biomes.add(biome);}

            ore.addProperty("weight", quad.two);
            ore.add("dimensions", dimensions);
            ore.add("biomes", biomes);

            ores.add(quad.one, ore);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonFinal = new JsonObject();
        jsonFinal.add("ores", ores);
        createFile(JSONBasePath.resolve("quantumquarryores.json"), gson.toJson(jsonFinal));
    }
}
