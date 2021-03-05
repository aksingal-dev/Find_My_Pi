import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Piframe implements ActionListener{
	//Basic setup for GUI
	JFrame fr = new JFrame();
	JLabel Title = new JLabel("Finding Pi using different methods");
	JLabel time = new JLabel("");
	ArrayList<JLabel> results = new ArrayList<JLabel>(); //Used to Implement SwingWorker functionality.
	int labelcount = 0;
	
	//See Newton.java for implementation
	JLabel Newt = new JLabel("Using Newton's Method");
	JLabel Newt_result = new JLabel("Result: ");
	
	//See Infinite_odd.java for implementation 
	JLabel I_odd = new JLabel("Using the series (-1)^x/(2x+1)");
	JLabel I_odd_result = new JLabel("Result: ");
	
	//See Infinite_square.java for implementation
	JLabel I_square = new JLabel("Using the series (i/x)^2");
	JLabel I_square_result = new JLabel("Result: ");
	
	//See random.java for Implementation.
	JLabel Rand = new JLabel("Using the GCF of random numbers");
	JLabel Rand_result = new JLabel("Result: ");
	
	//Used to provide support for computers with varied processing power.
	JLabel Rand_limit = new JLabel("higher limit for random number");
	JLabel Iterations = new JLabel("Number of iterations");
	JTextField limit = new JTextField();
	JTextField iterations = new JTextField();
	
	//Navigation and processing
	JButton Calculate = new JButton("Calculate Value");
	JButton exit = new JButton("Exit");
	JButton clear = new JButton("Clear");
	
	//Generator for Application
	public Piframe() {
		//Create GUI
		prepareGUI();
		
		/*Add result placeholder labels to returns ArrayList.
		*The order of the list is important and is referred to by labelcount when updating
		*/
		results.add(0, Newt_result);
		results.add(1, I_odd_result);
		results.add(2, I_square_result);
		results.add(3, Rand_result);
	}
	
	public void prepareGUI() {
		
		//Basic Setup
		fr.setTitle("The Recipe for Pi");
		fr.getContentPane().setLayout(null);
		fr.setVisible(true);
		fr.setBounds(200, 200, 500, 350);
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fr.setResizable(false);
		
		//Set properties for all labels and textboxes
		setProp();
		
		//set properties for all buttons
		buttonProperties();
		
		//Add all components to frame
		Addtoframe();
		
	}
	
	//Setting button properties
	private void buttonProperties() {
		Calculate.setBounds(185, 180, 141, 30);
		Calculate.addActionListener(this); //on button click
		
		clear.setBounds(185, 220, 70, 25);
		clear.addActionListener(this); //on button click
		
		exit.setBounds(256, 220, 70, 25);
		exit.addActionListener(this); //on button click
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Calculate) { //calculate values of Pi
			@SuppressWarnings("rawtypes")
			SwingWorker sw1 = new SwingWorker<String, Double>() { //implementation of concurrency to allow UI updates as and when we get a result.
				@Override
				protected String doInBackground() throws Exception{
					double res; //result
					long start = System.currentTimeMillis(); //time of process start
					
					//Using Newton's method
					Newton newt = new Newton();
					res = newt.pi();
					labelcount =0; //used to choose which label is updated
					publish(res); //Display Result
					Thread.sleep(1000);
					
					//Using Infinite Odd series.
					Infinite_odd i_odd; 
					try { //with user input
						i_odd = new Infinite_odd(Integer.parseInt(iterations.getText()));
					}catch (Exception e) { //With default values
						i_odd = new Infinite_odd();
					}
					res = i_odd.pi();
					labelcount =1; //used to choose which label is updated
					publish(res); //display result
					Thread.sleep(1000);
					
					//Using infinite square series
					Infinite_square i_square;
					try { //with user input
						i_square = new Infinite_square(Integer.parseInt(iterations.getText()));
					}catch (Exception e) { //with default values
						i_square = new Infinite_square();
					}
					res = i_square.pi();
					labelcount =2; //used to choose which label is updated
					publish(res); //display result
					Thread.sleep(1000);
					
					//Using the probability of two random numbers being co-primes
					random randpi;
					try { //with user input
						randpi = new random(Integer.parseInt(limit.getText()), Integer.parseInt(iterations.getText()));	
					}catch (Exception e) { //with default values
						randpi = new random();
					}
					res = randpi.pi();
					labelcount = 3; //used to choose which label is updated
					publish(res); //display result
					Thread.sleep(100);
					
					long end = System.currentTimeMillis(); //Time of process end
					
					return timeconvert(end-start); //total time taken is HH:MM:SS
				}

				@Override
				protected void process(List chunks) { //display partial results
						results.get(labelcount).setText("Result: " + chunks.get(chunks.size()-1));
				}
				
				@Override
				protected void done() { //display total time taken at the end of all processes.
					try {	
						String msg = (String) get(); //get return
						time.setText("Time Taken: " + msg);
					}
					catch (Exception e) { //error 
						e.printStackTrace();
					}
				}
			};
			
			sw1.execute(); //run SwingWorker

		}
		if(e.getSource() == clear) { //clear all results
			Newt_result.setText("Result: ");
			I_odd_result.setText("Result: ");
			I_square_result.setText("Result: ");
			Rand_result.setText("Result: ");
		}
		
		if(e.getSource() == exit) { //exit application
			fr.dispose();
		}
				
	}

	
	private String timeconvert(long l) { //convert time from long to HH:MM:SS
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(l), //hours
			    TimeUnit.MILLISECONDS.toMinutes(l) % TimeUnit.HOURS.toMinutes(1), //minutes
			    TimeUnit.MILLISECONDS.toSeconds(l) % TimeUnit.MINUTES.toSeconds(1)); //seconds
		return hms;
	}
	private void Addtoframe() { //add components to frame
		//Basics
		fr.add(Title);
		fr.add(time);
		
		//Labels
		fr.add(Newt);
		fr.add(I_odd);
		fr.add(I_square);
		fr.add(Rand);
		fr.add(Rand_limit);
		fr.add(Iterations);
		
		//Result place-holders
		fr.add(Newt_result);
		fr.add(I_odd_result);
		fr.add(I_square_result);
		fr.add(Rand_result);
		
		//Text Boxes
		fr.add(iterations);
		fr.add(limit);
		
		//Buttons
		fr.add(Calculate);
		fr.add(clear);
		fr.add(exit);
	}

	private void setProp() { //Set label and textbox properties
		//basic
		Title.setBounds(150, 0, 250, 20);
		time.setBounds(185, 250, 125, 30);
		//labels
		Newt.setBounds(30, 30, 150, 20);
		I_odd.setBounds(30, 50, 175, 20);
		I_square.setBounds(30, 70, 175, 20);
		Rand.setBounds(30, 90, 200, 20);
		Rand_limit.setBounds(30, 110, 200, 20);
		Iterations.setBounds(30, 130, 200, 20);
		//Result place holders
		Newt_result.setBounds(245, 30, 250, 20);
		I_odd_result.setBounds(245, 50, 250, 20);
		I_square_result.setBounds(245, 70, 250, 20);
		Rand_result.setBounds(245, 90, 250, 20);
		//Text boxes
		limit.setBounds(245, 110, 100, 20);
		limit.setToolTipText("The higher the limit the longer the time taken to find Pi but the more accurate the value.");
		iterations.setBounds(245, 130, 100, 20);
		iterations.setToolTipText("Used in random, square series and odd series.");
	}
	
}

public class Main {
	public static void main(String[] args) {
		new Piframe();
	}

}
