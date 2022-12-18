package com.waste.fedimension.gui;

import com.waste.fedimension.Fedimension;
import com.waste.fedimension.world.DimensionTeleporter;
import com.waste.fedimension.world.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.Mod;

public class DImensionTeleporterGui extends Screen {

    private Screen parent;
    private World world;
    private PlayerEntity player;
    private Hand hand;


    public DImensionTeleporterGui(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super(ITextComponent.getTextComponentOrEmpty("Dimension Teleporter"));
        this.parent = parent;
        this.world = worldIn;
        this.player = playerIn;
        this.hand = handIn;
    }

    public void dimTeleport(String dimension){
        Fedimension.LOGGER.info("onUse");
        if (!world.isRemote()) {
            if (!player.isCrouching()) {
                MinecraftServer server = player.getServer();

                if (server != null) {
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

    public void init() {
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + -50, 102, 20, ITextComponent.getTextComponentOrEmpty("Overworld"), (button) -> {
            dimTeleport("overworld");
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + -25, 102, 20, ITextComponent.getTextComponentOrEmpty("Nether"), (button) -> {
            dimTeleport("nether");
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24, 102, 20, ITextComponent.getTextComponentOrEmpty("End"), (button) -> {
            dimTeleport("end");
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + +25, 102, 20, ITextComponent.getTextComponentOrEmpty("Superflat"), (button) -> {
            dimTeleport("superflat");
        }));
        /*this.addDrawableChild(new ButtonWidget(this.width / 2 - 204, this.height / 4 + 24 + -25, 102, 20, Text.translatable("FlyHack: " + (Hacks.FlyHackEnabled ? "On" : "Off")), (button) -> {
            Hacks.FlyHackEnabled = !Hacks.FlyHackEnabled;
            button.setMessage(Text.literal("FlyHack: " + (Hacks.FlyHackEnabled ? "On" : "Off")));
        }));
        this.addDrawableChild(new SliderWidget(this.width / 2 - 204, this.height / 4 + 24, 102, 20, Text.translatable("Gamma: " + Hacks.Gamma), Hacks.Gamma/10) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.translatable("Gamma: " + Math.round(value * 10)));
            }

            @Override
            protected void applyValue() {
                Hacks.Gamma = Double.valueOf(Math.round(value*10));
            }
        });
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 204, this.height / 4 + 24 + 25, 102, 20, Text.translatable("X-Ray: " + (Hacks.XRAYEnabled ? "On" : "Off")), (button) -> {
            Hacks.XRAYEnabled = !Hacks.XRAYEnabled;
            if(Hacks.XRAYEnabled) Hacks.Gamma = 10.0;
            button.setMessage(Text.literal("X-Ray: " + (Hacks.XRAYEnabled ? "On" : "Off")));
            MinecraftClient client = MinecraftClient.getInstance();
            client.worldRenderer.reload();
        }));*/
    }
}
