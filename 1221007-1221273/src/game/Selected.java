package game;
public class Selected {
	protected boolean someoneIsSelected;
	protected  int i;
	protected  int j;
	
	public Selected() {
		someoneIsSelected=false;
		i=-1;
		j=-1;	
	}
	protected void SelectedUpdate(boolean b, int x, int y) {
		someoneIsSelected=b;
		i=x;
		j=y;	
	}
}
