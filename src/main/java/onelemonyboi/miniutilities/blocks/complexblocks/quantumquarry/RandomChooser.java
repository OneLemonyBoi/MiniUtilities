package onelemonyboi.miniutilities.blocks.complexblocks.quantumquarry;

import java.util.*;
import java.util.function.Predicate;

public class RandomChooser<T extends WeightProvider> {
    private int sum = 0;
    private final int[] weights;
    private final float[] inverse_weights;
    private final ArrayList<T> weightedEntries;
    private final Random random = new Random();
    private int min_weight = Integer.MAX_VALUE;


    public RandomChooser(List<T> weightedEntries) {
        this.weightedEntries = new ArrayList<>(weightedEntries);
        weights = new int[weightedEntries.size()];
        inverse_weights = new float[weightedEntries.size()];
        for (int i = 0; i < weightedEntries.size(); i++) {
            T weightedEntry = weightedEntries.get(i);
            int weight = weightedEntry.getWeight();
            sum += weight;
            weightedEntries.set(i, weightedEntry);
            weights[i] = sum;
            inverse_weights[i] = 1.F / weight;
            if(weight < min_weight){
                min_weight = weight;
            }
        }
    }

    public Map<T, Integer> getItemsOfWorth(int worth, Predicate<T> blacklist) {
        Map<T, Integer> res = new HashMap<>();
        if(weightedEntries.stream().allMatch(blacklist)) {
            return res;
        }
        float maxPrice = 1.f / worth;
        float totalPrice = 0;
        while (totalPrice <= maxPrice) {
            int pickedItemIndex = getItemIndex();
            T pickedItem = weightedEntries.get(pickedItemIndex);
            if(blacklist.test(pickedItem))
                continue;
            res.put(pickedItem, res.getOrDefault(pickedItem, 0) + 1);
            totalPrice += inverse_weights[pickedItemIndex];
        }
        return res;
    }

    public T getItem() {
        return weightedEntries.get(getItemIndex());
    }

    private int getItemIndex() {
        int selectedItem = random.nextInt(sum);
        int pickedItemIndex = Arrays.binarySearch(weights, selectedItem);
        if (pickedItemIndex < 0) {
            pickedItemIndex = -pickedItemIndex - 1;
        }
        return pickedItemIndex;
    }

    public Map<T, Integer> getItemsOfHighestWorth(Predicate<T> blacklist) {
        return getItemsOfWorth(min_weight, blacklist);
    }
}
