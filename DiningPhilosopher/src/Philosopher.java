import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
public class Philosopher implements Runnable {
		public int id;
	public  Fork leftFork, rightFrok; 
	private JLabel  comments;
	private Random randomPeriod = new Random(); 
	int maxPeriod = 5000; 
	int numberOfMeals = 0;
	JLabel la;
	JLabel thin;
	public Philosopher(int id, Fork leftFork, Fork rightFrok, JLabel comments , JLabel lab, JLabel the) {
		super();
		this.la = lab;
		this.thin = the;
		this.id = id;
		this.leftFork = leftFork;
		this.rightFrok = rightFrok;
		this.comments = comments;
	}
	public void run() {
		while (true) {
			try {
				this.think();
				this.hungry();
				this.eat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void think() throws InterruptedException {
	
		comments.setText(id+" :Thinking");
		comments.setForeground(Color.BLACK);
		la.setVisible(false);
		thin.setVisible(true);
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
	}
	public void hungry() throws InterruptedException {
		while (DiningPhilosophers.leftNeighbourPhilosopher(id) || DiningPhilosophers.rightNeighbourPhilosopher(id)) {
			Thread.sleep(randomPeriod.nextInt(10));
		}
		DiningPhilosophers.permissions.acquire();
		leftFork.pickUp();
		rightFrok.pickUp();
		DiningPhilosophers.philIsEating[id] = true;
	}

	private void eat() throws InterruptedException {
		
		comments.setText(id+" :Eating");
		comments.setForeground(Color.RED);
		la.setVisible(true);
		thin.setVisible(false);
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
		leftFork.putDown();
		rightFrok.putDown();
		DiningPhilosophers.permissions.release();
		DiningPhilosophers.philIsEating[id] = false;
	}
}