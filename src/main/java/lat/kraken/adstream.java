package lat.kraken;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class adstream extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("Ad-Stream Ejecutado :3");
        saveDefaultConfig(); // Guardar la configuración predeterminada si no existe
    }
    public List<String> getTikTokMessages() {
        return config.getStringList("tiktok.messages");
    }

    public List<String> getTwitchMessages() {
        return config.getStringList("twitch.messages");
    }

    public List<String> getKickMessages() {
        return config.getStringList("kick.messages");
    }

    @Override
    public void onDisable() {
        getLogger().info("Ad-Stream Finalizado :c");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Comando para mostrar la ayuda
        if (cmd.getName().equalsIgnoreCase("adstream")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    // Mensajes de ayuda desde la configuración
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("help.title")));
                    for (String line : getConfig().getStringList("help.description")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
                    }
                    return true;
                }

                // Comando para recargar la configuración
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("adstream.reload")) { // Verifica permisos
                        reloadConfig();
                        sender.sendMessage(ChatColor.GREEN + getConfig().getString("reload.success"));
                    } else {
                        sender.sendMessage(ChatColor.RED + "No tienes permiso para usar este comando.");
                    }
                    return true;
                }
            }
        }

        // Comando para Twitch
        if (cmd.getName().equalsIgnoreCase("twitch") && args.length >= 2) {
            String canal = args[0];
            String url = args[1];
            // Reemplaza %canal% y %url% en el mensaje
            String mensaje = getConfig().getString("twitch.messages")
                    .replace("%canal%", canal)
                    .replace("%url%", url);
            if (sender instanceof Player) {
                mensaje = PlaceholderAPI.setPlaceholders((Player) sender, mensaje);
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensaje));
            return true;
        }

        // Comando para Kick
        if (cmd.getName().equalsIgnoreCase("kick") && args.length >= 2) {
            String canal = args[0];
            String url = args[1];
            // Reemplaza %canal% y %url% en el mensaje
            String mensaje = getConfig().getString("kick.messages")
                    .replace("%canal%", canal)
                    .replace("%url%", url);
            if (sender instanceof Player) {
                mensaje = PlaceholderAPI.setPlaceholders((Player) sender, mensaje);
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensaje));
            return true;
        }

        // Comando para TikTok
        if (cmd.getName().equalsIgnoreCase("tiktok") && args.length >= 2) {
            String canal = args[0];
            String url = args[1];
            // Reemplaza %canal% y %url% en el mensaje
            String mensaje = getConfig().getString("tiktok.messages")
                    .replace("%canal%", canal)
                    .replace("%url%", url);
            if (sender instanceof Player) {
                mensaje = PlaceholderAPI.setPlaceholders((Player) sender, mensaje);
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensaje));
            return true;
        }


        return false;
    }
}
