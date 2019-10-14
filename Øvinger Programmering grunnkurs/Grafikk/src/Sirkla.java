import Twelve.Oppgave4;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Sirkla extends GLCanvas implements GLEventListener
{
	private GLU glu;
	
	private Circle[] circles = new Circle[10];
	
	public Sirkla()
	{
		this.addGLEventListener(this);
	}
	
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		gl.glTranslatef(-1.5f, 0.0f, -4.0f);
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL_POLYGON);
		gl.glVertex3f(0.25f, 0.25f, 0.0f);
		gl.glVertex3f(0.75f, 0.25f, 0.0f);
		gl.glVertex3f(0.75f, 0.75f, 0.0f);
		gl.glVertex3f(0.75f, 0.75f, 0.0f);
		gl.glEnd();
		
		gl.glFlush();
	}
	
	public void init(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f);
	}
	
	public void dispose(GLAutoDrawable drawable) { }
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		
		if (height == 0) height = 1;   // prevent divide by zero
		float aspect = (float)width / height;
		
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
		GLCanvas canvas = new Sirkla();
		canvas.setPreferredSize(new Dimension(250, 250));
		
		final JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
