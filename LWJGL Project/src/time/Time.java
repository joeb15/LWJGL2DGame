package time;

import java.util.ArrayList;
import java.util.List;

public class Time {

	private static long time=0;
	private static double delta=0;
	private static double totalGameTime=0;
	
	private static List<Timer> timers = new ArrayList<Timer>();
	
	public static Timer addTimer(TimerInterface ti, double time){
		return addTimer(ti, time, -1);
	}
	
	public static Timer addTimer(TimerInterface ti, double time, int repetitions){
		Timer t = new Timer(ti, time, repetitions);
		addTimer(t);
		return t;
	}
	
	private static void addTimer(Timer t){
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
	
	public static double getTotalGameTime(){
		return totalGameTime;
	}
	
	public static double getDelta(){
		return delta;
	}
	
}
