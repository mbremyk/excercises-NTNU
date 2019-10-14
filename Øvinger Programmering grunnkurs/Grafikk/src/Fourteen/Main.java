package Fourteen;

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
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Main extends GLCanvas implements GLEventListener
{
	private static GLCanvas canvas;
	
	public static GL2 gl;
	public static GLU glu;
	public static GLUT glut;
	
	public static Draw draw;
	private Cube cube;
	
	private final double[][] corners = {
			{-1,1,-1},
			{1,1,-1},
			{1,-1,-1},
			{-1,-1,-1},
			{-1,1,1},
			{-1,-1,1},
			{1,1,1},
			{1,-1,1}
	};
	private final double[][] colours = {
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
		canvas.setPreferredSize(new Dimension(250, 250));
		
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
		glu = new GLU();
		glut = new GLUT();
		
		draw = new Draw();
		cube = new Cube(corners,colours);
		canvas.addKeyListener(cube);
	}
	
	public void dispose(GLAutoDrawable drawable) { }
	
	public void display(GLAutoDrawable drawable)
	{
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -4f);
		glu.gluLookAt(0f,0f,-8f, 0f,0f,-4f,0f, 1f,0f);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		//gl.glOrtho(0, 0, 0, 0, 0, 0);
		gl.glPushMatrix();
		cube.rotate();
		gl.glViewport(0,0,125,125);
		cube.draw();
		gl.glPopMatrix();
		gl.glViewport(125, 125, 125, 125);
		cube.draw();
		gl.glPushMatrix();
		gl.glViewport(125,0,125,125);
		glu.gluLookAt(5,2,3,0,0,0,1,1,0);
		cube.draw();
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glViewport(0,125,125,125);
		gl.glTranslated(3,-4,2);
		gl.glRotated(32,15,27,60);
		glu.gluLookAt(0,0,0,3,-4,2,1,0,0);
		cube.draw();
		
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
}
