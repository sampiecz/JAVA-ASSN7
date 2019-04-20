public class Animation 
{

    // data members
    private final String name;
    private final int width, height, numberOfFrames, millisecondsBetweenFrames;

    // constructor
    public Animation(String name, int width, int height, int numberOfFrames, int millisecondsBetweenFrames)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        this.numberOfFrames = numberOfFrames;
        this.millisecondsBetweenFrames = millisecondsBetweenFrames;
    }

    // accessor methods
    public String getName()
    {
        return this.name;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getNumberOfFrames()
    {
        return this.numberOfFrames;
    }

    public int getMillisecondsBetweenFrames()
    {
        return this.millisecondsBetweenFrames;
    }

}
