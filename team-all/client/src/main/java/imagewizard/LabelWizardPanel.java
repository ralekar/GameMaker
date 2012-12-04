package imagewizard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;

public class LabelWizardPanel extends JWizardPanel {
	private static final long serialVersionUID = 1L;

	public LabelWizardPanel(JWizardComponents wizardComponents, String label) {
		super(wizardComponents);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(label), new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
	}

}
