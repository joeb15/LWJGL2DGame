package time;

import java.util.ArrayList;
import java.util.List;

public class Time {

	private static long time=0;
	private static double delta=0;
	private static double totalGameTime=0;
	
	private static List<Timer> timers = new ArrayList<Timer>();
	
	public static void addTimer(Timer t){
		if(!timers.contains(t))
			timers.add(t);
	}
	
	public static void update(){
		if(time==0)
			time = System.nanoTime();
		long curr = System.nanoTime();
		delta = (curr-time)/1E9;
		time=curr;
		totalGameTime+=delta;
		for(Timer t:timers)
			t.update(delta);
	}
	
	public static double getDelta(){
		return delta;
	}
	
}
