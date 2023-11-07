package net.rockeatersteve.tutorialmod.item;

import net.rockeatersteve.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rockeatersteve.tutorialmod.item.custom.DetectorItem;
import net.rockeatersteve.tutorialmod.item.custom.FuelItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> SCYTHE = ITEMS.register("scythe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPEAR = ITEMS.register("spear",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REINFORCED_STICK = ITEMS.register("reinforced_stick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DETECTOR = ITEMS.register("detector",
            () -> new DetectorItem(new Item.Properties().durability(250)));
    public static final RegistryObject<Item> CHOCOLATE_CAKE = ITEMS.register("chocolate_cake",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHOCOLATE_CAKE)));
    public static final RegistryObject<Item> PEAT_BRICK = ITEMS.register("peat_brick",
            () -> new FuelItem(new Item.Properties(), 200));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}