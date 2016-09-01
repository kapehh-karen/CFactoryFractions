package me.kapehh.net.cfactoryfractions;

import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.exceptions.EmptyTownException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.UUID;

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
        OfflinePlayer playerMayor = Bukkit.getOfflinePlayer(mayor.getName());
        if (playerResident == null || playerMayor == null) return;

        boolean isTownHero = Main.permission.playerHas(null, playerMayor, ListPerm.HEROES.toString());
        boolean isTownOutcast = Main.permission.playerHas(null, playerMayor, ListPerm.OUTCAST.toString());
        boolean isTownAdmin = Main.permission.playerHas(null, playerMayor, ListPerm.ADMIN.toString());
        boolean isResHero = Main.permission.has(playerResident, ListPerm.HEROES.toString());
        boolean isResOutcast = Main.permission.has(playerResident, ListPerm.OUTCAST.toString());
        boolean isResAdmin = Main.permission.has(playerResident, ListPerm.ADMIN.toString());
        boolean isCorrect = isResAdmin || isTownAdmin || (isTownHero && isResHero) || (isTownOutcast && isResOutcast);

        if (ConfigParam.DEBUG) {
            System.out.println(String.format(
                "TownAddResidentEvent - isCorrect: %b\nisTownHero: %b, isTownOutcast: %b, isResHero: %b, isResOutcast: %b, isResAdmin: %b | Mayor [%s] ID: %d\n",
                isCorrect, isTownHero, isTownOutcast, isResHero, isResOutcast, isResAdmin, mayor.getName(), mayor.getUID()
            ));
        }

        if (!isCorrect) {
            playerResident.sendMessage(ConfigParam.MESSAGE_RESIDENT);
            try {
                event.getTown().removeResident(resident);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
