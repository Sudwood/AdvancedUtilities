package com.sudwood.advancedutilities.client.gui;

public class TextSquare
{
	int x = 0;
	int y = 0;
	int xSize = 0;
	int ySize = 0;
	String hoverText = "";
	
	public TextSquare(int xCoord, int yCoord, int sizeX, int sizeY, String text)
	{
		x = xCoord;
		y = yCoord;
		xSize = sizeX;
		ySize = sizeY;
		hoverText = text;
	}
}