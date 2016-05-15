package decorator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public abstract class ImageDecorator {

	protected BufferedImage image;

	public ImageDecorator(BufferedImage image) {
		this.image = image;
	}

	static public float[] getHSBFromRGB(Raster raster, int x, int y) {
		int[] rgbColor = raster.getPixel(x, y, new int[3]);
		return Color.RGBtoHSB(rgbColor[0], rgbColor[1], rgbColor[2], null);
	}

	static public float getHueFromRGB(Raster raster, int x, int y) {
		return getHSBFromRGB(raster, x, y)[0];
	}

	static public float getSaturationFromRGB(Raster raster, int x, int y) {
		return getHSBFromRGB(raster, x, y)[1];
	}

	static public float getBrightnessFromRGB(Raster raster, int x, int y) {
		return getHSBFromRGB(raster, x, y)[2];
	}

	static public float[] maxHSB(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();

		Raster raster = image.getData();
		float[] maxVal = { Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY };
		for (int y = 0; y < imgHeight; ++y) {
			for (int x = 0; x < imgWidth; ++x) {
				maxVal[0] = Math.max(maxVal[0], getHueFromRGB(raster, x, y));
				maxVal[1] = Math.max(maxVal[1], getSaturationFromRGB(raster, x, y));
				maxVal[2] = Math.max(maxVal[2], getBrightnessFromRGB(raster, x, y));
			}
		}
		return maxVal;
	}

	static public float[] minHSB(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();

		Raster raster = image.getData();
		float[] minVal = { Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY };
		for (int y = 0; y < imgHeight; ++y) {
			for (int x = 0; x < imgWidth; ++x) {
				minVal[0] = Math.min(minVal[0], getHueFromRGB(raster, x, y));
				minVal[1] = Math.min(minVal[1], getSaturationFromRGB(raster, x, y));
				minVal[2] = Math.min(minVal[2], getBrightnessFromRGB(raster, x, y));
			}
		}
		return minVal;
	}

	static public int[] getRGBFromHSB(float[] hsb) {
		int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		Color color = new Color(rgb);
		int[] rgbColor = new int[3];
		rgbColor[0] = color.getRed();
		rgbColor[1] = color.getGreen();
		rgbColor[2] = color.getBlue();

		return rgbColor;
	}

	public abstract BufferedImage change(float incVal);// 추상메소드로 만들어서 subclass에서
														// 구현을 하게 한다.

	public abstract BufferedImage change();// 추상메소드로 만들어서 subclass에서 구현을 하게 한다.

}
