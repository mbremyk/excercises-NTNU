package Fourteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.Arrays;

public class Cube implements KeyListener
{
	private static final int wKey = 87;
	private static final int aKey = 65;
	private static final int sKey = 83;
	private static final int dKey = 68;
	
	private Boolean[] keysPressed = new Boolean[4];
	
	final static BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static GL2 gl = Main.gl;
	private GLUT glut = Main.glut;
	private static Draw draw = Main.draw;
	private double[][] corners;
	private double[][] colours;
	
	public static final double rotateSpeed = 3;
	private double ws = 0, ad = 0;
	private double rotation[] = {0, 0};
	
	public Cube(double[][] corners, double[][] colours)
	{
		this.corners = new double[corners.length][corners[0].length];
		for (int i = 0; i < corners.length; i++)
		{
			for (int j = 0; j < corners[i].length; j++)
			{
				this.corners[i][j] = corners[i][j];
			}
		}
		
		this.colours = new double[colours.length][colours[0].length];
		for (int i = 0; i < colours.length; i++)
		{
			for (int j = 0; j < colours[i].length; j++)
			{
				this.colours[i][j] = colours[i][j];
			}
		}
		
		Arrays.fill(keysPressed, false);
	}
	
	public void draw()
	{
		draw.drawCube(corners, colours);
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case wKey:
				keysPressed[0] = true;
				break;
			case aKey:
				keysPressed[1] = true;
				break;
			case sKey:
				keysPressed[2] = true;
				break;
			case dKey:
				keysPressed[3] = true;
				break;
			default:
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case wKey:
				keysPressed[0] = false;
				break;
			case aKey:
				keysPressed[1] = false;
				break;
			case sKey:
				keysPressed[2] = false;
				break;
			case dKey:
				keysPressed[3] = false;
				break;
			default:
				break;
		}
	}
	
	public void rotate()
	{
		if (keysPressed[0] && !keysPressed[2])
		{
			ws = 1;
		}
		else if (keysPressed[2] && !keysPressed[0])
		{
			ws = -1;
		}
		else
		{
			ws = 0;
		}
		
		if (keysPressed[1] && !keysPressed[3])
		{
			ad = -1;
		}
		else if (keysPressed[3] && !keysPressed[1])
		{
			ad = 1;
		}
		else
		{
			ad = 0;
		}
		
		rotation[0] += rotateSpeed * ws;
		rotation[1] += rotateSpeed * ad;
		
		gl.glRotated(rotation[0], 1, 0, 0);
		gl.glRotated(rotation[1], 0, 1, 0);
	}
}
