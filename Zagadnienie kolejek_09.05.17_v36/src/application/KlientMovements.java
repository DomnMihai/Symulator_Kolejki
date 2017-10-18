package application;

public interface KlientMovements {
	public void moveInQueue(int position, int previousPosition);
	public void moveToKasa(int kasa);
	public void quitQueue();
	public void quitKasa(int kasa);
	
	/**	Return the position of the klient.
	*	<li>-2 should be removed
 	*	<li> -1 is moving
	*	<li>0 just spawned
	*	<li>1-14 place in queue
	*	<li>15-26 number of kasy</li><p>
	*/
	public int getLinePosition();
}
