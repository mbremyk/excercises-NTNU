package Thirteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.GL2.GL_QUAD_STRIP;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.GL2GL3.GL_POINT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Oppgave1 extends GLCanvas implements GLEventListener
{
	private GLU glu;
	private GL2 gl;
	
	private double[][] points = new double[][]{{0f, 2f, 0f}, {1.5f, 1.5f, 0f}, {2f, 0f, 0f}, {1.5f, -1.5f, 0f}, {0f, -2f, 0f}, {-1.5f, -1.5f, 0f}, {-2f, 0f, 0f}, {-1.5f, 1.5f, 0f}};
	
	public Oppgave1()
	{
		this.addGLEventListener(this);
	}
	
	public void display(GLAutoDrawable drawable)
	{
		//gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		//Flytte canvas unna kameraet
		gl.glTranslatef(0.0f, 0.0f, -5f);
		gl.glClear(GL_COLOR_BUFFER_BIT);
		//Farge hvit
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		//gl.glOrtho(0, 0, 0, 0, 0, 0);
		gl.glBegin(GL_POLYGON);
		{
			//Tegning her
			for (int i = 0; i < points.length; i++)
			{
				gl.glVertex3dv(points[i], 0);
			}
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_TRIANGLES);
		{
			gl.glColor3f(1f, 1f, 0f);
			gl.glVertex3dv(points[0], 0);
			gl.glVertex3dv(points[7], 0);
			gl.glVertex3dv(points[6], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_QUADS);
		{
			gl.glColor3f(1f, 0.3f, 0.2f);
			gl.glVertex3dv(points[1], 0);
			gl.glVertex3dv(points[2], 0);
			gl.glColor3f(0.1f, 0.7f, 0f);
			gl.glVertex3dv(points[5], 0);
			gl.glVertex3dv(points[4], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(3.3f, 0.0f, 0f);
		gl.glBegin(GL_TRIANGLE_FAN);
		{
			gl.glColor3f(0f, 1f, 0f);
			gl.glVertex3dv(points[0], 0);
			gl.glVertex3dv(points[6], 0);
			gl.glColor3f(1f, 0f, 0.5f);
			gl.glVertex3dv(points[5], 0);
			gl.glColor3f(0f, 0f, 1f);
			gl.glVertex3dv(points[2], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_TRIANGLE_STRIP);
		{
			gl.glColor3f(0f, 1f, 1f);
			gl.glVertex3dv(points[0], 0);
			gl.glVertex3dv(points[1], 0);
			gl.glVertex3dv(points[3], 0);
			gl.glColor3f(1f, 0f, 1f);
			gl.glVertex3dv(points[4], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_LINES);
		{
			gl.glColor3f(0f, 1f, 0f);
			gl.glVertex3dv(points[0], 0);
			gl.glVertex3dv(points[2], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_LINE_STRIP);
		{
			gl.glColor3f(1f, 0f, 0f);
			gl.glVertex3dv(points[1], 0);
			gl.glVertex3dv(points[3], 0);
			gl.glVertex3dv(points[5], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_LINE_LOOP);
		{
			gl.glColor3f(0f, 0f, 1f);
			gl.glVertex3dv(points[2], 0);
			gl.glVertex3dv(points[4], 0);
			gl.glVertex3dv(points[6], 0);
		}
		gl.glEnd();
		//gl.glTranslatef(1.3f, 0.0f, 0f);
		gl.glBegin(GL_QUAD_STRIP);
		{
			for (int i = 0; i < points.length; i += 2)
			{
				gl.glVertex3d(points[i][0] / 2, points[i][1] / 2, points[i][2] / 2);
			}
		}
		gl.glEnd();
		
		gl.glFlush();
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f);
	}
	
	public void dispose(GLAutoDrawable drawable)
	{
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		//gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		
		if (height == 0)
			height = 1;   // prevent divide by zero
		float aspect = (float) width / height;
		
		//Set the view port (display area) to cover the entire window
		//gl.glViewport(0, 0, width, height);
		
		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
		
		// Enable the model-view transform
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}
	
	public static void main(String[] args)
	{
		GLCanvas canvas = new Oppgave1();
		canvas.setPreferredSize(new Dimension(250, 250));
		
		final JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
