package ui;

import javax.swing.*;

// Credit: 
// Main is learned from Main:
// https://www.youtube.com/watch?v=1vVJPzVzaK8&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U&ab_channel=JavaCodeJunkie

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FamilyTreeGUI();
            }
        });
    }
}
