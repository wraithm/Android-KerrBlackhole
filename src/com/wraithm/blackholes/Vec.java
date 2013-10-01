package com.wraithm.blackholes;

public class Vec {
    public float x, y, z;
    
    public Vec(float x, float y, float z) {
        this.setVec(x, y, z);
    }
    
    public static Vec add(Vec v1, Vec v2) {
        return new Vec(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    
    public static Vec sub(Vec v1, Vec v2) {
        return Vec.add(v1, v2.scale(-1.0f));
    }
    
    public Vec scale(float c) {
        x = x*c;
        y = y*c;
        z = z*c;

        return this;
    }
    
    private void setVec(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
