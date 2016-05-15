package decorator;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;

public class ImageFilterFrameView extends JFrame {

	private static final long serialVersionUID = 1L;
	static final double DEFAULT_RATIO = 4d / 3d;
	static final double HD_RATIO = 16d / 9d;

	static final int DEFAULT_WIDTH = 1200;
	static final int DEFAULT_HEIGHT = 900;
	static final int HD_WIDTH = 1600;
	static final int HD_HEIGHT = 900;
	static final int SIDE_WIDTH = 200;

	private ImageFilterController controller = null;
	private ImageFilterModel model = null;
	private ImageFilterFrameView thisClass = this;
	private DrawPanelView drawPanelView = null;

	private JFileChooser jFileChooser1 = null;
	private JFileChooser jFileChooser2 = null;
	private String defaultDir = "C:/home/cskim/temp/";

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem jmiOpen;
	private JPanel statusPanel;
	private BufferedImage image;

	ImageDecorator ImageDecorator;
	private JButton btnBlur;
	private JButton btnSharpen;
	private JButton btnEdge_Detect;
	private JButton btnEmboss;
	private JButton btnInvert;
	private JButton btnBlueInvert;
	private JButton btnPoster;
	private JButton btnHueMinus;
	private JButton btnBrightMinus;
	private JButton btnSaturationMinus;
	private JButton btnHuePlus;
	private JButton btnBrightPlus;
	private JButton btnSaturationPlus;

	/**
	 * Create the frame.
	 */
	public ImageFilterFrameView(ImageFilterController controller, ImageFilterModel model) {
		super();
		this.controller = controller;
		this.model = model;
		this.model.setFrameView(this);

		initialize();
	}

	private void initialize() {

		this.setTitle("Image Filter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 50, DEFAULT_WIDTH, DEFAULT_HEIGHT);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File ");
		menuBar.add(mnFile);

		jmiOpen = new JMenuItem("Open ");
		jmiOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenAction();
			}
		});
		mnFile.add(jmiOpen);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jFileChooser1 = new JFileChooser(defaultDir);
		jFileChooser1.setDialogTitle("Open Image File");
		jFileChooser1.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "JPG", "jpeg", "png", "PNG"));

		jFileChooser2 = new JFileChooser(defaultDir);
		jFileChooser2.setDialogType(JFileChooser.SAVE_DIALOG);
		jFileChooser2.setDialogTitle("Save As ...");

		drawPanelView = new DrawPanelView();
		contentPane.add(drawPanelView, BorderLayout.CENTER);
		controller.setDrawPanelView(drawPanelView);

		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(300, 500));
		contentPane.add(statusPanel, BorderLayout.EAST);
		statusPanel.setLayout(new GridLayout(10, 1, 5, 5));
		
		JPanel statusPanel1 = new JPanel();
		statusPanel1.setLayout(new GridLayout(1, 2, 5, 5));
		JPanel statusPanel1_1 = new JPanel();
		JPanel statusPanel1_2 = new JPanel();
		statusPanel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		statusPanel1.add(statusPanel1_1);
		statusPanel1_1.setLayout(new BorderLayout(0, 0));
		
		btnHueMinus = new JButton("Hue -");
		btnHueMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ImageDecorator = new Hue(model.getMainImage());
				model.setMainImage(ImageDecorator.change(-0.1f));
				drawPanelView.repaint();	
			}
		});
		btnHueMinus.setForeground(new Color(51, 0, 102));
		btnHueMinus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel1_1.add(btnHueMinus, BorderLayout.CENTER);
		statusPanel1.add(statusPanel1_2);
		statusPanel1_2.setLayout(new BorderLayout(0, 0));
		
		btnHuePlus = new JButton("Hue +");
		btnHuePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ImageDecorator = new Hue(model.getMainImage());
				model.setMainImage(ImageDecorator.change(0.1f));
				drawPanelView.repaint();	
			}
		});
		btnHuePlus.setForeground(new Color(51, 0, 102));
		btnHuePlus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel1_2.add(btnHuePlus, BorderLayout.CENTER);
		
		JPanel statusPanel2 = new JPanel();
		statusPanel2.setLayout(new GridLayout(1, 2, 5, 5));
		JPanel statusPanel2_1 = new JPanel();
		JPanel statusPanel2_2 = new JPanel();
		statusPanel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		statusPanel2.add(statusPanel2_1);	
		statusPanel2_1.setLayout(new BorderLayout(0, 0));
		btnBrightMinus = new JButton("Bright -");
		btnBrightMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Brightness(model.getMainImage());
				model.setMainImage(ImageDecorator.change(-0.1f));
				drawPanelView.repaint();
			}
		});
		btnBrightMinus.setForeground(new Color(102, 51, 153));
		btnBrightMinus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel2_1.add(btnBrightMinus, BorderLayout.CENTER);
		
		statusPanel2.add(statusPanel2_2);
		statusPanel2_2.setLayout(new BorderLayout(0, 0));
		btnBrightPlus = new JButton("Bright +");
		btnBrightPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Brightness(model.getMainImage());
				model.setMainImage(ImageDecorator.change(0.1f));
				drawPanelView.repaint();
			}
		});
		btnBrightPlus.setForeground(new Color(102, 51, 153));
		btnBrightPlus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel2_2.add(btnBrightPlus, BorderLayout.CENTER);
		
		JPanel statusPanel3 = new JPanel();
		statusPanel3.setLayout(new GridLayout(1, 2, 5, 5));
		JPanel statusPanel3_1 = new JPanel();
		JPanel statusPanel3_2 = new JPanel();
		statusPanel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		statusPanel3.add(statusPanel3_1);	
		statusPanel3_1.setLayout(new BorderLayout(0, 0));
		btnSaturationMinus = new JButton("Saturation -");
		btnSaturationMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Saturation(model.getMainImage());
				model.setMainImage(ImageDecorator.change(-0.1f));
				drawPanelView.repaint();
			}
		});
		btnSaturationMinus.setForeground(new Color(102, 0, 204));
		btnSaturationMinus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel3_1.add(btnSaturationMinus, BorderLayout.CENTER);
		
		statusPanel3.add(statusPanel3_2);
		statusPanel3_2.setLayout(new BorderLayout(0, 0));
		btnSaturationPlus = new JButton("Saturation +");
		btnSaturationPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Saturation(model.getMainImage());
				model.setMainImage(ImageDecorator.change(0.1f));
				drawPanelView.repaint();
			}
		});
		btnSaturationPlus.setForeground(new Color(102, 0, 204));
		btnSaturationPlus.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel3_2.add(btnSaturationPlus, BorderLayout.CENTER);
		JPanel statusPanel7 = new JPanel();
		JPanel statusPanel8 = new JPanel();
		JPanel statusPanel9 = new JPanel();
		JPanel statusPanel10 = new JPanel();
		JPanel statusPanel11 = new JPanel();
		JPanel statusPanel12 = new JPanel();
		JPanel statusPanel13 = new JPanel();
		statusPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		statusPanel.add(statusPanel1);
		statusPanel1.setLayout(new GridLayout(1, 0, 0, 0));
		statusPanel.add(statusPanel2);
		statusPanel2.setLayout(new GridLayout(1, 0, 0, 0));
		statusPanel.add(statusPanel3);
		statusPanel3.setLayout(new GridLayout(1, 0, 0, 0));
		statusPanel.add(statusPanel7);
		statusPanel7.setLayout(new BorderLayout(0, 0));

		btnBlur = new JButton("Blur");
		btnBlur.setForeground(new Color(153, 51, 255));
		btnBlur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Blur(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnBlur.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel7.add(btnBlur, BorderLayout.CENTER);
		statusPanel.add(statusPanel8);
		statusPanel8.setLayout(new BorderLayout(0, 0));

		btnSharpen = new JButton("Sharpen");
		btnSharpen.setForeground(new Color(153, 51, 255));
		btnSharpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Sharpen(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnSharpen.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel8.add(btnSharpen, BorderLayout.CENTER);
		statusPanel.add(statusPanel9);
		statusPanel9.setLayout(new BorderLayout(0, 0));

		btnEdge_Detect = new JButton("Edge Detect");
		btnEdge_Detect.setForeground(new Color(204, 102, 255));
		btnEdge_Detect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new EdgeEffect(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnEdge_Detect.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel9.add(btnEdge_Detect, BorderLayout.CENTER);
		statusPanel.add(statusPanel10);
		statusPanel10.setLayout(new BorderLayout(0, 0));

		btnEmboss = new JButton("Emboss");
		btnEmboss.setForeground(new Color(204, 102, 255));
		btnEmboss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Emboss(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnEmboss.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel10.add(btnEmboss, BorderLayout.CENTER);
		
		statusPanel.add(statusPanel11);
		statusPanel11.setLayout(new BorderLayout(0, 0));
		
		btnInvert = new JButton("Invert");
		btnInvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Invert(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnInvert.setForeground(new Color(204, 153, 255));
		btnInvert.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel11.add(btnInvert, BorderLayout.CENTER);
		statusPanel.add(statusPanel12);
		statusPanel12.setLayout(new BorderLayout(0, 0));
		
		btnBlueInvert = new JButton("BlueInvert");
		btnBlueInvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new BlueInvert(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnBlueInvert.setForeground(new Color(204, 153, 255));
		btnBlueInvert.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel12.add(btnBlueInvert, BorderLayout.CENTER);
		statusPanel.add(statusPanel13);
		statusPanel13.setLayout(new BorderLayout(0, 0));
		
		btnPoster = new JButton("Poster");
		btnPoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageDecorator = new Poster(model.getMainImage());
				model.setMainImage(ImageDecorator.change());
				drawPanelView.repaint();
			}
		});
		btnPoster.setForeground(new Color(153, 153, 255));
		btnPoster.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 23));
		statusPanel13.add(btnPoster, BorderLayout.CENTER);

	}

	/**
	 * 
	 */
	protected void OpenAction() {
		if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selFile = jFileChooser1.getSelectedFile();
			model.setSaveFile(selFile);
			controller.loadNDrawImageFile();
			int imgWidth = model.getMainImage().getWidth();
			int imgHeight = model.getMainImage().getHeight();
			double imgRatio = (double) imgWidth / imgHeight;
			if (Math.abs(imgRatio - DEFAULT_RATIO) < Math.abs(imgRatio - HD_RATIO)) {
				this.setBounds(100, 50, DEFAULT_WIDTH + SIDE_WIDTH, DEFAULT_HEIGHT);
			} else {
				this.setBounds(100, 50, HD_WIDTH + SIDE_WIDTH, HD_HEIGHT);
			}
			drawPanelView.repaint();
		}

	}

}
