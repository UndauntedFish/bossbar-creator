package com.ben.bossbarcreator;

public class Undo
{
	String undoType;
	Object storedObject;
	
	public Undo(Object storedObject, String undoType)
	{
		this.storedObject = storedObject;
		this.undoType = undoType;
	}
	
	public Object doUndo()
	{
		return storedObject;
	}
}
