package Fifteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.Arrays;

public class Cube
{
	private static final int wKey = 87;
	private static final int aKey = 65;
	private static final int sKey = 83;
	private static final int dKey = 68;
	
	final static BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static GL2 gl = Main.gl;
	private GLUT glut = Main.glut;
	private static Draw draw = Main.draw;
	private double[][] corners;
	private double[][] colours;
	
	public static final double rotateSpeed = 0.05;
	private double rotation[] = {0, 0, 0};
	
	private double x, y, z;
	
	public Cube(double x, double y, double z, double[][] corners, double[][] colours)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
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
	}
	
	public void draw()
	{
		gl.glPushMatrix();
		gl.glRotated(rotation[0],1,0,0);
		gl.glRotated(rotation[1],0,1,0);
		gl.glRotated(rotation[2],0,0,1);
		gl.glTranslated(x, y, z);
		draw.drawCube(corners, colours);
		gl.glPopMatrix();
	}
	
	/*@Override
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
	}*/
	
	public void rotate(double angle, double x, double y, double z)
	{
		this.rotation[0] = angle * x;
		this.rotation[1] = angle * y;
		this.rotation[2] = angle * z;
	}
	
	public void rotate(double angle)
	{
		gl.glRotated(angle, 0,1,0);
	}
}

