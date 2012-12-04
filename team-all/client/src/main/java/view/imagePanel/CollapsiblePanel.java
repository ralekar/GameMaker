package view.imagePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CollapsiblePanel extends MouseAdapter {
    private ActionPanel[] aps;
    private JPanel[] panels;
    private JFrame baseFrame;

    public CollapsiblePanel(JFrame baseFrame) {
	assembleActionPanels();
	// assemblePanels();
	this.baseFrame = baseFrame;
    }

    public void mousePressed(MouseEvent e) {
	ActionPanel ap = (ActionPanel) e.getSource();
	if (ap.getTarget().contains(e.getPoint())) {
	    Boolean toggle = ap.toggleSelection();
	    // togglePanelVisibility(ap,"imagepanel");

	    if (toggle) {
		// togglePanelVisibility(ap,"controlpanel");
		togglePanelVisibility(ap, "imagepanel");
	    } else {
		// togglePanelVisibility(ap,"imagepanel");
		togglePanelVisibility(ap, "controlpanel");
	    }
	}
    }

    private void togglePanelVisibility(ActionPanel ap, String s) {
	int index = getPanelIndex(ap);
//	CardLayout cl = (CardLayout) (GameMakerView.getInstance().getSwitchPanel().getLayout());
//	cl.show(GameMakerView.getInstance().getSwitchPanel(), s);

    }

    private int getPanelIndex(ActionPanel ap) {
	for (int j = 0; j < aps.length; j++)
	    if (ap == aps[j])
		return j;
	return -1;
    }

    private void assembleActionPanels() {
	String[] ids = { "See all images" };
	aps = new ActionPanel[ids.length];
	for (int j = 0; j < aps.length; j++)
	    aps[j] = new ActionPanel(ids[j], this);
    }

    private void assemblePanels() {
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(2, 1, 2, 1);
	gbc.weightx = 1.0;
	gbc.weighty = 1.0;

	JPanel p = new JPanel(new GridBagLayout());
	addComponents(new JLabel("label 1"), new JTextField(12), p, gbc);
	addComponents(new JLabel("label 2"), new JTextField(16), p, gbc);
	gbc.gridwidth = 2;
	gbc.gridy = 2;
	p.add(new JSlider(), gbc);
	gbc.gridy++;
	JPanel p5 = new JPanel(new GridBagLayout());
	p5.add(new JButton("button 1"), gbc);
	p5.add(new JButton("button 2"), gbc);
	p5.add(new JButton("button 3"), gbc);
	p5.add(new JButton("button 4"), gbc);
	gbc.weighty = 1.0;
	gbc.fill = gbc.BOTH;
	p.add(p5, gbc);
	panels = new JPanel[] { p };
    }

    private void addComponents(Component c1, Component c2, Container c, GridBagConstraints gbc) {
	gbc.anchor = gbc.EAST;
	gbc.gridwidth = gbc.RELATIVE;
	c.add(c1, gbc);
	gbc.anchor = gbc.WEST;
	gbc.gridwidth = gbc.REMAINDER;
	c.add(c2, gbc);
	gbc.anchor = gbc.CENTER;
    }

    public JPanel getComponent() {
	// JScrollPane panel = new
	// JScrollPane(view.Design.getInstance().getImagePanel());

	JPanel panel = new JPanel(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(1, 3, 0, 3);
	gbc.weightx = 1.0;
	gbc.fill = gbc.HORIZONTAL;
	gbc.gridwidth = gbc.REMAINDER;
	for (int j = 0; j < aps.length; j++) {
	    panel.add(aps[j], gbc);
	    // panel.add(panels[j], gbc);
	    // panels[j].setVisible(false);
	}
	JLabel padding = new JLabel();
	gbc.weighty = 1.0;
	panel.add(padding, gbc);
	return panel;
    }

    // public static void main(String[] args)
    // {
    // JFrame f = new JFrame();
    // CollapsiblePanel test = new CollapsiblePanel(f);
    //
    // GridLayout myLayout = new GridLayout(2,1);
    // f.setLayout(myLayout);
    // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //
    // f.getContentPane().add(new
    // JScrollPane(view.Design.getInstance().getImagePanel()));
    // f.getContentPane().add(new JScrollPane(test.getComponent()));
    // //f.getContentPane().add(new JScrollPane(test.getComponent()));
    // f.setSize(360,500);
    // f.setLocation(200,100);
    // f.setVisible(true);
    // }
    public JPanel[] getPanels() {
	return panels;
    }

    public void setPanels(JPanel[] panels) {
	this.panels = panels;
    }
}
