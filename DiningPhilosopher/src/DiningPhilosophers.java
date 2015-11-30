
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTable;

public class DiningPhilosophers {
	private JFrame frame;	
	static final int NUM_PHILOSOPHERS = 5;
	Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
	static Semaphore permissions = new Semaphore(2);
	static boolean [] philIsEating = {false, false, false, false, false};
	JLabel[] PhilsLables = new JLabel[5];
	JLabel[] comments = new JLabel[5];
	private JLabel lblNewLabel_0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	JLabel[] la = new JLabel[5];
	JLabel[] thin = new JLabel[5];
	//private JTable table;
	//private JTable table_1;
	//private JTable table_2;
	//private JTable table_3;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiningPhilosophers window = new DiningPhilosophers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DiningPhilosophers() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0,600, 600);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Button start = new Button("start eating");
		start.setBounds(20,118 , 101, 22);
		start.setBackground(Color.WHITE);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(start);
		lblNewLabel_0 = new JLabel(" 0");
		lblNewLabel_0.setBounds(193, 84, 88, 30);
		frame.getContentPane().add(lblNewLabel_0);
		
		lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setBounds(201, 126, 110, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("2");
		lblNewLabel_2.setBounds(201,165, 169, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("3");
		lblNewLabel_3.setBounds(201, 207, 169, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("4");
		lblNewLabel_4.setBounds(201, 261, 169, 14);
		frame.getContentPane().add(lblNewLabel_4);
		comments[0] = lblNewLabel_0;
		comments[1] = lblNewLabel_1;
		comments[2] = lblNewLabel_2;
		comments[3] = lblNewLabel_3;
		comments[4] = lblNewLabel_4;
		
		
		//la = new JLabel[5];
		//Image img = new ImageIcon(this.getClass().getResource("/fork.png")).getImage();
		//ImageIcon im = new ImageIcon(img);
		la[0] = new JLabel("");
		la[1] = new JLabel("");
		la[2] = new JLabel("");
		la[3] = new JLabel("");
		la[4] = new JLabel("");
		for(int i=0;i<5;i++){
			//la[i].setIcon(im);
			frame.getContentPane().add(la[i]);
		}
		la[0].setBounds(225, 40, 59, 58);
		la[1].setBounds(365, 50, 59, 58);
		la[2].setBounds(365, 200, 59, 58);
		la[3].setBounds(30, 200, 59, 58);
		la[4].setBounds(30, 60, 59, 58);
		la[0].setVisible(false);
		la[1].setVisible(false);
		la[2].setVisible(false);
		la[3].setVisible(false);
		la[4].setVisible(false);
		//Image img1 = new ImageIcon(this.getClass().getResource("/think.png")).getImage();
		//ImageIcon im1 = new ImageIcon(img1);
		thin[0] = new JLabel("");
		thin[1] = new JLabel("");
		thin[2] = new JLabel("");
		thin[3] = new JLabel("");
		thin[4] = new JLabel("");
		for(int i=0;i<5;i++){
			//thin[i].setIcon(im1);
			frame.getContentPane().add(thin[i]);
		}
		thin[0].setBounds(225, 40, 59, 58);
		thin[1].setBounds(365, 50, 59, 58);
		thin[2].setBounds(365, 200, 59, 58);
		thin[3].setBounds(30, 200, 59, 58);
		thin[4].setBounds(30, 60, 59, 58);
		thin[0].setVisible(false);
		thin[1].setVisible(false);
		thin[2].setVisible(false);
		thin[3].setVisible(false);
		thin[4].setVisible(false);
		}
	
	public void start() {		
		
		Fork[] forks = new Fork[NUM_PHILOSOPHERS];
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			forks[i] = new Fork(i);
		}
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, forks[(i + 1) % NUM_PHILOSOPHERS], forks[i], comments[i],la[i],thin[i]);
			new Thread(philosophers[i]).start();
		}
	}
	public synchronized static boolean leftNeighbourPhilosopher(int id) {
		return DiningPhilosophers.philIsEating[(id + 1)
				% DiningPhilosophers.NUM_PHILOSOPHERS]; 
	}
	public synchronized static boolean rightNeighbourPhilosopher(int id) {

		if (id == 0)
		{
			return DiningPhilosophers.philIsEating[4];
		}
		else
		{
			return DiningPhilosophers.philIsEating[(id - 1)
					% DiningPhilosophers.NUM_PHILOSOPHERS];
		}
	}
}