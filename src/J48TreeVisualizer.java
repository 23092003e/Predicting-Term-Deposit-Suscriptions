package src;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;

public class J48TreeVisualizer {

    public void run(Instances data) throws Exception {
        // Initialize the J48 classifier
        J48 tree = new J48();
        tree.buildClassifier(data);

        // Visualize tree
        final JFrame jf = new JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(800, 600);
        jf.getContentPane().setLayout(new BorderLayout());

        TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });

        jf.setVisible(true);
        tv.fitToScreen();
    }
}
