package com.wraithm.blackholes;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;

public class OpenGLRenderer implements Renderer {
    private Sphere sphere;
    public Camera camera;
    //private Kerr kerr;
    
    public OpenGLRenderer() {
        sphere = new Sphere(1, 10);
        //camera = new Camera(0.0f, 2.5f, 5.0f,  0.0f, 2.5f, 0.0f,  0.0f, 1.0f, 0.0f);
        camera = new Camera(0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        //kerr = new Kerr(sphere);
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
     * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
     */
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        float specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shininess[] = {80.0f};
        float whiteLight[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float ambient[] = {0.2f, 0.2f, 0.2f, 1.0f};
        float lightPosition[] = {0.0f, 0.0f, 0.0f, 1.0f};
        
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL10.GL_SMOOTH);
        
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, specular, 0);
        gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SHININESS, shininess, 0);

        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, whiteLight, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, ambient, 0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambient, 0);
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, ambient, 0);
        
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
     * khronos.opengles.GL10)
     */
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        GLU.gluLookAt(gl, camera.pos.x, camera.pos.y, camera.pos.z,
                          camera.view.x, camera.view.y, camera.view.z,
                          camera.up.x, camera.up.y, camera.up.z);
        
        long time = SystemClock.uptimeMillis();
        double angle = 0.001*((float) time);
        float lightPosition[] = {(float) (3.0f*Math.cos(angle)), 3.0f,
                                 (float) (3.0f*Math.sin(angle)), 1.0f};
        gl.glPushMatrix();
            gl.glTranslatef(lightPosition[0], lightPosition[1], lightPosition[2]);
            float color[] = {0.0f, 1.0f, 1.0f, 1.0f};
            gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT_AND_DIFFUSE, color, 0);
            gl.glColor4f(color[0], color[1], color[2], color[3]);
            sphere.setRadius(0.5f);
            sphere.draw(gl);
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
        gl.glPopMatrix();
        
        float color2[] = {1.0f, 1.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT_AND_DIFFUSE, color2, 0);
        gl.glColor4f(color2[0], color2[1], color2[2], color2[3]);
        sphere.setRadius(1.0f);
        //gl.glPushMatrix();
        //gl.glRotatef((float) (2.0f*angle), 0.0f, 1.0f, 0.5f);
        sphere.draw(gl);
        //gl.glPopMatrix();
        
        
        //kerr.drawKerr(gl);
        //kerr.drawTrace(gl);
        
        gl.glFlush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
     * .khronos.opengles.GL10, int, int)
     */
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 80.0f, (float) width / (float) height, 0.1f, 300.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
