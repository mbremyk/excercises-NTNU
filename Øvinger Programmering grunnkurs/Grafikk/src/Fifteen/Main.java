package Fifteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class Main extends GLCanvas implements GLEventListener
{
	private static GLCanvas canvas;
	
	public static GL2 gl;
	public static GLU glu;
	public static GLUT glut;
	
	public static Draw draw;
	public static Player player;
	
	private double[][][] planePoints;
	
	private final static double[][] colours = {
			{1,0,0},
			{0,1,0},
			{0,0,1},
			{1,1,0},
			{1,0,1},
			{0,1,1}
	};
	
	public Main()
	{
		this.addGLEventListener(this);
	}
	
	public static void main(String[] args)
	{
		canvas = new Main();
		canvas.setPreferredSize(new Dimension(1600, 900));
		
		final JFrame frame = new JFrame();
		frame.add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		FPSAnimator animator = new FPSAnimator(canvas,60,true);
		animator.start();
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL2();
		gl.glClearColor(0f,0f,0f,0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA,GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glTranslated(0,0,-4);
		glu = new GLU();
		glut = new GLUT();
		
		draw = new Draw();
		player = new Player(0,9,0,colours);
		
		this.addKeyListener(player);
		
		planePoints = new double[100][100][2];
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				planePoints[i][j][0] = i - 50;
				planePoints[i][j][1] = j - 50;
			}
		}
		player.updatePlayer();
		this.drawPlane();
	}
	
	public void dispose(GLAutoDrawable drawable) { }
	
	public void display(GLAutoDrawable drawable)
	{
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -4f);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		
		lookAt(player);
		player.updatePlayer();
		
		gl.glFlush();
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		if (height == 0) height = 1;   // prevent divide by zero
		float aspect = (float)width / height;
		
		//Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);
		
		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
		
		// Enable the model-view transform
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}
	
	static public void drawPlane()
	{
		gl.glColor3d(1,1,1);
		gl.glBegin(GL2.GL_LINES);
		for(int i = 0; i <= 100; i += 2)
		{
			gl.glVertex3d(i - 50,0,-50);
			gl.glVertex3d(i - 50,0,50);
		}
		for(int i = 0; i <= 100; i += 2)
		{
			gl.glVertex3d(-50,0,i - 50);
			gl.glVertex3d(50,0,i - 50);
		}
		gl.glEnd();
		gl.glFlush();
	}
	
	static public void lookAt(Player p)
	{
		double x = p.x;
		double y = p.y;
		double z = p.z;
		
		double length = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
		x /= length;
		y /= length;
		z /= length;
		// 10 * (Math.cos(this.rotation / 180 * Math.PI) * this.x + Math.sin(this.rotation / 180 * Math.PI) * this.z)
		//10 * (-Math.sin(this.rotation / 180 * Math.PI) * this.x + Math.cos(this.rotation / 180 * Math.PI) * this.z)
		glu.gluLookAt(p.x - 10 * (Math.cos((p.rotation / 180) * Math.PI)),p.y + 10,p.z - 10 * (Math.sin(p.rotation / 180 * Math.PI)), p.x, p.y, p.z,0f, 1f,0f);
		
	}
}