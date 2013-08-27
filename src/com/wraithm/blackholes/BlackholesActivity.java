package com.wraithm.blackholes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class BlackholesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GLSurfaceView view = new BlackholesSurfaceView(this);
        //view.setRenderer(new OpenGLRenderer());
        //setContentView(R.layout.main);
        setContentView(view);
    }
}

class BlackholesSurfaceView extends GLSurfaceView {
	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private OpenGLRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;
    
	public BlackholesSurfaceView(Context context){
        super(context);
        mRenderer = new OpenGLRenderer();
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
	
	@Override 
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();
        
        switch (e.getAction()) {
	        case MotionEvent.ACTION_MOVE:
	        	float dx = (x - mPreviousX)*TOUCH_SCALE_FACTOR/100;
	        	mRenderer.camera.rotateCamera(dx);
	        	float dy = (y - mPreviousY)*TOUCH_SCALE_FACTOR/100;
	        	mRenderer.camera.moveForeward(-dy);
        }

	    requestRender();

        //                float dx = x - mPreviousX;
        //                float dy = y - mPreviousY;
        //    
        //                if (y > getHeight() / 2) {
        //                  dx = dx * -1 ;
        //                }
        //   
        //                if (x < getWidth() / 2) {
        //                  dy = dy * -1 ;
        //                }
        //              
        //                mRenderer.mAngle += (dx + dy) * TOUCH_SCALE_FACTOR;
        //                requestRender();

        mPreviousX = x;
        mPreviousY = y;
        return true;
	} 
}