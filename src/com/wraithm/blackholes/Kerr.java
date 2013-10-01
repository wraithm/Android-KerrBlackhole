package com.wraithm.blackholes;

import javax.microedition.khronos.opengles.GL10;
import android.os.SystemClock;

// TODO Plot path of particle
public class Kerr {
    private final double INCREMENT = 0.003f;
    private final double SIMSPEED = 10000;
    private final int PREVSTEP = 0;
    
    private double tint = 5.0;
    private double dt;
    
    private ODESolver solver;
    private Sphere sphere;
    
    private double prevpos[] = {0, 0.0, 18.0, Math.PI/2, 0.0, 23.0, 0.0, 0.08, 0.06};
    
    private int step;
    
    public Kerr(Sphere sphere) {
        this.sphere = sphere;
        solver = new ODESolver();
    }
    
    public void drawKerr(GL10 gl) {
        double Y[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int nbad = 0, nok = 0, nvar;
        double h, t1;
        
        for(int i = 1; i < prevpos.length; i++)
            Y[i] = prevpos[i];
        
        nvar = 8;
        t1 = 0.0;
        h = 0.01;
        
        long time;
        double deltat;
        
        if(tint == 5.0) {
            time = SystemClock.uptimeMillis();
            tint = (double) time/1000.0;
            deltat = tint + INCREMENT;
        } else {
            time = SystemClock.uptimeMillis();
            deltat = (double) time/1000.0;
        }
        
        dt = deltat - tint;
        
        solver.odeint(Y, nvar, t1, SIMSPEED*dt, 1e-4, h, 0.001, nok, nbad);
        
        float x = (float) (Math.sqrt(Math.pow(Y[2], 2) + Math.pow(solver.a, 2)) * Math.cos(Y[4]) * Math.sin(Y[3]));
        float y = (float) (Y[2] * Math.cos(Y[3]));
        float z = (float) (Math.sqrt(Math.pow(Y[2], 2) + Math.pow(solver.a, 2)) * Math.sin(Y[3]) * Math.sin(Y[4]));
        
        gl.glPushMatrix();
            gl.glColor4f(0.0f, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(x, y, z);
            sphere.setRadius((float) (2.0*solver.M));
            sphere.draw(gl);
        gl.glPopMatrix();
        
        if(step >= PREVSTEP) {
            for(int i = 1; i < prevpos.length; i++)
                prevpos[i] = Y[i];
            
            step = 0;
        } else {
            step++;
        }
    }
}
