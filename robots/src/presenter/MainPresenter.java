package presenter;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.MainModel;
import view.MainView;

public class MainPresenter {
	private MainModel model;
	private MainView view;
	
	public MainPresenter(MainView view) {
		model = new MainModel();
		this.view = view;
		
	}
	
	public void mouseClicked(Point p) {
		model.setTargetPosition(p);
		view.setTargetPosition(model.getTargetPosition());
	}
	
	public void getTargetPosition() {
		view.setTargetPosition(model.getTargetPosition());
	}
	public void getPointRobot() {
		view.setPointRobot(model.getPointRobot());
	}
	public void getDirectionRobot() {
		view.setDirectionRobot(model.getDirectionRobot());
	}
	
}
