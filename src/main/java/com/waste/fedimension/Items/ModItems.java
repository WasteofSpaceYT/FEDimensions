package com.waste.fedimension.Items;

import com.waste.fedimension.Fedimension;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Fedimension.MOD_ID);


    public static final RegistryObject<Item> DIMENSION_TELEPORTER = ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_GROUP)));
}
