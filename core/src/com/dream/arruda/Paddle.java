package com.dream.arruda;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by ti on 23/06/2016.
 */
public class Paddle {
    public Rectangle pos;
    private int w;
    private int h;

    public Paddle(int w, int h){
        pos=new Rectangle(w/2-w/5/2,h/7,w/5,h/35);
        this.w=w;
        this.h=h;
    }

    public void Move(int x){
        pos.set(x-w/5/2,h/7,w/5,h/35);
    }
}
