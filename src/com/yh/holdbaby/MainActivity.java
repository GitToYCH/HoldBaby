package com.yh.holdbaby;

import com.yh.holdbaby.view.GameView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.app.Activity;

public class MainActivity extends Activity {
	public static float width, height;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels; 
		
		GameView game=new GameView(this);
		setContentView(game);
	}
}
