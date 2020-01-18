package com.ben.bossbarcreator;

import java.util.Stack;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

public class Editor
{
	private String cmdArg, setOrDelete, userInput;
	private Main main;
	private BossBarCommand bossbarcommand;
	
	private Stack<String> titleUndos = new Stack<>();
	private Stack<BarColor> barColorUndos = new Stack<>();
	private Stack<BarFlag> barFlagUndos = new Stack<>();
	
	private Stack<Object> undos = new Stack<>();
	
	//private Stack<Object> undos = new Stack<>();
	
	public Editor (String cmdArg, String setOrDelete, String userInput, Main main, BossBarCommand bossbarcommand)
	{
		this.cmdArg = cmdArg;
		this.setOrDelete = setOrDelete;
		this.userInput = userInput;
		this.main = main;
		this.bossbarcommand = bossbarcommand;
	}
	
	private Object undoDriver()
	{
		if (undos.peek() instanceof String)
		{
			return (String) undos.pop();
		}
		if (undos.peek() instanceof BarColor)
		{
			return (BarColor) undos.pop();
		}
		if (undos.peek() instanceof BarStyle)
		{
			return (BarStyle) undos.pop();
		}
		if (undos.peek() instanceof BarFlag)
		{
			return (BarFlag) undos.pop();
		}
		return null;
	}
	
	
	
	public void editTitle()
	{	
		switch (setOrDelete)
		{
		case "set":
			undos.push(main.bossbar.getTitle());
			main.bossbar.setTitle(userInput);
		case "undo":
			if (!titleUndos.isEmpty())
			{
				//undoDriver Method
				try
				{
					main.bossbar.setTitle((String) undoDriver());
				}
				catch (NullPointerException e)
				{
					bossbarcommand.player.sendMessage(ChatColor.BLACK + "[BossBarCreator] Something horribly wrong has happened.");
				}
			}
			else
			{
				bossbarcommand.player.sendMessage(ChatColor.GRAY + "[BossBarCreator] Nothing left to undo!");
			}
		}
	}
	
	private BarColor processInputBarColor(String userInput)
	{
		if (userInput.equalsIgnoreCase("blue"))
		{
			return BarColor.BLUE;
		}
		if (userInput.equalsIgnoreCase("green"))
		{
			return BarColor.GREEN;
		}
		if (userInput.equalsIgnoreCase("pink"))
		{
			return BarColor.PINK;
		}
		if (userInput.equalsIgnoreCase("purple"))
		{
			return BarColor.PURPLE;
		}
		if (userInput.equalsIgnoreCase("red"))
		{
			return BarColor.RED;
		}
		if (userInput.equalsIgnoreCase("white"))
		{
			return BarColor.WHITE;
		}
		if (userInput.equalsIgnoreCase("yellow"))
		{
			return BarColor.YELLOW;
		}
		return null;
	}
	
	public void editBarColor()
	{
		BarColor inputColor = null;
		try
		{
			inputColor = processInputBarColor(userInput);
		}
		catch (NullPointerException e)
		{
			bossbarcommand.player.sendMessage(ChatColor.RED + "[BossBarCreator] You must chose either:\n"
					+ ChatColor.ITALIC + " blue, green, pink, purple, red, white, yellow "
					+ ChatColor.RESET + "" + ChatColor.RED + "for your bar color.");
		}
		
		switch (setOrDelete)
		{
		case "set":
			undos.push(main.bossbar.getColor());
			main.bossbar.setColor(inputColor);
		case "undo":
			if (!undos.isEmpty())
			{
				try
				{
					main.bossbar.setColor((BarColor) undoDriver());
				}
				catch (NullPointerException e)
				{
					bossbarcommand.player.sendMessage(ChatColor.BLACK + "[BossBarCreator] Something terribly wrong has happened.");
				}
			}
			else
			{
				bossbarcommand.player.sendMessage(ChatColor.GRAY + "[BossBarCreator] Nothing left to undo!");
			}
		}
	}
	
	private BarStyle processInputBarStyle(String userInput)
	{
		if (userInput.equalsIgnoreCase("solid"))
		{
			return BarStyle.SOLID;
		}
		if (userInput.equalsIgnoreCase("6segments"))
		{
			return BarStyle.SEGMENTED_6;
		}
		if (userInput.equalsIgnoreCase("10segments"))
		{
			return BarStyle.SEGMENTED_10;
		}
		if (userInput.equalsIgnoreCase("12segments"))
		{
			return BarStyle.SEGMENTED_12;
		}
		if (userInput.equalsIgnoreCase("20segments"))
		{
			return BarStyle.SEGMENTED_20;
		}
		return null;
	}
	
	public void editBarStyle()
	{
		BarStyle inputStyle = null;
		try
		{
			inputStyle = processInputBarStyle(userInput);
		}
		catch (NullPointerException e)
		{
			bossbarcommand.player.sendMessage(ChatColor.RED + "[BossBarCreator] You must chose either:\n"
					+ ChatColor.ITALIC + " solid, 6segments, 10segments, 12segments, 20segments "
					+ ChatColor.RESET + "" + ChatColor.RED + "for your bar style.");
		}
		
		switch (setOrDelete)
		{
		case "set":
			undos.push(main.bossbar.getStyle());
			main.bossbar.setStyle(inputStyle);
		case "undo":
			if (!undos.isEmpty())
			{
				try
				{
					main.bossbar.setStyle((BarStyle) undoDriver());
				}
				catch (NullPointerException e)
				{
					bossbarcommand.player.sendMessage(ChatColor.BLACK + "[BossBarCreator] Something terribly wrong has happened.");
				}
			}
			else
			{
				bossbarcommand.player.sendMessage(ChatColor.GRAY + "[BossBarCreator] Nothing left to undo!");
			}
		}
	}

	private BarFlag processInputBarFlag(String userInput)
	{
		if (userInput.equalsIgnoreCase("fog"))
		{
			return BarFlag.CREATE_FOG;
		}
		if (userInput.equalsIgnoreCase("darksky"))
		{
			return BarFlag.DARKEN_SKY;
		}
		if (userInput.equalsIgnoreCase("bossmusic"))
		{
			return BarFlag.PLAY_BOSS_MUSIC;
		}
		return null;
	}
	
	public void editFlags()
	{
		BarFlag inputFlag = null;
		try
		{
			inputFlag = processInputBarFlag(userInput);
		}
		catch (NullPointerException e)
		{
			bossbarcommand.player.sendMessage(ChatColor.RED + "[BossBarCreator] You must chose either:\n"
					+ ChatColor.ITALIC + " fog, darksky, 10segments, 12segments, 20segments "
					+ ChatColor.RESET + "" + ChatColor.RED + "for your bar flag.");
		}
		
		switch (setOrDelete)
		{
		case "set":
			undos.push(main.bossbar.getStyle());
			main.bossbar.setFlag(inputFlag);
		case "undo":
			if (!barStyleUndos.isEmpty())
			{
				main.bossbar.setStyle(barStyleUndos.pop());
			}
			else
			{
				bossbarcommand.player.sendMessage(ChatColor.GRAY + "[BossBarCreator] Nothing left to undo!");
			}
		}
	}
}
