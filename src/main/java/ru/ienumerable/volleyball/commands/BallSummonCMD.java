package ru.ienumerable.volleyball.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import ru.ienumerable.volleyball.Config;
import ru.ienumerable.volleyball.Volleyball;
import ru.ienumerable.volleyball.ball.Ball;
import ru.ienumerable.volleyball.tools.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class BallSummonCMD implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Location location = null;
        World world = null;
        if (args.length >= 3 && sender instanceof Player && (args[0].startsWith("~") || args[1].startsWith("~") || args[2].startsWith("~"))) {
            try {
                Player player = (Player) sender;

                double x;
                double y;
                double z;

                if (args[0].startsWith("~"))
                    x = (Double.parseDouble((args[0].replace("~", "").equalsIgnoreCase("") ? "0" : args[0].replace("~", ""))) + player.getLocation().getX());
                else {
                    x = Double.parseDouble(args[0]);
                }

                if (args[1].startsWith("~"))
                    y = (Double.parseDouble((args[1].replace("~", "").equalsIgnoreCase("") ? "0" : args[1].replace("~", ""))) + player.getLocation().getY());
                else {
                    y = Double.parseDouble(args[1]);
                }

                if (args[2].startsWith("~"))
                    z = (Double.parseDouble((args[2].replace("~", "").equalsIgnoreCase("") ? "0" : args[1].replace("~", ""))) + player.getLocation().getZ());
                else {
                    z = Double.parseDouble(args[2]);
                }

                if (args.length > 3) {
                    world = Bukkit.getWorld(args[3]);
                } else {
                    world = player.getLocation().getWorld();
                }

                location = new Location(world, x, y, z);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments");
            }
        } else if (args.length >= 4) {
            try {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                world = Bukkit.getWorld(args[3]);
                location = new Location(world, x, y, z);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments");
            }
        } else if (sender instanceof Player) {
            location = ((Player) sender).getLocation();
            world = location.getWorld();
        }

        if (location == null || world == null) {
            sender.sendMessage(ChatColor.RED + "Not the location to summon");
            return true;
        }

        new Ball(location, new Vector(), Volleyball.getSkullsContainer().getSkull(Config.DEFAULT_SKULLSKIN));
        sender.sendMessage(ChatColor.GREEN + "Summoned Ball!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length <= 3) return List.of("~");
        else if (args.length == 4) {
            List<World> worlds = Bukkit.getWorlds();
            List<String> worldsName = new ArrayList<>();
            for (World world : worlds) {
                worldsName.add(world.getName());
            }
            return worldsName;
        };
        return List.of();
    }
}