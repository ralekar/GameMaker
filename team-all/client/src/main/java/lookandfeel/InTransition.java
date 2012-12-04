package lookandfeel;

import javax.swing.JComponent;
import org.jdesktop.animation.transitions.TransitionTarget;

public class InTransition implements TransitionTarget {

    private JComponent jc;
    private JComponent container;

    public InTransition(JComponent jc, JComponent container) {
        this.jc = jc;
        this.container = container;
    }

    @Override
    public void setupNextScreen() {
        container.remove(jc);
        container.add(jc);
    }
}
