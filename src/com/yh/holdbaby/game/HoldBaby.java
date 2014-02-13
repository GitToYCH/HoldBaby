package com.yh.holdbaby.game;

import java.util.ArrayList;

import com.yh.holdbaby.item.Baby;
import com.yh.holdbaby.item.Cradle;

public class HoldBaby {
	private ArrayList<Cradle> cradles;// ÊÓÒ°ÖĞµÄÒ¡Àº
	private Baby baby;//±¦±¦
	private int level;

	public HoldBaby() {
		level = 1;
		cradles = new ArrayList<Cradle>();
		for (int i = 0; i < 5; i++)
			cradles.add(new Cradle());
		baby=new Baby(cradles.get(0));
	}
	
	public float []getCradlesX(){
		float []xs=new float[cradles.size()];
		for(int i=0;i<cradles.size();i++){
			xs[i]=cradles.get(i).getX();
		}
		return xs;
	}
	
	public Baby getBaby(){
		return baby;
	}
	
	public void babyFly(){
		baby.fly();
	}
}
