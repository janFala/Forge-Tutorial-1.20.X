package net.rockeatersteve.tutorialmod.item;

import net.rockeatersteve.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rockeatersteve.tutorialmod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SCYTHE.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ModItems.SCYTHE.get());
                    pOutput.accept(ModItems.SPEAR.get());

                    pOutput.accept(ModItems.REINFORCED_STICK.get());

                    pOutput.accept(ModItems.RUBY.get());
                    pOutput.accept(ModItems.RAW_RUBY.get());

                    pOutput.accept(ModBlocks.RUBY_BLOCK.get());
                    pOutput.accept(ModBlocks.RAW_RUBY_BLOCK.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}