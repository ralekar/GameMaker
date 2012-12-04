package imagewizard;

import java.io.File;

import jwizardcomponent.Utilities;
import jwizardcomponent.JWizardPanel;
import jwizardcomponent.frame.JWizardFrame;

public class Wizard extends JWizardFrame {
	private static final long serialVersionUID = 1L;

	public static final int PANEL_CHOOSER = 0;
	public static final int PANEL_TAG = 1;
	public static final int PANEL_LAST = 2;
	public final int WWIDTH = 500;
	public final int WHEIGTH = 300;
	// keep reference to chooserWizardPanel in order to obtain an image/sound
	private ChooserWizardPanel chooserWizardPanel;
	// keep reference to lastWizardPanel in order to obtain tags
	private TagWizardPanel tagWizardPanel;
	private LastWizardPanel lastWizardPanel;
	private File file;
	private String tags;

	public Wizard() {
		super();
		init();
	}

	private void init() {
		setSize(WWIDTH, WHEIGTH);
		this.setTitle("Image/Sound Import Wizard");
		chooserWizardPanel = new ChooserWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(PANEL_CHOOSER,
				getChooserWizardPanel());

		tagWizardPanel = new TagWizardPanel(getWizardComponents());
		lastWizardPanel = new LastWizardPanel(getWizardComponents());

		tagWizardPanel.setNextStep(lastWizardPanel);
		tagWizardPanel.setPreviousStep(chooserWizardPanel);

		setTagWizardPanel(tagWizardPanel);
		getWizardComponents().addWizardPanel(PANEL_TAG, getTagWizardPanel());
		setLastWizardPanel(lastWizardPanel);
		getWizardComponents().addWizardPanel(PANEL_LAST, getLastWizardPanel());
		Utilities.centerComponentOnScreen(this);

	}

	public ImageData getImageData() {
		File file = chooserWizardPanel.getFile();
		String tags = tagWizardPanel.getTags();
		ImageData imageData = new ImageData();
		imageData.setFile(file);
		imageData.setTags(tags);
		return imageData;
	}

	public JWizardPanel getChooserWizardPanel() {
		return chooserWizardPanel;
	}

	public void setChooserWizardPanel(ChooserWizardPanel chooserWizardPanel) {
		this.chooserWizardPanel = chooserWizardPanel;
	}

	public JWizardPanel getTagWizardPanel() {
		return tagWizardPanel;
	}

	public void setTagWizardPanel(TagWizardPanel tagWizardPanel) {
		this.tagWizardPanel = tagWizardPanel;
	}

	public JWizardPanel getLastWizardPanel() {
		return lastWizardPanel;
	}

	public void setLastWizardPanel(LastWizardPanel lastWizardPanel) {
		this.lastWizardPanel = lastWizardPanel;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
