
public class Selected {
	public boolean someoneIsSelected;
	public int i;
	public int j;
	
	public Selected() {
		someoneIsSelected=false;
		i=-1;
		j=-1;	
	}
	public void SelectedUpdate(boolean b, int x, int y) {
		someoneIsSelected=b;
		i=x;
		j=y;	
	}
}
