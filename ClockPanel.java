import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClockPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int delay;
	private final int diameter;
	private final int center;
	private Point minuteEnd;
	private Point hourEnd;
	private Point secondEnd;
	private int minuteLength;
	private int secondLength;
	private int hourLength;
	private double minuteAngle;
	private double hourAngle;
	private double secondAngle;
    private JTextField jtf;
    private JButton jb;
    private int seconds, minutes, hours;

	public ClockPanel(JPanel pane){
        Component[] components = pane.getComponents();
        jtf = null;
        jb = null;
        for (int i = 0; i < components.length; i++){
            if (components[i] instanceof JTextField)
                jtf = (JTextField) components[i];
            else
                jb = (JButton) components[i];
        }
        System.out.println(jtf);
        System.out.println(jb);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		delay = 1000;
		diameter = 200;
		center = diameter / 2;
		secondEnd = new Point(center, 10);
		minuteEnd = new Point(center, 10);
		hourEnd = new Point(center, 40);
		reset();
		secondLength = minuteLength = 80;
		hourLength = 60;
		setPreferredSize(new Dimension(diameter, diameter));
		timer = new Timer(delay, this);
		timer.setInitialDelay(0);
		timer.start();
        jb.addActionListener(this);
	}

	public void paintComponent(Graphics pen){
		super.paintComponent(pen);
		pen.setColor(Color.BLACK);
		pen.drawOval(0, 0, diameter, diameter);
		pen.drawLine(center, 0, center, 5);
		pen.drawLine(center, 200, center, 195);
		pen.drawLine(0, center, 5, center);
		pen.drawLine(195, center, 200, center);
		pen.drawLine(center, center, minuteEnd.x, minuteEnd.y);
		pen.drawLine(center, center, hourEnd.x, hourEnd.y);
		pen.setColor(Color.RED);
		pen.drawLine(center, center, secondEnd.x, secondEnd.y);

	}
	public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source == timer) {
	        secondEnd.setLocation(center + Math.cos(secondAngle) * secondLength, center + Math.sin(secondAngle) * secondLength);
	        minuteEnd.setLocation(center + Math.cos(minuteAngle) * minuteLength, center + Math.sin(minuteAngle) * minuteLength);
	        hourEnd.setLocation(center + Math.cos(hourAngle) * hourLength, center + Math.sin(hourAngle) * hourLength);
	        repaint();
	    	secondAngle += Math.PI / 30;
		    minuteAngle += Math.PI / 1800;
		    hourAngle += Math.PI / 21600;
        }
        else { //source == jb
            setClock();
        }
	}
	public void reset(){
		secondAngle = minuteAngle = hourAngle = - Math.PI / 2;
        repaint();
	}
	public void setClock(){
        String[] strings = null;
        strings = jtf.getText().trim().split(":");
        setSecondHand(strings[2]);
        setMinuteHand(strings[1]);
        setHourHand(strings[0]);
        repaint();
	}
    private void setMinuteHand(String s){
        minutes = Integer.valueOf(s);
        if (minutes > 59 || minutes < 0)
            return;
        minuteAngle = (-Math.PI / 2) + (minutes * Math.PI / 30) + (seconds * Math.PI / 1800);
    }
    private void setHourHand(String s){
        hours = Integer.valueOf(s);
        if (hours > 12 || hours < 0)
            return;
        hourAngle = (-Math.PI / 2) + (hours * Math.PI / 6) + (minutes * Math.PI / 360) + (seconds * Math.PI / 21600);
    }
    private void setSecondHand(String s){
        seconds = Integer.valueOf(s);
        if (seconds > 59 || seconds < 0)
            return;
        secondAngle = (-Math.PI / 2) + (seconds * Math.PI / 30);
    }
}
