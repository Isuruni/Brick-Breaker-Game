package com.isuruni;
import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) {
        JFrame object =new JFrame();
        Game_Play gameplay=new Game_Play();
        object.setBounds(10,10,700,600);
        object.setTitle("Brick Breaker");
        object.setResizable(false);
        object.setVisible(true);
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        object.add(gameplay);
    }
}
