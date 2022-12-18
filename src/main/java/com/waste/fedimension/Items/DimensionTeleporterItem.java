package com.waste.fedimension.Items;

import com.waste.fedimension.Fedimension;
import com.waste.fedimension.gui.DImensionTeleporterGui;
import com.waste.fedimension.world.DimensionTeleporter;
import com.waste.fedimension.world.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.MixinEnvironment;

public class DimensionTeleporterItem extends Item {
    public DimensionTeleporterItem(Item.Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
        Minecraft.getInstance().displayGuiScreen(new DImensionTeleporterGui(worldIn, playerIn, handIn));



        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
