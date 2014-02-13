package com.yh.holdbaby.item;

public class Baby {
	public float x;// 宝宝所处位置
	public float height;// 宝宝所处高度
	private Cradle cradle;

	private FlyThread flyThread;
	
	public Baby(Cradle cradle) {
		x = cradle.getX();
		cradle.holdBaby(this);
		height=140;
	}
	public void goCradle(Cradle cradle){
		this.cradle=cradle;		
	}
	public void fly(){
		if(flyThread==null){
			flyThread=new FlyThread();
			cradle.babyLeave();
			flyThread.start();
		}
	}
	
	private void flying(float stepHeight) {
		height += stepHeight;
		if(height<0&&flyThread!=null){
			flyThread.run=false;
			flyThread=null;
			this.cradle.holdBaby(this);
		}
			
	}
	class FlyThread extends Thread {
		public boolean run=true;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			float v0 = 30;
			float a = -1.7F;
			float t = 0;

			while (run) {
				float vt = v0+a * t;
				t += 1;
				flying(vt);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
