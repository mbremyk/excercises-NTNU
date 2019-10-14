import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.applet.*;

class Oppgave1 extends JApplet
{
	public static void main(String[] args) 
	{
		Window aWindow = new Window("Smiley");
		aWindow.setVisible(true);
	}

	public void init()
	{
		add(new Drawing());
	}
}

class Window extends JFrame
{
	public Window (String title)
	{
		setTitle(title);
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Drawing drawing = new Drawing();
		add(drawing);
	}
}

class Drawing extends JPanel
{
	public void paintComponent(Graphics pane)
	{
		final int HEAD = 39;
		final int BORDER = 8;

		super.paintComponent(pane);
		pane.setColor(Color.YELLOW);
		pane.fillOval(50 - BORDER, 50 - (HEAD - BORDER), 400, 400);

		pane.setColor(Color.BLACK);
		pane.drawArc(100 - BORDER, 100 - (HEAD - BORDER / 2), 300, 300, 180, 180);

		pane.fillOval(100 - BORDER, 100 - (HEAD - BORDER / 2), 50, 50);
		pane.fillOval(350 - BORDER, 100 - (HEAD - BORDER / 2), 50, 50);

		pane.drawString("MoM saYs i'M SPeCiaL", 50, 450);

		System.out.println(getWidth());
	}
}