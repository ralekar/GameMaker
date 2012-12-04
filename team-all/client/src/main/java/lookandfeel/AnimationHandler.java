package lookandfeel;

import javax.swing.JComponent;

import lookandfeel.ChangeTransition;

import org.apache.log4j.Logger;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.transitions.Effect;
import org.jdesktop.animation.transitions.EffectsManager;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.effects.CompositeEffect;
import org.jdesktop.animation.transitions.effects.FadeIn;
import org.jdesktop.animation.transitions.effects.FadeOut;
import org.jdesktop.animation.transitions.effects.Move;
import org.jdesktop.animation.transitions.effects.Rotate;
import org.jdesktop.animation.transitions.effects.Scale;

public class AnimationHandler {

    @SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(AnimationHandler.class.getName());

    public static void FadeIn(JComponent jc, JComponent container, int duration) {
        Animator animator = new Animator(duration);
        animator.setAcceleration(.2f);
        animator.setDeceleration(.4f);

        InTransition tran = new InTransition(jc, container);
        ScreenTransition transition = new ScreenTransition(container, tran, animator);

        FadeIn fader = new FadeIn();
        CompositeEffect effect = new CompositeEffect();
        effect.addEffect(fader);
        EffectsManager.setEffect(jc, effect, EffectsManager.TransitionType.APPEARING);

        transition.start();
    }

    public static void FadeOut(JComponent jc, JComponent container, int duration) {
        Animator animator = new Animator(duration);
        animator.setAcceleration(.2f);
        animator.setDeceleration(.4f);

        OutTransition tran = new OutTransition(jc, container);
        ScreenTransition transition = new ScreenTransition(container, tran, animator);

        FadeOut fader = new FadeOut();
        CompositeEffect effect = new CompositeEffect();
        effect.addEffect(fader);
        EffectsManager.setEffect(jc, effect, EffectsManager.TransitionType.DISAPPEARING);

        transition.start();
    }

    public static void RotateIn(JComponent jc, JComponent container, int duration, int degree, int xcenter, int ycenter) {
        Animator animator = new Animator(duration);
        animator.setAcceleration(.2f);
        animator.setDeceleration(.4f);

        InTransition tran = new InTransition(jc, container);
        ScreenTransition transition = new ScreenTransition(container, tran, animator);

        Rotate rotate = new Rotate(degree, xcenter, ycenter);
        CompositeEffect effect = new CompositeEffect();
        effect.addEffect(rotate);
        EffectsManager.setEffect(jc, effect, EffectsManager.TransitionType.APPEARING);

        transition.start();
    }

    public static void RotateOut(JComponent jc, JComponent container, int duration, int degree, int xcenter, int ycenter) {
        Animator animator = new Animator(duration);
        animator.setAcceleration(.2f);
        animator.setDeceleration(.4f);

        OutTransition tran = new OutTransition(jc, container);
        ScreenTransition transition = new ScreenTransition(container, tran, animator);

        Rotate rotate = new Rotate(degree, xcenter, ycenter);
        CompositeEffect effect = new CompositeEffect();
        effect.addEffect(rotate);
        EffectsManager.setEffect(jc, effect, EffectsManager.TransitionType.DISAPPEARING);

        transition.start();
    }

    public static void ChangeSizePosition(JComponent jc, JComponent container, int duration, int x, int y, int width, int height, boolean renderAsGraphics) {
        Animator animator = new Animator(duration);
        animator.setAcceleration(.2f);
        animator.setDeceleration(.4f);

        ChangeTransition tran = new ChangeTransition(jc, container, x, y, width, height);
        ScreenTransition transition = new ScreenTransition(container, tran, animator);

        Effect move = new Move();
        Scale scale = new Scale();
        CompositeEffect effect = new CompositeEffect();
        effect.addEffect(move);
        effect.addEffect(scale);
        effect.setRenderComponent(renderAsGraphics);

        EffectsManager.setEffect(jc, effect, EffectsManager.TransitionType.CHANGING);

        transition.start();
    }
}
