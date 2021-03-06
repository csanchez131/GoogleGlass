package edu.berkeley.wifi;

import java.io.IOException;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** A basic Camera preview class */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;

	public CameraPreview(Context context, Camera camera) {
		super(context);
		mCamera = camera;
		mHolder = getHolder();
		mHolder.addCallback(this);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e) {
			Log.d("TAG1: ", "Error setting camera preview: " + e.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

		if (mHolder.getSurface() == null){
			return;
		}

		try {
			mCamera.stopPreview();
		} catch (Exception e){
		}

		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (Exception e){
			Log.d("TAG2: ", "Error starting camera preview: " + e.getMessage());
		}
	}
}