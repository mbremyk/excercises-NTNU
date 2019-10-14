package Fifteen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Draw
{
	private static GL2 gl;
	private GLUT glut;
	
	private static final int[][] cubeSides = {
			{0, 1, 2, 3},              //    0 - 1
			{1, 6, 7, 2},              //0 - 3 - 2 - 1
			{6, 4, 5, 7},              //4 - 5 - 7 - 6
			{4, 0, 3, 5},              //    4 - 6
			{3, 2, 7, 5},              //    0 - 1
			{1, 0, 4, 6}               //
	};
	
	public Draw()
	{
		this.gl = Main.gl;
		this.glut = Main.glut;
	}
	
	public static void setColour(double r, double g, double b) { gl.glColor3d(r, g, b); }
	public static void setColour(double[] colour)
	{
		gl.glColor3dv(colour, 0);
	}
	
	public static void drawSide(double[][] cornerCoordinates, int[] corners, double[] colour)
	{
		setColour(colour[0], colour[1], colour[2]);
		gl.glBegin(GL2.GL_POLYGON);
		for (int i : corners)
		{
			gl.glVertex3dv(cornerCoordinates[i], 0);
		}
		gl.glEnd();
	}
	
	public static void drawCube(double[][] cornerCoordinates, double[][] colours)
	{
		for (int i = 0; i < cubeSides.length; i++)
		{
			drawSide(cornerCoordinates, cubeSides[i], colours[i]);
		}
		gl.glFlush();
	}
}
