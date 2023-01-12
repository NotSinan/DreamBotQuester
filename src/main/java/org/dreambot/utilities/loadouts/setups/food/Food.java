package org.dreambot.utilities.loadouts.setups.food;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.utilities.OwnedItems;

import java.util.Arrays;
import java.util.Comparator;

public enum Food {
    SHRIMP("Shrimp", 3),
    COOKED_CHICKEN("Cooked chicken", 3),
    COOKED_MEAT("Cooked chicken", 3),
    SARDINE("Sardine", 4),
    BREAD("Bread", 5),
    HERRING("Herring", 5),
    TROUT("Trout", 7),
    PIKE("Pike", 8),
    PEACH("Peach", 8),
    SALMON("Salmon", 9),
    TUNA("Tuna", 10),
    JUG_OF_WINE("Jug of wine", 11),
    STEW("Stew", 11),
    CAKE("Cake", 12),
    CAKE_2_3("2/3 cake", 8),
    CAKE_1_3("Slice of cake", 4),
    MEAT_PIE("Meat pie", 12),
    MEAT_PIE_1_2("Half a meat pie", 6),
    LOBSTER("Lobster", 12),
    BASS("Bass", 13),
    PLAIN_PIZZA("Plain pizza", 14),
    PLAIN_PIZZA_1_2("1/2 plain pizza", 7),
    SWORDFISH("Swordfish", 14),
    POTATO_WITH_BUTTER("Potato with butter", 14),
    APPLE_PIE("Apple pie", 14),
    APPLE_PIE_1_2("Half an apple pie", 7),
    CHOCOLATE_CAKE("Chocolate cake", 15),
    CHOCOLATE_CAKE_2_3("2/3 chocolate cake", 10),
    CHOCOLATE_CAKE_1_3("Chocolate slice", 5),
    TANGLED_TOADS_LEGS("Tangled toad's legs", 15),
    CHOCOLATE_BOMB("Chocolate bomb", 15),
    POTATO_WITH_CHEESE("Potato with cheese", 16),
    MEAT_PIZZA("Meat pizza", 16),
    MEAT_PIZZA_1_2("1/2 meat pizza", 8),
    MONKFISH("Monkfish", 16),
    ANCHOVY_PIZZA("Anchovy pizza", 18),
    ANCHOVY_PIZZA_1_2("1/2 anchovy pizza", 9),
    CURRY("Curry", 19),
    GUTHIX_REST_4("Guthix rest(4)", 20),
    GUTHIX_REST_3("Guthix rest(3)", 15),
    GUTHIX_REST_2("Guthix rest(2)", 10),
    GUTHIX_REST_1("Guthix rest(1)", 5),
    SHARK("Shark", 20),
    SEA_TURTLE("Sea turtle", 21),
    PINEAPPLE_PIZZA("Pineapple pizza", 22),
    PINEAPPLE_PIZZA_1_2("1/2 pineapple pizza", 22),
    MANTA_RAY("Manta ray", 22),
    TUNA_POTATO("Tuna potato", 22),
    DARK_CRAB("Dark crab", 22);

    private String foodName;
    private int healAmount;

    Food(String foodName, int healAmount) {
        this.foodName = foodName;
        this.healAmount = healAmount;
    }
    public String foodName() {
        return foodName;
    }

    public int healAmount() {
        return healAmount;
    }

    public static Food getBestOwnedFood() {
        return Arrays.stream(Food.values())
                .filter(food -> OwnedItems.contains(food.foodName()))
                .sorted(Comparator.comparingInt(Food::healAmount).reversed())
                .findFirst()
                .orElse(null);
    }
    public static Food getBestFoodInInventory() {
        return Arrays.stream(Food.values())
                .filter(food -> Inventory.contains(food.foodName()))
                .sorted(Comparator.comparingInt(Food::healAmount).reversed())
                .findFirst()
                .orElse(null);
    }
}
