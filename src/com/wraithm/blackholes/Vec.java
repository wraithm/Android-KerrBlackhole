package com.wraithm.blackholes;

public class Vec {
	public float x, y, z;
	
	public Vec() {
		this.setVec(0, 0, 0);
	}
	
	public Vec(float x, float y, float z) {
		this.setVec(x, y, z);
	}
	
	public Vec add(Vec v1, Vec v2) {
		return new Vec(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}
	
	public Vec sub(Vec v1, Vec v2) {
		return new Vec().add(v1, v2.scale(-1.0f));
	}
	
	public Vec scale(float c) {
		return new Vec(this.x*c, this.y*c, this.z*c);
	}
	
	private void setVec(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}