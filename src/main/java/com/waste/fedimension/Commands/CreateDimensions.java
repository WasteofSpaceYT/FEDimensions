package com.waste.fedimension.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.waste.fedimension.Fedimension;
import com.waste.fedimension.world.DimensionManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.world.server.ServerWorld;

public class CreateDimensions {

    public CreateDimensions(CommandDispatcher<CommandSource> dispatcher) {
        Fedimension.LOGGER.info("Beep Beep");
        this.CreateDimensions(dispatcher);
    }

    private static int CreateDimension(CommandSource source, CommandContext<CommandSource> context) throws CommandSyntaxException {
        try {
            String name = context.getArgument("name", String.class);
            String descriptor = context.getArgument("descriptor", String.class);
            //String seed = context.getArgument("seed", String.class);
            ServerWorld world = DimensionManager.createWorld(source.getWorld(), name, "seed", descriptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void CreateDimensions(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("dimcreate")
                .requires(cs -> cs.hasPermissionLevel(1))
                .then(Commands.argument("name", StringArgumentType.word())
                        .then(Commands.argument("descriptor", StringArgumentType.string())
                                .then(Commands.argument("seed", StringArgumentType.string()))
                                .executes((command) -> CreateDimension(command.getSource(), command)))));
    }
}
