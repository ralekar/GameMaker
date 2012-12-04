package imagewizard;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;

import javax.swing.JTextField;

import jwizardcomponent.JWizardComponents;

class TagWizardPanel extends LabelWizardPanel {
	private static final long serialVersionUID = 1L;

	private JTextField tags = new JTextField();
	ChooserWizardPanel previousStep;
	LastWizardPanel nextStep;

	public TagWizardPanel(JWizardComponents wizardComponents) {
		super(wizardComponents, "Please add some tags for the image/sound:");
		this.add(tags, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		setPanelTitle("Assign Tags to the Image");
	}

	public void update() {
		setNextButtonEnabled(true);
		setFinishButtonEnabled(false);
		setBackButtonEnabled(true);
	}

	public void next() {

		switchPanel(Wizard.PANEL_LAST);
		File file = previousStep.getFile();
		nextStep.updateImagePreview(file);

	}

	public void setPreviousStep(ChooserWizardPanel previousStep) {
		this.previousStep = previousStep;
	}

	public void setNextStep(LastWizardPanel nextStep) {
		this.nextStep = nextStep;
	}

	public void back() {
		switchPanel(Wizard.PANEL_CHOOSER);
	}

	public String getTags() {
		return tags.getText();
	}

	public void setTags(JTextField jTextField) {
		this.tags = jTextField;
	}
}