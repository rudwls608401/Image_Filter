package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class Invert extends ImageDecorator {

	public Invert(BufferedImage image) {
		super(image);
	}

	public BufferedImage change() {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		short[] invert = new short[256];
		for (int i = 0; i < 256; i++)
			invert[i] = (short) (255 - i);
		BufferedImageOp invertOp = new LookupOp(new ShortLookupTable(0, invert), null);
		invertOp.filter(image, result);
		return result;
	}

	@Override
	public BufferedImage change(float incVal) {
		// TODO Auto-generated method stub
		return null;
	}

}
