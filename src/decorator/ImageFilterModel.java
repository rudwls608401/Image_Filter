package decorator;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageFilterModel {

	private volatile static ImageFilterModel uniqueModelInstance;// singleton

	private ImageFilterFrameView frameView = null;
	private ImageFilterController controller = null;

	private File saveFile = null;

	private BufferedImage mainImage = null;

	private ImageFilterModel() {

	}

	public static ImageFilterModel getInstance() {// singleton
		if (uniqueModelInstance == null) {
			synchronized (ImageFilterModel.class) {
				if (uniqueModelInstance == null) {
					uniqueModelInstance = new ImageFilterModel();
				}
			}
		}
		return uniqueModelInstance;
	}

	/**
	 * @return the frameView
	 */
	public ImageFilterFrameView getFrameView() {
		return frameView;
	}

	/**
	 * @param frameView
	 *            the frameView to set
	 */
	public void setFrameView(ImageFilterFrameView frameView) {
		this.frameView = frameView;
	}

	/**
	 * @return the controller
	 */
	public ImageFilterController getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(ImageFilterController controller) {
		this.controller = controller;
	}

	/**
	 * @return the saveFile
	 */
	public File getSaveFile() {
		return saveFile;
	}

	/**
	 * @param saveFile
	 *            the saveFile to set
	 */
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}

	/**
	 * @return the mainImage
	 */
	public BufferedImage getMainImage() {
		return mainImage;
	}

	/**
	 * @param mainImage
	 *            the mainImage to set
	 */
	public void setMainImage(BufferedImage mainImage) {
		this.mainImage = mainImage;
	}

}
