import java.util.concurrent.locks.ReentrantLock;
//import javax.swing.JLabel;
public  class Fork extends ReentrantLock{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	public Fork(int id) {
		super();
		this.id = id;
	}
	public synchronized void pickUp(){
		this.lock();	
	}
	public synchronized void putDown(){
				this.unlock();
			}	
}