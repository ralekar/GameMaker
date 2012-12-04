package imagewizard;

import java.io.File;

import javax.swing.JLabel;

import jwizardcomponent.JWizardComponents;

class LastWizardPanel extends LabelWizardPanel {
	private static final long serialVersionUID = 1L;

	private JLabel fileInfo = new JLabel();
	private JLabel tagsInfo = new JLabel();

	public LastWizardPanel(JWizardComponents wizardComponents) {
		super(wizardComponents, "Info:");
		fileInfo.setText("File info ");
		tagsInfo.setText("Tag info");
		this.add(fileInfo);
		this.add(tagsInfo);

	}

	public void update() {
		setNextButtonEnabled(false);
		setFinishButtonEnabled(true);
		setBackButtonEnabled(true);
	}

	public void updateImagePreview(File file) {
		fileInfo.setText(file.getName());
	}

	public void next() {
	}

	@Override
	public void back() {
		switchPanel(Wizard.PANEL_TAG);
	}

}