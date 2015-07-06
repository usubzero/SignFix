package com.usubzero.signfix;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by ethanfine on 6/17/15.
 */
public class SignFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < (e.getWorld().getMaxHeight() - 1); y++) {
                for (int z = 0; z < 15; z++) {
                    Block block = e.getChunk().getBlock(x, y, z);

                    if (block != null && block.getState() != null && block.getState() instanceof Sign) {
                        Sign sign = (Sign) block.getState();

                        if (Arrays.toString(sign.getLines()).length() > 100) {
                            deleteBlock(sign);
                        }
                    }
                }
            }
        }
    }

    public void deleteBlock(final Sign sign) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                System.out.println("Deleting invalid signs at " + sign.getLocation().toString() + " :");

                sign.setLine(0, "§4§lCorrupt sign!");
                sign.setLine(1, "§4§lCorrupt sign!");
                sign.setLine(2, "§4§lCorrupt sign!");
                sign.setLine(3, "§4§lCorrupt sign!");

                sign.update();
            }
        });
    }

}
