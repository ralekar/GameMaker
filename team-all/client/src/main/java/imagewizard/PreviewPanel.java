package imagewizard;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class PreviewPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image image = null;
	private int width, heigth;

	public Dimension getMinimumSize() {
		return new Dimension((int) (this.width * 0.7),
				(int) (this.heigth * 0.7));
	}

	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	PreviewPanel(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}