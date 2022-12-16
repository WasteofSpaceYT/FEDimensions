package com.waste.fedimension.world;

import com.waste.fedimension.Fedimension;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * Manages runtime handling of a dimension. That includes the compiled descriptors and creation of dimensions
 */
public class DimensionManager {

    public static ServerWorld createWorld(World world, String name, String seed, String descriptor) {
        try {
            ResourceLocation id = new ResourceLocation(Fedimension.MOD_ID, name);

            ServerWorld wrld = world.getServer().getWorld(RegistryKey.getOrCreateKey(Registry.WORLD_KEY, id));
            System.out.println(wrld);

            return wrld;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}