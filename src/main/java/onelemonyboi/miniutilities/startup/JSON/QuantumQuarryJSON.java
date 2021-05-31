package onelemonyboi.miniutilities.startup.JSON;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import onelemonyboi.miniutilities.datastorage.Quadruple;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static onelemonyboi.miniutilities.datastorage.Quadruple.from;
import static onelemonyboi.miniutilities.startup.JSON.JSONHelper.createFile;

public class QuantumQuarryJSON {
    public static List<MutableTriple<Item, List<ResourceLocation>, List<ResourceLocation>>> oreList;

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
