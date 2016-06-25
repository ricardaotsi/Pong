package com.dream.arruda;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ti on 23/06/2016.
 */
public class Brick {
    public Rectangle[][] pos;
    public Boolean[][] colidiu;

    public Brick(int w,int h){
        pos = new Rectangle[6][7];
        colidiu=new Boolean[6][7];
        for (int i=0; i<= pos.length-1;i++)
        {
            for (int j=0; j<=pos[i].length-1;j++)
            {
                pos[i][j] = new Rectangle(w/7*j+w/40,h/20*(i)+h/3*2,w/10,h/25);
                colidiu[i][j] = false;
            }
        }
    }
}
