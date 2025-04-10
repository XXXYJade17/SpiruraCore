package com.xxxyjade17.spiruracore.Command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.xxxyjade17.spiruracore.Capability.Spirura;
import com.xxxyjade17.spiruracore.Config;
import com.xxxyjade17.spiruracore.Handler.CapabilityHandler;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;

import java.util.Optional;

public class PlayerCommand {
    private static PlayerCommand INSTANCE;
    private static final Logger LOGGER= SpiruraCore.getLogger();
    private static final Config config= Config.getINSTANCE();

    public static PlayerCommand getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerCommand();
        }
        return INSTANCE;
    }

    public void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        playerGetSpirura(dispatcher);
    }

    private void playerGetSpirura(CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralArgumentBuilder<CommandSourceStack> playerGetSpirura =
                Commands.literal("SpiruraCore")
                        .requires(source -> source.hasPermission(0))
                        .then(Commands.literal("get")
                                .executes(this::getSpirura));

        dispatcher.register(playerGetSpirura);
    }

    private int getSpirura(CommandContext<CommandSourceStack> context){
        try {
            ServerPlayer player = context.getSource().getPlayerOrException();
            Optional<Spirura> optionalSpirura =
                    Optional.ofNullable(player.getCapability(CapabilityHandler.SPIRURA_HANDLER));
            optionalSpirura.ifPresent(spirura -> {
                int rank = spirura.getRank();
                int level = spirura.getLevel();
                int experience = spirura.getExperience();
                String rankName= config.getRankName(rank);
                String levelName= config.getLevelName(level);
                System.out.println(rank+" "+level+" "+experience+" "+rankName+" "+levelName);
                context.getSource().sendSuccess(() ->
                        config.getMessage("spirura.player.get",rankName,levelName,experience),false);
            });
            return 1;
        } catch (Exception e) {
            LOGGER.error(config.getLogMessage("player.get.spirura.error"));
            return 0;
        }
    }
}
