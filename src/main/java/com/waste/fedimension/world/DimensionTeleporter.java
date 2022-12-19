package com.waste.fedimension.world;

import com.waste.fedimension.Fedimension;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.Optional;
import java.util.function.Function;

public class DimensionTeleporter implements ITeleporter {

    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public DimensionTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    public static void dimTeleport(String dimension, PlayerEntity player, World world){
        Fedimension.LOGGER.info("onUse");
        if (!world.isRemote()) {
            Fedimension.LOGGER.info("not remote");
            if (!player.isCrouching()) {
                Fedimension.LOGGER.info("not crouching");
                MinecraftServer server = player.getServer();
                if (server != null) {
                    Fedimension.LOGGER.info("not null");
                    /*if (world.getDimensionKey() == ModDimensions.END_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            player.changeDimension(overWorld, new DimensionTeleporter(player.getPosition(), false));
                        }
                    } else {
                        ServerWorld newDim = server.getWorld(ModDimensions.END_DIM);
                        if (newDim != null) {
                            player.changeDimension(newDim, new DimensionTeleporter(player.getPosition(), true));
                        }
                    }*/
                    switch(dimension){
                        case "overworld":
                            ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                            overWorld.getGameRules().get(GameRules.DO_MOB_SPAWNING).set(false, server);
                            if (overWorld != null) {
                                player.changeDimension(overWorld, new DimensionTeleporter(player.getPosition(), false));
                            }
                            break;
                        case "nether":
                            ServerWorld netherWorld = server.getWorld(ModDimensions.NETHER_DIM);
                            netherWorld.getGameRules().get(GameRules.DO_MOB_SPAWNING).set(false, server);
                            if (netherWorld != null) {
                                player.changeDimension(netherWorld, new DimensionTeleporter(player.getPosition(), false));
                            }
                            break;
                        case "end":
                            ServerWorld endWorld = server.getWorld(ModDimensions.END_DIM);
                            if (endWorld != null) {
                                player.changeDimension(endWorld, new DimensionTeleporter(player.getPosition(), false));
                            }
                            break;
                        case "superflat":
                            ServerWorld superflatWorld = server.getWorld(ModDimensions.NEW_DIM);
                            superflatWorld.getGameRules().get(GameRules.DO_MOB_SPAWNING).set(false, server);
                            if (superflatWorld != null) {
                                player.changeDimension(superflatWorld, new DimensionTeleporter(player.getPosition(), false));
                            }
                            break;
                    }
                }
            }
        }
    }
    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;

        if (!insideDimension) {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), 51, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getMaterial() != Material.AIR) &&
                !destinationWorld.getBlockState(destinationPos).isReplaceable(Fluids.WATER) &&
                destinationWorld.getBlockState(destinationPos.up()).getMaterial() != Material.AIR &&
                !destinationWorld.getBlockState(destinationPos.up()).isReplaceable(Fluids.WATER) && tries < 25) {
            destinationPos = destinationPos.up(2);
            tries++;
        }

        entity.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());


        return entity;
    }
}
