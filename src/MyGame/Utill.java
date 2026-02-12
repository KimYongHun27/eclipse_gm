package MyGame;

public class Utill {

	static int clamp(int current, int max) {
	    if (current > max) {
	        return max;
	    }
	    return current;
	}
	
	public boolean PrioritySequence(Character Ch1, Character Ch2)
	{
		return (Ch1.GetSpeed() > Ch2.GetSpeed()) ? true : false;
	}
}
