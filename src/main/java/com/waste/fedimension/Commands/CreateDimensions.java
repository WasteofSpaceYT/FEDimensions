package com.waste.fedimension.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.waste.fedimension.world.DimensionManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;

public class CreateDimensions implements Command<CommandSource> {
    private static final CreateDimensions CMD = new CreateDimensions();

    public static LiteralArgumentBuilder<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("dimcreate")
                .requires(cs -> cs.hasPermissionLevel(1))
                .then(Commands.argument("name", StringArgumentType.word())
                        .then(Commands.argument("descriptor", StringArgumentType.string())
                                .then(Commands.argument("seed", StringArgumentType.string()))
                                .executes(CMD)));
    }
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        try {
            String name = context.getArgument("name", String.class);
            String descriptor = context.getArgument("descriptor", String.class);
            String seed = context.getArgument("seed", String.class);
            ServerWorld world = DimensionManager.createWorld(context.getSource().getWorld(), name, seed, descriptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
