package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Emboss extends ImageDecorator {

	public Emboss(BufferedImage image) {
		super(image);
	}

	public BufferedImage change() {
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		Kernel kernel = KernelFactory.createEmbossKernel();
		ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		convolveOp.filter(image, result);
		return result;
	}

	@Override
	public BufferedImage change(float incVal) {
		// TODO Auto-generated method stub
		return null;
	}

}
