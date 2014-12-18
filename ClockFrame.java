import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClockFrame extends JFrame{

	public ClockFrame(){
		setTitle("Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));
        myPanel.add(new JTextField("::", 5));
        myPanel.add(new JButton("Set Clock"));
        add(myPanel, BorderLayout.SOUTH);
		add(new ClockPanel(myPanel), BorderLayout.CENTER);
		setVisible(true);
		setResizable(false);
		this.pack();
	}
}
