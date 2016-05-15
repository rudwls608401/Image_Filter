package decorator;

import java.awt.EventQueue;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFilterController {

	private ImageFilterModel model = ImageFilterModel.getInstance();
	private ImageFilterFrameView frameView = null;
	private DrawPanelView drawPanelView = null;

	public ImageFilterController() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createFrameView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void createFrameView() {
		frameView = new ImageFilterFrameView(this, model);
		frameView.setVisible(true);
		model.setController(this);

	}

	/**
	 * 
	 */
	public void loadNDrawImageFile() {

		try {
			model.setMainImage(ImageIO.read(model.getSaveFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the drawPanelView
	 */
	public DrawPanelView getDrawPanelView() {
		return drawPanelView;
	}

	/**
	 * @param drawPanelView
	 *            the drawPanelView to set
	 */
	public void setDrawPanelView(DrawPanelView drawPanelView) {
		this.drawPanelView = drawPanelView;
	}

}
