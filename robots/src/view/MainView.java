package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import presenter.MainPresenter;

public class MainView extends JPanel{
	
	MainPresenter presenter;
	
	private volatile double m_robotPositionX;
    private volatile double m_robotPositionY;
    private volatile double m_robotDirection;
    
    private volatile int m_targetPositionX;
    private volatile int m_targetPositionY;
	
    
    private final Timer m_timer = initTimer();
    
	public MainView() 
	{
		
		presenter = new MainPresenter(this);
		
		presenter.getTargetPosition();
		m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
            	presenter.getPointRobot();
            	presenter.getDirectionRobot();
                onRedrawEvent();
            }
        }, 0, 50);
		addMouseListener(new MouseAdapter()
	    {
	        @Override
	        public void mouseClicked(MouseEvent e)
	        {
	            presenter.mouseClicked(e.getPoint());
	            repaint();
	        }
	    });
		setDoubleBuffered(true);
	}
	
    private static Timer initTimer() 
    {
        Timer timer = new Timer("events generator", true);
        return timer;
    }
	
	public void setTargetPosition(Point p)
    {
        m_targetPositionX = p.x;
        m_targetPositionY = p.y;
    }
	
	public void setPointRobot(Point p)
    {
		m_robotPositionX = p.x;
		m_robotPositionY = p.y;
    }
	
	public void setDirectionRobot(Double direction)
	{
		m_robotDirection = direction;
	}
	
	protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
	
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g; 
        drawRobot(g2d, round(m_robotPositionX), round(m_robotPositionY), m_robotDirection);
        drawTarget(g2d, m_targetPositionX, m_targetPositionY);
    }
    
    private static int round(double value)
    {
        return (int)(value + 0.5);
    }
    
    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    
    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
    
    private void drawRobot(Graphics2D g, int x, int y, double direction)
    {
        int robotCenterX = x; 
        int robotCenterY = y;
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY); 
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }
    
    private void drawTarget(Graphics2D g, int x, int y)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0); 
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}
