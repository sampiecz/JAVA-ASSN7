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
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import java.io.File;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import java.util.Collections;
import java.util.Comparator;


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

            Animation animation = new Animation(subStrings[0], Integer.parseInt(subStrings[1]), Integer.parseInt(subStrings[2]), Integer.parseInt(subStrings[3]), Integer.parseInt(subStrings[4]));
            this.animationVector.addElement(animation);
        }

        Collections.sort(this.animationVector,
		new Comparator<Animation>() {
			@Override
			public int compare(Animation a1, Animation a2) {
				return (a1.getName().compareTo(a2.getName()));
			}           
		});
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

        leftPanel.add(list);

        AnimationPanel animationPanel = new AnimationPanel();
        rightPanel.add(animationPanel);

        bottomPanel.add(this.startButton);
		bottomPanel.add(this.pauseResumeButton);
		bottomPanel.add(this.stopButton);

        mainPanel.add(rightPanel, "East");
        mainPanel.add(leftPanel, "West");
        mainPanel.add(bottomPanel, "South");

        add(mainPanel);
    }

	public void actionPerformed(ActionEvent event)
	{ 
		String input = event.getActionCommand();
			
		if(input.equals("Start"))
		{

			for(int i = 0; i < this.animationVector.length(); i++)
			{
				if(this.animationVector.get(i).getName() == this.list.getSelectedValue())
				{
					this.animationVector.get(i).startAnimation();
				}
			}

			this.startButton.setEnabled(false);
			this.stopButton.setEnabled(true);
			this.pauseResumeButton.setEnabled(true);
        }
        else if (input.equals("Stop"))
        {
			for(int i = 0; i < this.animationVector.length(); i++)
			{
				if(this.animationVector.get(i).getName() == this.list.getSelectedValue())
				{
					this.animationVector.get(i).stopAnimation();
				}
			}



        }
        else if (input.equals("Pause"))
        {
			for(int i = 0; i < this.animationVector.length(); i++)
			{
				if(this.animationVector.get(i).getName() == this.list.getSelectedValue())
				{
					this.animationVector.get(i).pauseAnimation();
				}
			}
			this.pauseResumeButton.setText("Resume"); 
		}
        else if (input.equals("Resume"))
        {
			for(int i = 0; i < this.animationVector.length(); i++)
			{
				if(this.animationVector.get(i).getName() == this.list.getSelectedValue())
				{
					this.animationVector.get(i).resumeAnimation();
				}
			}
			 this.pauseResumeButton.setText("Pause"); 
        }
	} 

    public void valueChanged(ListSelectionEvent event)
    {
		for(int i = 0; i < this.animationVector.length(); i++)
		{
			if(this.animationVector.get(i).getName() == this.list.getSelectedValue())
			{
				this.animationVector.get(i).stopAnimation();
				this.animationVector.get(i).loadAnimation(event.getActionCommand());
			}
		}
    }

}
