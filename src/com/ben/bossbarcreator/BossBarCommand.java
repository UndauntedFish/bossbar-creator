package com.ben.bossbarcreator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossBarCommand implements CommandExecutor
{
	Player player;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			player = (Player) sender;
			
			if (player.hasPermission("bossbar.allowedit"))
			{
				//            0    1    2  
				// /bossbar title set Howdy
				buildBossBarEditor(args[0], args[1], args[2]);
			}
		}
		
		return false;
	}

	private void buildBossBarEditor(String cmdArg, String setOrDelete, String userInput)
	{
		if (cmdArg != null)
		{
			switch (cmdArg)
			{
			case "title":
				//create new BossBarTitle
				
			case "color":
				//create new BossBarColor
			case "style":
				//create new BossBarStyle
			case "flags":
				//create new BossBarFlags
			}
		}	
	}
}
