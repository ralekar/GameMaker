package lookandfeel;

import javax.swing.JComponent;
import org.jdesktop.animation.transitions.TransitionTarget;

public class ChangeTransition implements TransitionTarget {

    private JComponent jc;
    private JComponent container;
    private int x, y, width, height;

    public ChangeTransition(JComponent jc, JComponent container, int x, int y, int width, int height) {
        this.jc = jc;
        this.container = container;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setupNextScreen() {
        jc.setBounds(x, y, width, height);
        container.revalidate();
    }
}
