import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Scanner;
import java.io.IOException;

public class AnimationPanel extends JPanel implements Runnable
{

    // data members
    volatile Thread backgroundAnimationThread; 
    private boolean paused = false;
    int x, y, frameDelay;
    int currentAnimationFrame = 0;
    Image images[];
    MediaTracker loadProgress; 

    // methods
    void loadAnimation(Animation animation)
    {

        // 1.
        this.images = new Image[animation.getNumberOfFrames()]; 

        // 2.
        this.x = (this.x - animation.getWidth()) / 2;
        this.y = (this.y - animation.getHeight()) / 2; 

        // 3.
        this.frameDelay = animation.getMillisecondsBetweenFrames();

        // 4.
        this.loadProgress = new MediaTracker(this);

        // 5. 
        for ( Image arrayElement : this.images)
        {

            String fileName = String.join("_", arrayElement.getName());
            Image image = arrayElement.getToolKit().getImage("Animations/" + fileName);
            this.loadProgress.addImage(image, 1);

            try
            {
                this.loadProgress.waitForAll();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }

        // 6.
        repaint();

    }

    public void startAnimation()
    {
        this.currentAnimationFrame = 0;
        this.paused = false; 
        new Thread(new RunnableClass()).start();
    }

    synchronized void pauseAnimation()
    {
        this.paused = true;
    }

    synchronized void resumeAnimation()
    {
        this.paused = false;
        notify();
    }
    
    synchronized void stopAnimation()
    {
        this.backgroundAnimationThread = null;
        notify();
        this.currentAnimationFrame = 0;
    }

    protected void paintComponent(Graphics graphic)
    {
        super.paintComponent(graphic);

        if(this.images != null)
        {
            graphic.drawImage(this.images[this.currentAnimationFrame], this.x, this.y, this);
        }
    }

    public void run()
    {
        loadProgress.waitForID(0);

		// Get a reference to the current thread.
		Thread thisThread = Thread.currentThread();

		// Continue to loop while the thread has not been stopped.
		while (this.backgroundAnimationThread == thisThread) 
		{
			try 
			{
				// Sleep for the required delay between frames.
				Thread.sleep(this.frameDelay);

				// If the thread is paused and has not been stopped, wait.
				synchronized(this) 
				{
					while (this.paused && this.backgroundAnimationThread == thisThread)
					wait();
				}
			} 
			catch (InterruptedException e) 
			{
				// Thread was interrupted.
			}

			// Redraw the animation panel.
			repaint();

			// Increment the current frame index (not shown).
            if(this.currentAnimationFrame == this.images.length)
            {
                this.currentAnimationFrame = 0;
            }
            else
            {
                this.currentAnimationFrame++;
            }
		}

    }

}
