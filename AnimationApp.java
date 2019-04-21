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
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;

public class AnimationApp extends JFrame
{
    // Data members
    private JPanel mainPanel = new JPanel();
    private JButton startButton, pauseResumeButton, stopButton;
	private Vector<Animation> animationVector = new Vector<Animation>();
    private String fileName = "Animations/animations.txt";
    private JList list;

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


	public void readAnimations() throws IOException
	{
        Scanner fileScan = new Scanner(new File(this.fileName));

		String animationRecord;

		String[] subStrings;

        while (fileScan.hasNext())
        {
            animationRecord = fileScan.nextLine();
            
            subStrings = animationRecord.split(":", 5);

            animation = new Animation();

            animation.setName(subStrings[0]);
            animation.setWidth(Integer.parseInt(subStrings[1]));
            animation.setHeight(Integer.parseInt(subStrings[2]));
            animation.setNumberOfFrames(Integer.parseInt(subStrings[3]));
            animation.setMillisecondsBetweenFrames(Integer.parseInt(subStrings[4]));

            this.animationVector.addElement(animation);
        }

        Collections.sort(this.animationVector); 
	}


    private void initComponents()
    {
        mainPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        DefaultListModel model = new DefaultListModel();
        this.animationVector.forEach((animation) -> model.addElement(animation));

        this.list = new JList(model);

        leftPanel.add(list)

        AnimationPanel animationPanel = new AnimationPanel()
        rightPanel.add(animationPanel);

        bottomPanel.add(this.startButton, this.pauseResumeButton, this.stopButton);

        mainPanel.add(rightPanel, "East");
        mainPanel.add(leftPanel, "West");
        mainPanel.add(bottomPanel, "South");

        add(mainPanel);
    }


    @Override
	public void actionPerformed(ActionEvent event)
	{ 
		String input = event.getActionCommand();
			
		if(input.equals("Start"))
		{
            this.list.getSelectedValue().startAnimation();
        }
        else if (input.equals("Stop"))
        {
            this.list.getSelectedValue().stopAnimation();
        }
        else if (input.equals("Pause"))
        {
             this.list.getSelectedValue().pauseAnimation();   
        }
        else if (input.equals("Resume"))
        {
             this.list.getSelectedValue().resumeAnimation();   
        }
	} 

    public void valueChanged(ListSelectionEvent event)
    {
        String input = event.getActionCommand();
        stopAnimation();
        loadAnimation(event.getActionCommand());
    }

}
