/************************************************************
 *                                                          *
 *  CSCI 470-1   Assignment 7                   Spring 2019 *
 *                                                          *
 *  Programmer:  Samuel Piecz                               *  
 *               z1732715                                   *
 *                                                          * 
 *                                                          *   
 *                                                          *
 *  Date Due:    4/21/19                                    *
 *                                                          *
 ************************************************************/ 

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;
import java.awt.GridLayout;

public class AnimationApp extends JFrame
{
    // Data members
    private JPanel mainPanel = new JPanel(new GridLayout(6, 2, 5, 5));
    private JButton startButton, pauseResumeButton, stopButton;

    // Methods 
    public static void main(String[] args)
    {
        AnimationApp animationApp = new AnimationApp();
        animationApp.createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initComponents()
    {
        mainPanel.add(new JLabel("Bill Amount"));

        add(mainPanel);
    }


    @Override
	public void actionPerformed(ActionEvent event)
	{ 

		String input = event.getActionCommand();
			
		if(input.equals("Start"))
		{
            startAnimation();
        }
        else if (input.equals("Stop"))
        {
            stopAnimation();
        }
        else if (input.equals("Pause"))
        {
             pauseAnimation();   
        }
        else if (input.equals("Resume"))
        {
             resumeAnimation();   
        }

	} 

    public void valueChanged(ListSelectionEvent event)
    {
        String input = event.getActionCommand();
        stopAnimation();
        loadAnimation(event.getActionCommand());
    }

}
