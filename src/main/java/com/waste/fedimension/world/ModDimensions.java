package com.waste.fedimension.world;

import com.waste.fedimension.Fedimension;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> NEW_DIM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(Fedimension.MOD_ID, "new_dimension"));
}
