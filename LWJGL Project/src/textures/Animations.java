package textures;

import java.util.HashMap;


public class Animations {

    private static class AnimData{
        private String fileName;
        private int frame;
        private float time;
        private AnimData(String fileName, int frame, float time){
            this.fileName=fileName;
            this.frame=frame;
            this.time=time;
        }
        public boolean equals(Object o){
            if(o instanceof AnimData){
                AnimData ad = ((AnimData)o);
                return ad.time==time && ad.frame==frame && ad.fileName.equals(fileName);
            }
            return false;
        }
    }

	private static HashMap<AnimData, Animation> animations = new HashMap<AnimData, Animation>();
	
	public static Animation getAnimation(String fileName, int frames, float time){
        AnimData ad = new AnimData(fileName, frames, time);
	    if(!animations.containsKey(ad))
			animations.put(ad, new Animation(fileName, frames, time));
		return animations.get(ad);
	}
	
}
