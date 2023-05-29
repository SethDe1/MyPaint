package myPaint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PaintGUI {
	private int x =1500, y=900;
	private JButton eraser, white, yellow, orange, black, blue, green, red, magenta, resize;
	private PaintControl canvas;
	private JRadioButton AAButton;
	private JFrame mainPanel;
	private Container content;
	private JPanel display, optionsBar;
	//creating menu
	private JMenuBar menuBarTop;
	//Creating menu bar items
	private JMenu menuFile, menuInset;
	//creating File options menu
	private JMenuItem newOption, save, image;
	ActionListener ae = new ActionListener() {


		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == white) {
				canvas.white();
			}
			else if (e.getSource() == yellow) {
				canvas.yellow();
			}
			else if (e.getSource() == orange) {
				canvas.orange();
			}
			else if (e.getSource() == black) {
				canvas.black();
			} 
			else if (e.getSource() == blue) {
				canvas.blue();
			} 
			else if (e.getSource() == green) {
				canvas.green();
			} 
			else if (e.getSource() == red) {
				canvas.red();
			} 
			else if (e.getSource() == magenta) {
				canvas.magenta();
			}
			else if (e.getSource() == newOption)
			{
				canvas.clear();
			}
			else if (e.getSource() == save)
			{
				BufferedImage im = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
				canvas.paint(im.getGraphics());
				String fn = JOptionPane.showInputDialog("Enter file name");
				if (fn !=null) {
					try {
						ImageIO.write(im, "PNG", new File("pictures/"+fn +".png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else if (e.getSource() == image)
			{
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					//This is where a real application would open the file.
					System.out.println("Opening: " + file.getName() + ".");
					try {                
						BufferedImage pic = ImageIO.read(file.getAbsoluteFile());
						canvas.setImage(pic);
						canvas.repaint();
					} catch (IOException ex) {
						// handle exception...
					}
				} else {
					System.out.println("import cancelled");
				}

			}
			else if (e.getSource() == eraser)
			{
				canvas.white();
			}
			else if (e.getSource() == AAButton)
			{
				canvas.setAntiAliasing(AAButton.isSelected());
			}
			else if (e.getSource() == resize)
			{
				String coords =(String)JOptionPane.showInputDialog("Enter X,Y");
				String[] yo = coords.split(",");
				int xVal = Integer.parseInt(yo[0]);
				int yVal = Integer.parseInt(yo[1]);
				restart(xVal, yVal);
			}
		}
	};

	public static void main(String[] args) {
		new PaintGUI();
	}

	public PaintGUI() {
		AAButton = new JRadioButton("Toggle AntiAliasing");
		eraser = new JButton("Erase");
		resize = new JButton("Resize Canvas");
		optionsBar = new JPanel(new GridLayout(1,7));
		JPanel toolBar = new JPanel();

		// create main frame
		mainPanel = new JFrame("MyPaint");
		content = mainPanel.getContentPane();
		// set layout on content pane
		content.setLayout(new BorderLayout());
		display = new JPanel(new BorderLayout());
		//creating menu
		menuBarTop = new JMenuBar();
		//Creating menu bar items
		menuFile = new JMenu("File");
		menuInset = new JMenu("Insert");

		//creating File options menu
		newOption = new JMenuItem("New");
		save = new JMenuItem("Save Image");

		//creating insert options menu
		image = new JMenuItem("Image");


		// create draw area
		canvas = new PaintControl();

		//adding option buttons
		white = new JButton("White");
		yellow = new JButton("Yellow");
		orange = new JButton("Orange");
		black = new JButton("Black");
		blue = new JButton("Blue");
		green = new JButton("Green");
		red = new JButton("Red");
		magenta = new JButton("Magenta");

		//adding action listeners
		black.addActionListener(ae);
		yellow.addActionListener(ae);
		orange.addActionListener(ae);
		blue.addActionListener(ae);
		green.addActionListener(ae);
		red.addActionListener(ae);
		magenta.addActionListener(ae);
		newOption.addActionListener(ae);
		save.addActionListener(ae);
		image.addActionListener(ae);
		eraser.addActionListener(ae);
		AAButton.addActionListener(ae);
		resize.addActionListener(ae);


		//adding buttons to option bar
		optionsBar.add(black);
		//optionsBar.add(white);
		optionsBar.add(yellow);
		optionsBar.add(orange);
		optionsBar.add(blue);
		optionsBar.add(green);
		optionsBar.add(red);
		optionsBar.add(magenta);
		


		//adding marker options
	
		toolBar.add(optionsBar);
		toolBar.add(eraser);
		toolBar.add(AAButton);
		//toolBar.add(resize);


		//adding to menus
		menuFile.add(newOption);
		menuFile.add(save);
		menuInset.add(image);

		//adding options to top menuBars
		menuBarTop.add(menuFile);
		menuBarTop.add(menuInset);

		// add to content pane
		display.add(toolBar,BorderLayout.NORTH);
		display.add(canvas,BorderLayout.CENTER);
		content.add(menuBarTop,BorderLayout.NORTH);
		content.add(display,BorderLayout.CENTER);

		//setting mainFrame options
		mainPanel.setVisible(true);
		mainPanel.setSize(x, y);
		mainPanel.setResizable(false);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void restart(int x, int y)
	{
		mainPanel.dispose();
		mainPanel.setSize(x, y);
		new PaintGUI();
		
	}
}
