package Thirteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Oppgave3 extends GLCanvas implements GLEventListener
{
	private GLU glu;
	private GL2 gl;
	private GLUT glut;
	
	public Oppgave3()
	{
		this.addGLEventListener(this);
	}
	
	public void display(GLAutoDrawable drawable)
	{
		//gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		glu.gluLookAt(0f,0f,0f, 1f,0f,-4f,1f, 1f,0f);
		
		gl.glPushMatrix();
		//Flytte canvas unna kameraet
		gl.glTranslatef(0.0f, 0.0f, -4f);
		gl.glClear(GL_COLOR_BUFFER_BIT);
		//Farge hvit
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		//gl.glOrtho(0, 0, 0, 0, 0, 0);
		
		glut.glutWireCube(.5f);
		
		gl.glTranslated(1,1,1);
		gl.glRotated(90, 1, 0, 0);
		gl.glScaled(0.5, 0.2, 1.4);
		
		glut.glutWireCube(1f);
		
		gl.glPopMatrix();
		
		gl.glTranslatef(0.0f, 0.0f, -4f);
		gl.glScaled(0.5, 0.2, 1.4);
		gl.glRotated(90, 1, 0, 0);
		gl.glTranslated(1,1,1);
		
		glut.glutWireCube(1f);
		
		
		
		gl.glFlush();
	}
	
	public void init(GLAutoDrawable drawable)
	{
		gl = drawable.getGL().getGL2();
		glu = new GLU();
		glut = new GLUT();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		//gl.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f);
	}
	
	public void dispose(GLAutoDrawable drawable) { }
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		//gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		
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
		GLCanvas canvas = new Oppgave3();
		canvas.setPreferredSize(new Dimension(250, 250));
		
		final JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
