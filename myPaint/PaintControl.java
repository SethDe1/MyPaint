package myPaint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PaintControl extends JComponent {

	// Image in which we're going to draw
	private Image image;
	private boolean value;
	// Graphics2D object ==> used to draw on
	private Graphics2D pen;
	// Mouse coordinates
	private int currentX, currentY, pastX, pastY;

	public PaintControl() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// save coord x,y when mouse is pressed
				pastX = e.getX();
				pastY = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// coord x,y when drag mouse
				currentX = e.getX();
				currentY = e.getY();

				if (pen != null) {
					// draw line if g2 context not null
					pen.fillOval(currentX,currentY,5, 5);

					// refresh draw area to repaint
					repaint();
					// store current coords x,y as olds x,y
					pastX = currentX;
					pastY = currentY;
					
				}
			}
		});
	}

	protected void paintComponent(Graphics g) {
		if (image == null) {
			// image to draw null ==> we create
			image = createImage(getSize().width, getSize().height);
			pen = (Graphics2D) image.getGraphics();
			// clear draw area
			clear();
		}
		// enable AntiAliasing based on button
			if (getAntiAliasing()==true)
				pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			else
				pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.drawImage(image, 0, 0, null);
	}

	//clearing screen
	public void clear() {
		pen.setPaint(Color.white);
		pen.fillRect(0, 0, getSize().width, getSize().height);
		pen.setPaint(Color.black);
		repaint();
	}
	//set pen white
	public void white() {
		// apply red color on g2 context
		pen.setPaint(Color.white);
	}
	//set pen yellow
	public void yellow() {
		// apply red color on g2 context
		pen.setPaint(Color.yellow);
	}
	//set pen orange
	public void orange() {
		// apply red color on g2 context
		pen.setPaint(Color.orange);
	}
	//set pen red
	public void red() {
		// apply red color on g2 context
		pen.setPaint(Color.red);
	}
	//set pen black
	public void black() {
		pen.setPaint(Color.black);
	}
	//set pen magenta
	public void magenta() {
		pen.setPaint(Color.magenta);
	}
	//set pen green
	public void green() {
		pen.setPaint(Color.green);
	}
	//set pen blue
	public void blue() {
		pen.setPaint(Color.blue);
	}
	//drawing selected image on canvas
	public void setImage(BufferedImage selectedImage) {
		// TODO Auto-generated method stub
		selectedImage.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
		pen.drawImage(selectedImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	//set size
	public boolean getAntiAliasing()
	{
		return value;

	}
	public void setAntiAliasing(boolean b)
	{
		value= b;
	}

}
