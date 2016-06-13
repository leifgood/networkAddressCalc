import GUI.NetworkFrame;
import Model.Data;
import Presenter.NetworkPresenter;


public class Main {

	public static void main(String[] args) {
		Data data = new Data();
		NetworkPresenter presenter = new NetworkPresenter(data);
		NetworkFrame frame = new NetworkFrame(presenter);
		presenter.setFrame(frame);
		frame.setVisible(true);
	}

}
