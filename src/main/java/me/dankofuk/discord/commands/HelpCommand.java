package me.dankofuk.discord.commands;

import me.dankofuk.discord.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand extends ListenerAdapter {
    private String noPlayersTitle;
    private String title;
    private String footer;
    private String thumbnailUrl;
    private DiscordBot discordBot;

    public HelpCommand(DiscordBot discordBot) {
        this.discordBot = discordBot;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
                List<String> playerNames = discordBot.getMinecraftServer().getOnlinePlayers().stream()
                        .map(player -> player.getName())
                        .collect(Collectors.toList());

                EmbedBuilder helpEmbed = new EmbedBuilder();
                helpEmbed.setColor(Color.RED);
                helpEmbed.setTitle("__`Help Page 1/1`__");
                helpEmbed.setDescription("Command List");

                helpEmbed.addField("/help", "Shows this menu", false);
                helpEmbed.addField("/command [command]", "Sends a command to the server!", false);
                helpEmbed.addField("/online", "Shows the players online", false);
                helpEmbed.addField("/logs [user]", "Shows the log file for the user selected", false);
                helpEmbed.addField("/reload", "Reloads the configs for the bot related stuff.", false);
                helpEmbed.addField("/link", "Links your Minecraft account with Discord.", false);

                helpEmbed.setFooter("Help Page 1/1 - Made by Exotic Development");
                event.replyEmbeds(helpEmbed.build()).queue();
        }
    }
}
