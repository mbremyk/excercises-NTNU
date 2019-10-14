import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Circle
{
	private final double xPos;
	private final double yPos;
	
	public Circle (double xPos, double yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void draw(GLAutoDrawable drawable, float rad, Boolean fill)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
	}
}
