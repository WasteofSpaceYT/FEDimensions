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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

import static com.waste.fedimension.world.DimensionTeleporter.dimTeleport;

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
    @OnlyIn(Dist.CLIENT)


    public void init() {
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + -50, 102, 20, ITextComponent.getTextComponentOrEmpty("Overworld"), (button) -> {
            dimTeleport("overworld", player, world);
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + -25, 102, 20, ITextComponent.getTextComponentOrEmpty("Nether"), (button) -> {
            dimTeleport("nether", player, world);
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24, 102, 20, ITextComponent.getTextComponentOrEmpty("End"), (button) -> {
            dimTeleport("end", player, world);
        }));
        this.addButton(new Button(this.width / 2 - 51, this.height / 4 + 24 + +25, 102, 20, ITextComponent.getTextComponentOrEmpty("Superflat"), (button) -> {
            dimTeleport("superflat", player, world);
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
