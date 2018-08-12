package graphics;

import main.Renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.*;


public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage canvas;
	
    double actual_height;
    double actual_width;
    
    public int canvas_width;
    public int canvas_height;

    
    public Canvas(int width, int height, double actual_w, double actual_h) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        canvas_width  = width;
        canvas_height = height;
        
        actual_width  = actual_w;
        actual_height = actual_h;
    }

	public static Canvas getCanvas(String title){
		JFrame frame = new JFrame(title);
		Canvas canvas = new Canvas(Renderer.WIDTH, Renderer.HEIGHT, Renderer.REAL_WIDTH, Renderer.REAL_HEIGHT);

		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.fill(Color.BLACK);
		canvas.repaint();

		return canvas;
	}
    
    public void fill(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas_width; x++) {
            for (int y = 0; y < canvas_height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
    }

    public void setColor(int x, int y, Color c){
    		canvas.setRGB(x, y, c.getRGB());
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D g = (Graphics2D) graph;
        g.drawImage(canvas, null, null);
    }
    
}
