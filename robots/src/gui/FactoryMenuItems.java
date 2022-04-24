package gui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import log.Logger;

public class FactoryMenuItems {
	private MainApplicationFrame frame;
	
	public FactoryMenuItems(MainApplicationFrame frame) {
		this.frame = frame;
	}
	public JMenuItem addSystemTheme() {
		JMenuItem systemLookAndFeel = new JMenuItem("��������� �����", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            frame.invalidate();
        });
        return systemLookAndFeel;
	}
	
	public JMenuItem addUniversalTheme() {
		JMenuItem crossplatformLookAndFeel = new JMenuItem("������������� �����", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            frame.invalidate();
        });
        return crossplatformLookAndFeel;
	}
	
	public JMenuItem addLogMessageItem() {
		JMenuItem addLogMessageItem = new JMenuItem("��������� � ���", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("����� ������");
        });
        return addLogMessageItem;
	}
	private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
