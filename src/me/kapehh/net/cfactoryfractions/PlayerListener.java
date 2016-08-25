package me.kapehh.net.cfactoryfractions;

import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.exceptions.EmptyTownException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by karen on 25.08.2016.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        /*System.out.println("DAMAGER: " + event.getDamager().toString());
        System.out.println("ENTITY: " + event.getEntity().toString());*/
    }

    @EventHandler
    public void onResidentAddTown(TownAddResidentEvent event) {
        Resident resident = event.getResident();
        if (resident == null) return;

        Resident mayor = event.getTown().getMayor();
        if (mayor == null || mayor.isNPC()) return;

        Player playerResident = Bukkit.getPlayer(resident.getName());
        Player playerMayor = Bukkit.getPlayer(mayor.getName());
        if (playerResident == null || playerMayor == null) return;

        boolean isTownHero = Main.permission.has(playerMayor, ListPerm.HEROES.toString());
        boolean isTownOutcast = Main.permission.has(playerMayor, ListPerm.OUTCAST.toString());
        boolean isResHero = Main.permission.has(playerResident, ListPerm.HEROES.toString());
        boolean isResOutcast = Main.permission.has(playerResident, ListPerm.OUTCAST.toString());
        boolean isCorrect = (isTownHero && isResHero) || (isTownOutcast && isResOutcast);

        if (!isCorrect) {
            playerMayor.sendMessage(ChatColor.RED + "Запрещено инвайтить игроков из противоположной фракции!");
            playerResident.sendMessage(ChatColor.RED + "Вы не можете вступить в город противоположной фракции!");
            try {
                event.getTown().removeResident(resident);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
