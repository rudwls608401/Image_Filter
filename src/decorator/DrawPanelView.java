package decorator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawPanelView extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageFilterModel model = null;

	public DrawPanelView() {
		this.model = ImageFilterModel.getInstance();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int viewWidth = this.getWidth();
		int viewHeight = this.getHeight();
		if (model.getMainImage() != null) {
			BufferedImage viewImage = ResizeImage.resizeImageWithHint(model.getMainImage(), viewWidth, viewHeight,
					model.getMainImage().getType());

			g2.drawImage(viewImage, 0, 0, null);
		}
	}

}
