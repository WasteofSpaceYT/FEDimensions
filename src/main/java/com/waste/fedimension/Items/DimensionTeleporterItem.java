package com.waste.fedimension.Items;

import com.waste.fedimension.world.DimensionTeleporter;
import com.waste.fedimension.world.ModDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DimensionTeleporterItem extends Item {
    public DimensionTeleporterItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count){
        if (!worldIn.isRemote()) {
            if (!livingEntityIn.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == ModDimensions.NEW_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            livingEntityIn.changeDimension(overWorld, new DimensionTeleporter(livingEntityIn.getBedPosition(), false));
                        }
                    } else {
                        ServerWorld newDim = server.getWorld(ModDimensions.NEW_DIM);
                        if (newDim != null) {
                            livingEntityIn.changeDimension(newDim, new DimensionTeleporter(livingEntityIn.getBedPosition(), true));
                        }
                    }
                }
            }
        }

    }
}
