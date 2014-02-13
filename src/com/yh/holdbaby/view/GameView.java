package com.yh.holdbaby.view;

import com.yh.holdbaby.R;
import com.yh.holdbaby.game.HoldBaby;
import com.yh.holdbaby.util.ImageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback{

	RefreshThread rfthread;
	
	Bitmap bg_play;
	Bitmap item_cradle;
	Bitmap item_baby;
	private Paint paint;
	
	private HoldBaby holdBaby;
	private float[] cradlesX;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		rfthread = new RefreshThread(holder);
		bg_play=BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_play);
		item_cradle=BitmapFactory.decodeResource(context.getResources(), R.drawable.item_cradle);
		item_baby=BitmapFactory.decodeResource(context.getResources(), R.drawable.item_baby);
		bg_play=ImageUtil.resizeImage(bg_play, 720F, 1280F);
		item_cradle=ImageUtil.resizeImage(item_cradle, 110F, 20F);
		item_baby=ImageUtil.resizeImage(item_baby, 60F, 60F);
		
		holdBaby=new HoldBaby();
	}

	public void drawing(Canvas canvas){
		canvas.drawBitmap(bg_play, 0, 0, paint);
		canvas.drawBitmap(item_baby, holdBaby.getBaby().x+55,1030-holdBaby.getBaby().height, paint);
		canvas.drawBitmap(item_cradle,(int)cradlesX[0]+55,930,paint);
		canvas.drawBitmap(item_cradle,(int)cradlesX[1]+55,710,paint);
		canvas.drawBitmap(item_cradle,(int)cradlesX[2]+55,490,paint);
		canvas.drawBitmap(item_cradle,(int)cradlesX[3]+55,270,paint);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		rfthread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		rfthread.run = false;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		holdBaby.babyFly();
		return true;
	}


	class RefreshThread extends Thread {
		SurfaceHolder surface;
		boolean run = true;
		boolean pause = false;

		public RefreshThread(SurfaceHolder surface) {
			this.surface = surface;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Canvas canvas = new Canvas();
			while (run) {
				while (!pause) {
					try {
						canvas = surface.lockCanvas();
						cradlesX=holdBaby.getCradlesX();
						drawing(canvas);
//						Thread.sleep(200);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (canvas != null) {
							surface.unlockCanvasAndPost(canvas);
						}
					}
				}
				super.run();
			}
		}
	}
}
