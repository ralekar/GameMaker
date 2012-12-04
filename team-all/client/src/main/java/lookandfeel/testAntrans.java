package lookandfeel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class testAntrans {

    public static void main(String[] args) {
        final JFrame f = new JFrame();
        f.setLayout(null);
        final JButton jb1 = new JButton("Fade In");
        jb1.setBounds(10, 10, 100, 30);
        final JButton jb2 = new JButton("Fade Out");
        jb2.setBounds(10, 50, 100, 30);
        final JButton jb3 = new JButton("Rotate In");
        jb3.setBounds(10, 100, 100, 30);
        final JButton jb4 = new JButton("Rotate Out");
        jb4.setBounds(10, 150, 100, 30);
        final JButton jb5 = new JButton("Larger");
        jb5.setBounds(10, 200, 100, 30);
        final JButton jb6 = new JButton("Smaller");
        jb6.setBounds(10, 250, 100, 30);
        final JButton jb7 = new JButton("Move Right");
        jb7.setBounds(10, 300, 100, 30);
        final JButton jb8 = new JButton("Move Left");
        jb8.setBounds(10, 350, 100, 30);


        final JButton test = new JButton("Target");
        test.setBounds(200, 200, 200, 50);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.FadeIn(test, (JComponent) f.getContentPane(), 500);
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.FadeOut(test, (JComponent) f.getContentPane(), 500);
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.RotateIn(test, (JComponent) f.getContentPane(), 500, 360, 50, 50);
            }
        });

        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.RotateOut(test, (JComponent) f.getContentPane(), 500, -360, 50, 50);
            }
        });

        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.ChangeSizePosition(test, (JComponent) f.getContentPane(), 500, test.getX(), test.getY(), 300, 100, false);
            }
        });

        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.ChangeSizePosition(test, (JComponent) f.getContentPane(), 500, test.getX(), test.getY(), 50, 20, false);
            }
        });

        jb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.ChangeSizePosition(test, (JComponent) f.getContentPane(), 500, test.getX() + 300, test.getY(), test.getWidth(), test.getHeight(), false);
            }
        });

        jb8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimationHandler.ChangeSizePosition(test, (JComponent) f.getContentPane(), 500, test.getX() - 300, test.getY(), test.getWidth(), test.getHeight(), false);
            }
        });

        f.add(jb1);
        f.add(jb2);
        f.add(jb3);
        f.add(jb4);
        f.add(jb5);
        f.add(jb6);
        f.add(jb7);
        f.add(jb8);
        f.setSize(800, 600);
        f.setVisible(true);


    }
}
