package onelemonyboi.miniutilities.startup.JSON;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.RandomChooser;
import onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry.WeightProvider;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static onelemonyboi.miniutilities.startup.JSON.JSONHelper.createFile;

public class QuantumQuarryJSON {
    public static RandomChooser<OreInfo> randomOreChooser;

    public static void readQuantumQuarryJSON(Path JSONBasePath) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(JSONBasePath.resolve("quantumquarryores.json"));
            List<OreInfo> oreList = gson.fromJson(reader, new TypeToken<List<OreInfo>>() {
            }.getType());
            randomOreChooser = new RandomChooser<>(oreList);
        }
        catch (Exception e) {

        }
    }

    public static void createQuantumQuarryJSON(Path JSONBasePath) {
        List<OreInfo> oreMap = new LinkedList<>();
        ArrayList<String> overworld = new ArrayList<>();
        overworld.add("minecraft:overworld");

        oreMap.add(new OreInfo("minecraft:coal_ore", 4, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:iron_ore", 3, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:gold_ore", 1, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:diamond_ore", 1, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:lapis_ore", 2, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:redstone_ore", 4, overworld, new ArrayList<>()));
        oreMap.add(new OreInfo("minecraft:emerald_ore", 1, overworld, new ArrayList<>()));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        createFile(JSONBasePath.resolve("quantumquarryores.json"), gson.toJson(oreMap));
    }

    public static class OreInfo implements WeightProvider {
        public String name;
        public int weight;
        public List<String> dimensions;
        public List<String> biomes;

        public OreInfo(String name, int weight, List<String> dimensions, List<String> biomes) {
            this.name = name;
            this.weight = weight;
            this.dimensions = dimensions;
            this.biomes = biomes;
        }

        @Override
        public int getWeight() {
            return weight;
        }
    }
}
