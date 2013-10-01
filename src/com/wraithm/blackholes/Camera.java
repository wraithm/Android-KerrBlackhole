package com.wraithm.blackholes;

public class Camera {
    public Vec pos, view, up;
    private Vec vel;
    
    public Camera(
            float posx, float posy, float posz,
            float viewx, float viewy, float viewz,
            float upx, float upy, float upz) {
        this.setCameraPosition(posx, posy, posz, viewx, viewy, viewz, upx, upy, upz);
    }
    
    public void setCameraPosition(
            float posx, float posy, float posz,
            float viewx, float viewy, float viewz,
            float upx, float upy, float upz) {
        this.pos = new Vec(posx, posy, posz);
        this.view = new Vec(viewx, viewy, viewz);
        this.up = new Vec(upx, upy, upz);
    }
    
    public void moveForeward(float speed) {
        vel = Vec.sub(view, pos);
        
        pos.x = pos.x + vel.x*speed;
        pos.y = pos.y + vel.y*speed;
        pos.z = pos.z + vel.z*speed;
        
        view.x = view.x + vel.x*speed;
        view.y = view.y + vel.y*speed;
        view.z = view.z + vel.z*speed;
    }
    
    public void moveByVec(Vec dir, float speed) {
        pos = Vec.add(dir, pos);
        view = Vec.add(dir, view);
    }
    
    public void rotateCamera(float speed) {
        vel = Vec.sub(view, pos);
        
        view.z = (float)(pos.z + Math.sin(speed)*vel.x + Math.cos(speed)*vel.z);
        view.x = (float)(pos.x + Math.cos(speed)*vel.x - Math.sin(speed)*vel.z);
    }
}
