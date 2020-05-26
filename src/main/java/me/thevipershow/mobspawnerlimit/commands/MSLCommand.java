package me.thevipershow.mobspawnerlimit.commands;

import java.util.Locale;
import me.thevipershow.mobspawnerlimit.config.Values;
import static me.thevipershow.mobspawnerlimit.enums.Messages.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public final class MSLCommand implements CommandExecutor {
    private static MSLCommand instance = null;
    private final Values values;

    private MSLCommand(final Values values) {
        this.values = values;
    }

    public static MSLCommand getInstance(final Values values) {
        return instance != null ? instance : (instance = new MSLCommand(values));
    }


    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(final CommandSender sender,
                             final Command command,
                             final String message,
                             final String[] args) {
        final int length = args.length;
        if (length <= 2) {
            switch (length) {
                case 0:
                    return false;
                case 1: {
                    if (args[0].equalsIgnoreCase("reload")) {
                        values.updateValues();
                        sendMessage(sender, UPDATED_CORRECTLY);
                        return true;
                    } else {
                        return false;
                    }
                }
                case 2: {
                    final String secondArg = args[1].toLowerCase(Locale.getDefault());
                    switch (args[0].toLowerCase(Locale.getDefault())) {
                        case "enable": {
                            if (secondArg.equals("true") || secondArg.equals("false")) {
                                final boolean toggleTo = Boolean.parseBoolean(secondArg);
                                values.setEnabled(toggleTo);
                                sendMessage(sender, UPDATED_CORRECTLY);
                            } else {
                                sendMessage(sender, WRONG_ARGS.getString(secondArg));
                            }
                            break;
                        }
                        case "limit": {
                            try {
                                final int max = Integer.parseInt(secondArg);
                                if (max < 1 || max > 65536) {
                                    sendMessage(sender, INVALID_RANGE);
                                } else {
                                    sendMessage(sender, UPDATED_CORRECTLY);
                                    values.setLimit(max);
                                }
                                break;
                            } catch (final NumberFormatException e) {
                                sendMessage(sender, WRONG_ARGS.getString(secondArg));
                                break;
                            }
                        }
                        default:
                            sendMessage(sender, WRONG_ARGS.getString(args[0]));
                            break;
                    }
                }
            }
        } else {
            sendMessage(sender, TOO_MANY_ARGS);
        }
        return true;
    }
}
