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
        this.loadProgress = new MediaTracker();

        // 5. 
        for( arrayElement : this.images)
        {

            String filename = arrayElement.getSource();
            Image image = getToolKit().getImage(fileName);

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


    public void run()
    {
        loadProgress.waitForID();

		// Get a reference to the current thread.
		Thread thisThread = Thread.currentThread();

		// Continue to loop while the thread has not been stopped.
		while (animator == thisThread) 
		{
			try 
			{
				// Sleep for the required delay between frames.
				Thread.sleep(delay);

				// If the thread is paused and has not been stopped, wait.
				synchronized(this) 
				{
					while (threadPaused && animator == thisThread)
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
		}

    }

    protected void paintComponent(Graphics g)
    {
        super();

        if(images != null)
        {
            drawImage();
        }
    }

    synchronized void stopAnimation()
    {
        this.backgroundAnimationThread = null;
        notify();
        this.currentAnimationFrame = 0;
    }
      
    synchronized void resumeAnimation()
    {
        this.paused = false;
        notify();
    }
      
    synchronized void pauseAnimation()
    {
        this.paused = true;
    }

    void startAnimation()
    {
        this.currentAnimationFrame = 0;
        this.paused = false; 
        new Thread(new RunnableClass()).start();
    }

}
