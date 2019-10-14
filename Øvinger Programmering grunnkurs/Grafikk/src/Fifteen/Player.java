package Fifteen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import static Fifteen.Main.gl;
import static Fifteen.Main.glu;

public class Player implements KeyListener
{
	private static final int wKey = 87;
	private static final int aKey = 65;
	private static final int sKey = 83;
	private static final int dKey = 68;
	
	private static final double rotationAngle = 10;
	
	final static BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public double rotation = 0;
	private double walkTime = 0;
	private double walkSpeed = 10;
	
	public double x, y, z;
	double[][] colours;
	
	private Cube head;
	private double[][] headPoints = {
			{-1.5,1.5,1.5},
			{1.5,1.5,1.5},
			{1.5,-1.5,1.5},
			{-1.5,-1.5,1.5},
			{-1.5,1.5,-1.5},
			{-1.5,-1.5,-1.5},
			{1.5,1.5,-1.5},
			{1.5,-1.5,-1.5}
	};
	
	private Cube torso;
	private double[][] torsoPoints = {
			{-2,1,1},
			{2,1,1},
			{2,-3,1},
			{-2,-3,1},
			{-2,1,-1},
			{-2,-3,-1},
			{2,1,-1},
			{2,-3,-1}
	};
	
	private Cube lArm;
	private Cube rArm;
	private Cube lLeg;
	private Cube rLeg;
	private double[][] armPoints = {
			{-1,1,1},
			{1,1,1},
			{1,-3,1},
			{-1,-3,1},
			{-1,1,-1},
			{-1,-3,-1},
			{1,1,-1},
			{1,-3,-1}
	};
	private Boolean[] keysPressed = new Boolean[4];
	
	public int walking;
	
	public Player(double x, double y, double z, double[][] colours)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.colours = new double[colours.length][colours[0].length];
		
		for(int i = 0; i < colours.length; i++)
		{
			for(int j = 0; j < colours[colours[i].length].length; j++)
			{
				this.colours[i][j] = colours[i][j];
			}
		}
		
		walking = 0;
		Arrays.fill(keysPressed, false);
		
		initializePlayer();
		updatePlayer();
	}
	
	public void initializePlayer()
	{
		head = new Cube(this.x, this.y, this.z, headPoints, colours);
		torso = new Cube(this.x, this.y - 2, this.z, torsoPoints, colours);
		lArm = new Cube(this.x - 3, this.y - 2, this.z, armPoints, colours);
		rArm = new Cube(this.x + 3, this.y - 2, this.z, armPoints, colours);
		lLeg = new Cube(this.x - 1, this.y - 6, this.z, armPoints, colours);
		rLeg = new Cube(this.x + 1, this.y - 6, this.z, armPoints, colours);
	}
	
	public void updatePlayer()
	{
		gl.glPushMatrix();
		gl.glLoadIdentity();
		Main.lookAt(this);
		Main.drawPlane();
		if(keysPressed[1])
		{
			rotation += rotationAngle;
		}
		
		if(keysPressed[3])
		{
			rotation -= rotationAngle;
		}
		gl.glRotated(rotation, 0, 1, 0);
		head.draw();
		torso.draw();
		if(walking > 0)
		{
			this.x += walkSpeed * 1 / 60 * Math.sin(rotation / 360 * 2 * Math.PI);
			this.z += walkSpeed * 1 / 60 * Math.cos(rotation / 360 * 2 * Math.PI);
			walkTime += 0.05;
		}
		else if(walking < 0)
		{
			this.x -= walkSpeed * 1 / 60 * Math.sin(rotation / 360 * 2 * Math.PI);
			this.z -= walkSpeed * 1 / 60 * Math.cos(rotation / 360 * 2 * Math.PI);
			walkTime -= 0.05;
		}
		else
		{
			walkTime = 0;
		}
		lArm.rotate(30 * Math.sin(walkTime),1,0,0);
		rArm.rotate(30 * -Math.sin(walkTime),1,0,0);
		lLeg.rotate(30 * -Math.sin(walkTime),1,0,0);
		rLeg.rotate(30 * Math.sin(walkTime),1,0,0);
		lArm.draw();
		rArm.draw();
		lLeg.draw();
		rLeg.draw();
		//glu.gluLookAt(this.x + 10 * Math.sin(this.rotation / 180 * Math.PI),this.y + 4,this.z - 10 * Math.cos(this.rotation / 180 * Math.PI), this.x, this.y, this.z,0f, 1f,0f);
		gl.glPopMatrix();
	}
	
	public void rotate(double angle, int x, int y, int z)
	{
		rotation += angle;
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case wKey:
				keysPressed[0] = true;
				walking = 1;
				break;
			case aKey:
				keysPressed[1] = true;
				break;
			case sKey:
				keysPressed[2] = true;
				walking = -1;
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
		switch(e.getKeyCode())
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
		
		if(!keysPressed[0] && !keysPressed[2])
		{
			walking = 0;
		}
	}
}
