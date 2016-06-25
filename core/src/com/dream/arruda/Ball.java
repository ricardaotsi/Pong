package com.dream.arruda;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by ti on 23/06/2016.
 */
public class Ball {
    public Vector2 pos;
    private Vector2 dir;
    private int vel;
    public int radius;

    public Ball(int w, int h){
        pos=new Vector2(w/2,h/2);
        dir=new Vector2(0.5f,1);
        radius=w/35;
        vel=w;
    }

    public void Move(float dt,int w,int h){
        pos.add(dir.cpy().scl(vel*dt));
        if(pos.y+radius>=h || pos.y-radius<=0)
            dir.set(dir.x,-dir.y);
        if(pos.x-radius<=0 || pos.x+radius>=w)
            dir.set(-dir.x,dir.y);
    }

    public void Changedir(){
        dir.set(dir.x,-dir.y);
    }

    public void Setpos(int w, int h){
        pos.set(w/2,h/2);
        dir.set(0.5f,1);
    }
}
