package dev.scarday.AntiElytra;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.Nullable;

import static dev.scarday.AntiElytra.ColorUtility.colorize;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AntiElytraCommand implements BasicCommand {
    AntiElytra plugin;

    @Override
    public @Nullable String permission() {
        return plugin.getConfiguration().getCommand().getPermission();
    }

    @Override
    public void execute(CommandSourceStack css, String[] args) {
        plugin.getConfiguration().load();
        css.getSender().sendMessage(colorize(plugin.getConfiguration().getMessages().getReloaded()));
    }
}
