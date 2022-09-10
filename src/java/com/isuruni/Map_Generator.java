package com.isuruni;
import java.awt.*;
public class Map_Generator {
    public int map[][];
    public int brick_width;
    public int brick_height;
    int count=0;
    int x=80;
    int y=50;
    int i,j;
    public Map_Generator(int row,int column){
        map=new int[row][column];
        for( i=0;i<map.length;i++){
            for( j=0;j<map[0].length;j++){
                map[i][j]=1;
            }
        }
        brick_width=500/column;
        brick_height=350/row;
    }
    public void draw(Graphics2D g){
        for(int i=0;i<10;i++){

            for(int j=0;j<10;j++){

                if(map[i][j]>0){
                    if(i==0||i==10)
                        if(i%2==0){
                            if(j%2==1){
                                g.setColor(Color.white);
                                g.fillRect(j*brick_width+x,i*brick_height+y,brick_width,brick_height);
                            }

                        }
                        else if(i%2==1){
                            if(j%2==0){
                                g.setColor(Color.white);
                                g.fillRect(j*brick_width+x,i*brick_height+y,brick_width,brick_height);
                            }

                        }


                }

            }
        }

    }
    public void setBrickValue(int value,int row,int column){
        map[row][column]=value;
    }
}