package lab2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Skeleton extends JPanel implements ActionListener {
	private static int width = 500;
	private static int height = 800;

	private Graphics2D g2d;
	private Timer timer;

	private double[][] myPolygonPoints = { { 250, 400 }, { 300, 425 }, { 300, 475 }, { 250, 500 }, { 200, 475 },
			{ 200, 425 }, { 250, 400 } };
	private GeneralPath myPolygon;

	private double scale = 1;
	private double delta = 0.01;
	
	private double ovX = 100;
	private double ovY = 380;
	private double direction = 1;
	
	public Skeleton() {
		myPolygon = polygonFromPoints(myPolygonPoints);
		timer = new Timer(10, this);
		timer.start();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Lab2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(new Skeleton());
		frame.setVisible(true);

		Dimension size = frame.getSize();
		Insets insets = frame.getInsets();
		width = size.width - insets.left - insets.right - 1;
		height = size.height - insets.top - insets.bottom - 1;
	}

	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		setRenderHints();
		g2d.setBackground(new Color(123, 54, 242));
		g2d.clearRect(0, 0, width, height);
		drawOriginal();
		drawFrame();
        drawAnimation();
	}

	private void drawFrame() {
		BasicStroke stroke = new BasicStroke(25, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(stroke);
		g2d.setColor(Color.CYAN);
		g2d.drawRect(60, 350, 370, 340);
	}

	public void actionPerformed(ActionEvent e) {
		if (scale < 0.01) {
			delta = -delta;
		} else if (scale > 0.99) {
			delta = -delta;
		}
		scale += delta;
		
		if(direction == 1)
			ovY++;
		if(direction == 2)
			ovX++;
		if(direction == 3)
			ovY--;
		if(direction == 4)
			ovX--;
		
		if(ovX == 100 && ovY == 380)
			direction = 1;
		if(ovX == 100 && ovY == 580)
			direction = 2;
		if(ovX == 320 && ovY == 580)
			direction = 3;
		if(ovX == 320 && ovY == 380)
			direction = 4;
		
		repaint();
	}

	private void setRenderHints() {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}

	private GeneralPath polygonFromPoints(double[][] points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points[0][0], points[0][1]);
		for (int i = 1; i < points.length; i++)
			path.lineTo(points[i][0], points[i][1]);
		path.closePath();
		return path;
	}
	private void drawAnimation() {
		BasicStroke bs = new BasicStroke(8, BasicStroke.CAP_BUTT,
				 BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs);
		g2d.setColor(Color.PINK);
		g2d.drawOval((int)(ovX), (int)(ovY), 80, 80);

		
		double zoomWidth = width * scale;
        double zoomHeight = height * scale;
        double anchorx = (width - zoomWidth) / 1.9;
        double anchory = (height - zoomHeight) / 1.5;
        g2d.translate(anchorx, anchory);
		g2d.scale(scale, scale);
		g2d.translate(0, 50);
		g2d.setColor(Color.GREEN);
		g2d.fill(myPolygon);
	}

	private void drawOriginal() {
		g2d.setColor(new Color(128, 0, 0));
		g2d.fillRect(75, 10, 350, 200);

		g2d.setColor(Color.YELLOW);
		BasicStroke bs1 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.drawLine(75, 70, 425, 70);
		g2d.drawLine(75, 145, 425, 145);
		g2d.drawLine(75, 145, 425, 145);
		g2d.drawLine(250, 10, 250, 70);
		g2d.drawLine(250, 145, 250, 210);
		g2d.drawLine(170, 70, 170, 140);
		g2d.drawLine(340, 70, 340, 140);
	}
}