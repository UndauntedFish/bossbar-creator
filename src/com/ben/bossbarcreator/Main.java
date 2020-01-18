package com.ben.bossbarcreator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	BossBar bossbar; //Declare BossBar here on the outside, so it can be accessed between all classes
	
	@Override
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
		getCommand("bossbar").setExecutor(new BossBarCommand());
		
		bossbar = Bukkit.createBossBar(
				ChatColor.GOLD + "Welcome to the best server ever!", //Title
				BarColor.RED, //Color of the bar 
				BarStyle.SOLID, //The style of the bar's shape itself
				BarFlag.PLAY_BOSS_MUSIC); //OPTIONAL: Add extra flags to your boss bar
		
		
		bossbar.setProgress(1.0); //Sets the progress % of the bossbar. Range is 0.0 to 1.0.
	}

}
