package com.yh.holdbaby.item;

import java.util.Random;

import com.yh.holdbaby.MainActivity;

public class Cradle {
	private static float BOX_WIDTH=MainActivity.width*0.625F;//盒子宽度
	private static int BASE_STEP_TIME=25;//基础步进时间
	private static int ADV_STEP_TIME=1;//时间缩短间隔
	
	private int stepTime;//摇篮步进时间
	private float x;//摇篮所处位置
	private float step_length=BOX_WIDTH*0.008F;//步进长度。为盒子宽度的0.8%
	private Baby baby;//摇篮里的宝宝
	private RunThread runThread;
	
	public Cradle(){
		int level=new Random().nextInt(8)+1;
		stepTime=BASE_STEP_TIME-level*ADV_STEP_TIME;
		x=new Random().nextInt((int)BOX_WIDTH-110);
		runThread=new RunThread();
		runThread.start();
	}
	
	public void holdBaby(Baby baby){
		this.baby=baby;
		baby.height=140;
		baby.goCradle(this);
	}
	public void babyLeave(){
		this.baby=null;
	}
	public void running(){
		x+=step_length;
		if(x+110>=BOX_WIDTH)
			step_length=-step_length;
		else if(x<=0)
			step_length=-step_length;
		
		if(baby!=null)
			baby.x=x+25;
	}
	public void free(){
		baby=null;
		runThread.run=false;
	}

	public float getX(){
		return x;
	}
	class RunThread extends Thread{
		public boolean run=true;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(run){
				running();
				try {
					Thread.sleep(stepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
