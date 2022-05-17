package gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import view.MainView;

public class GameWindow extends JInternalFrame
{
    //private final GameVisualizer m_visualizer;
	private final MainView view;
    public GameWindow() 
    {
        super("игровое поле", true, true, true, true);
        //m_visualizer = new GameVisualizer();
        view = new MainView();
        JPanel panel = new JPanel(new BorderLayout());
        //panel.add(m_visualizer, BorderLayout.CENTER);
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
