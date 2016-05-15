package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class Poster extends ImageDecorator {

	public Poster(BufferedImage image) {
		super(image);
	}

	public BufferedImage change() {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		short[] posterize = new short[256];
		for (int i = 0; i < 256; i++)
			posterize[i] = (short) (i - (i % 32));
		BufferedImageOp posterizeOp = new LookupOp(new ShortLookupTable(0, posterize), null);
		posterizeOp.filter(image, result);
		return result;
	}

	@Override
	public BufferedImage change(float incVal) {
		// TODO Auto-generated method stub
		return null;
	}

}
