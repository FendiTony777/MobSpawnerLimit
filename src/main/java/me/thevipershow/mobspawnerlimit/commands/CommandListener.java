package me.thevipershow.mobspawnerlimit.commands;

import me.thevipershow.mobspawnerlimit.MobSpawnerLimit;
import me.thevipershow.mobspawnerlimit.config.Values;
import me.thevipershow.mobspawnerlimit.enums.Messages;
import me.thevipershow.mobspawnerlimit.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {

    private final Values values = new Values();

    private void sendHelp(CommandSender target) {
        target.sendMessage(Utils.color("&8[&aMobSpawnerLimit&8]&7: &2Help page"));
        target.sendMessage(Utils.color("&7- &a/msl reload &7(reloads config.yml)"));
        target.sendMessage(Utils.color("&7- &a/msl set &7[&alimit&8|&aenabled&7] &2<&avalue&2>"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendHelp(sender);
        } else if (args.length == 1) {
            if (args[0].equals("reload")) {
                if (sender.hasPermission("msl.reload")) {
                    MobSpawnerLimit.plugin.reloadConfig();
                    values.setValues(MobSpawnerLimit.plugin.getConfig());
                    sender.sendMessage(Utils.color("&7- &aConfig.yml reloaded correctly."));
                } else {
                    sender.sendMessage(Messages.NO_PERMISSION.getString("msl.reload"));
                }
            } else {
                sender.sendMessage(Messages.WRONG_NUMBER.getString("+2"));
            }
        } else if (args.length == 3) {
            if (sender.hasPermission("msl.admin")) {
                if (args[0].equals("set")) {
                    switch (args[1].toLowerCase()) {
                        case "limit":
                            int limitInput = Integer.parseInt(args[2]);
                            if (limitInput >= 0 && limitInput <= 65535) {
                                values.setLimit(Integer.parseInt(args[2]), MobSpawnerLimit.plugin.getConfig());
                                sender.sendMessage(Messages.UPDATE_SUCCESS.getString(args[2]));
                            } else {
                                sender.sendMessage(Messages.UPDATE_FAILED.getString(args[2]));
                            }
                            break;
                        case "enabled":
                            if (args[2].equals("false") || args[2].equals("true")) {
                                values.setEnabled(Boolean.valueOf(args[2]), MobSpawnerLimit.plugin.getConfig());
                                sender.sendMessage(Messages.UPDATE_SUCCESS.getString(args[2]));
                            } else {
                                sender.sendMessage(Messages.UPDATE_FAILED.getString(args[2]));
                            }
                            break;
                        default:
                            sender.sendMessage(Messages.WRONG_ARGS.getString("limit|enabled"));
                            break;

                    }
                } else {
                    sender.sendMessage(Messages.WRONG_NUMBER.getString("0"));
                }
            } else {
                sender.sendMessage(Messages.NO_PERMISSION.getString("msl.admin"));
            }
        } else {
            sender.sendMessage(Messages.WRONG_NUMBER.getString("1|3"));
        }
        return false;
    }
}
