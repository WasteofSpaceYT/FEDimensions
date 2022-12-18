package com.waste.fedimension.Items;

import com.waste.fedimension.Fedimension;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Fedimension.MOD_ID);


    public static final RegistryObject<Item> DIMENSION_TELEPORTER = ITEMS.register("dimension_teleporter",
            () -> new DimensionTeleporterItem(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
