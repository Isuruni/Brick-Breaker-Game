package com.isuruni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game_Play extends JPanel implements KeyListener, ActionListener {
    private boolean play=false;
    private int scores;
    private int total_bricks=100;
    private Timer timer;
    private int delay;

    private int playerX=310;

    private int ballX=60;
    private int ballY=400;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private Map_Generator map;

    public  Game_Play(){
        map=new Map_Generator(10,10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();

    }

    public void paint(Graphics g){

        g.setColor(Color.black);
        g.fillRect(1,1,692, 592);
        map.draw((Graphics2D) g);

        g.setColor(Color.yellow);
        g.fillRect(0,0, 3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(681,0,3,592);

        g.setColor(Color.blue);
        g.fillRect(playerX,550,100,8);

        g.setColor(Color.red);
        g.fillOval(ballX,ballY,10,10);

        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+scores,590,30);

        if(total_bricks==0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("You Won.\nScore:"+scores,190,350);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart!",200,400);
        }
        if(ballY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over.\nScore:"+scores,190,500);


            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to restart!",230,530);
        }

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play==true ){
            if(new Rectangle(ballX,ballY,20,30).intersects(new Rectangle(playerX,550,100,8)) ){
                ballYdir= -ballYdir;
            }
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.brick_width+80;
                        int brickY=i*map.brick_height+50;
                        int brick_width=map.brick_width;
                        int brick_height=map.brick_height;
                        Rectangle r= new Rectangle(brickX,brickY,brick_width,brick_height);
                        Rectangle br=new Rectangle(ballX,ballY,10,10);
                        Rectangle brickR=r;
                        if(br.intersects(brickR)){
                            map.setBrickValue(0,i,j);
                            total_bricks--;
                            scores+=5;
                            if(ballX+14<=brickR.x||ballX+1<=brickR.x+ brickR.width){
                                ballXdir= -ballXdir;
                            }else{
                                ballYdir= -ballYdir;
                            }
                        }
                    }
                }
            }

            ballX+=ballXdir;
            ballY+=ballYdir;
            if(ballX<0){
                ballXdir= -ballXdir;
            }
            if(ballY<0){
                ballYdir= -ballYdir;
            }
            if(ballX>670){
                ballXdir= -ballXdir;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if (playerX>580){
                playerX=580;
            }
            else{
                moveRight();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if (playerX<10){
                playerX=10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(play=false){
                play=true;
                ballX=60;
                ballY=400;
                ballXdir=-1;
                ballYdir=-2;
                scores=0;
                total_bricks=140;
                map=new Map_Generator(20,7);
                repaint();
            }
        }

    }
    public void moveRight(){
        play=true;
        playerX+=20;
    }
    public void moveLeft(){
        play=true;
        playerX-=20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}