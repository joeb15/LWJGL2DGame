package time;

public class Timer {	
	
	private static int TIMER_COUNTER=0;
	
	private double time=0;
	private double desiredTime=0;
	private TimerInterface ti;
	private int id;
	private boolean enabled=true;
	private int numTimes;
	
	public Timer(TimerInterface ti, double time){
		this(ti,time,-1);
	}	
	
	public Timer(TimerInterface ti, double time, int numTimes){
		this.ti=ti;
		this.desiredTime=time;
		this.numTimes=numTimes;
		id = TIMER_COUNTER++;
		
		Time.addTimer(this);
	}
	
	public void setEnabled(boolean enabled){
		this.enabled=enabled;
	}
	
	public void update(double t){
		time+=t;
		if(time>=desiredTime){
			time-=desiredTime;
			if(enabled){
				ti.tick();
				if(numTimes!=-1 && numTimes>0)
					numTimes--;
				if(numTimes==0)
					enabled=false;
			}
		}
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Timer))return false;
		return ((Timer)o).id==id;
	}
}