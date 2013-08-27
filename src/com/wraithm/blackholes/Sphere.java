package com.wraithm.blackholes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Sphere { 

	static private FloatBuffer sphereVertex;
    static private FloatBuffer sphereNormal;
    //static float sphere_parms[]=new float[3];

    double mRaduis;
    double mStep;
    float mVertices[];
    private static double DEG = Math.PI/180;
    int mPoints;

    /**
     * The value of step will define the size of each facet as well as the number of facets
     *  
     * @param radius
     * @param step
     */

    public Sphere(float radius, double step) {
        this.mRaduis = radius;
        this.mStep = step;
        //sphereVertex = FloatBuffer.allocate(40000);
        sphereVertex = ByteBuffer.allocateDirect(40000).order(ByteOrder.nativeOrder()).asFloatBuffer();
        sphereNormal = ByteBuffer.allocateDirect(40000).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mPoints = build();
    }
    
    public void setRadius(float radius) {
    	this.mRaduis = radius;
    	mPoints = build();
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, sphereVertex);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, sphereNormal);
        
        //gl.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
        //GL_TRIANGLE_FAN works fairly well
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, mPoints);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

    private int build() {

        /**
         * x = p * sin(phi) * cos(theta)
         * y = p * sin(phi) * sin(theta)
         * z = p * cos(phi)
         */
        double dTheta = mStep * DEG;
        double dPhi = dTheta;
        int points = 0;
        
        for(double phi = -(Math.PI); phi <= Math.PI; phi+=dPhi) {
            //for each stage calculating the slices
            for(double theta = 0.0; theta <= (Math.PI * 2); theta+=dTheta) {
                sphereVertex.put((float) (mRaduis * Math.sin(phi) * Math.cos(theta)));
                sphereVertex.put((float) (mRaduis * Math.sin(phi) * Math.sin(theta)));
                sphereVertex.put((float) (mRaduis * Math.cos(phi)));
                sphereNormal.put((float) (mRaduis * Math.sin(phi) * Math.cos(theta)));
                sphereNormal.put((float) (mRaduis * Math.sin(phi) * Math.sin(theta)));
                sphereNormal.put((float) (mRaduis * Math.cos(phi)));
                points++;
            }
        }
        sphereVertex.position(0);
        sphereNormal.position(0);
        
        return points;
    }
}