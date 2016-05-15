package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class BlueInvert extends ImageDecorator {

	public BlueInvert(BufferedImage image) {
		super(image);
	}

	public BufferedImage change() {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		short[] invert = new short[256];
		short[] straight = new short[256];
		for (int i = 0; i < 256; i++) {
			invert[i] = (short) (255 - i);
			straight[i] = (short) i;
		}
		short[][] blueInvert = new short[][] { straight, straight, invert };
		BufferedImageOp blueInvertOp = new LookupOp(new ShortLookupTable(0, blueInvert), null);
		blueInvertOp.filter(image, result);
		return result;
	}

	@Override
	public BufferedImage change(float incVal) {
		// TODO Auto-generated method stub
		return null;
	}

}
