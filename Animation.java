public class Animation 
{

    // data members
    private String name;
    private int width, height, numberOfFrames, millisecondsBetweenFrames;

    // constructor
    public Animation(
        String name, 
        int width, 
        int height,
        int numberOfFrames,
        int millisecondsBetweenFrames
    )
    {
        this.name = name;
        this.width = width;
        this.height = height;
        this.numberOfFrames = numberOfFrames;
        this.millisecondsBetweenFrames = millisecondsBetweenFrames;
    }

    // getter methods
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

    // setter methods
    public void setName(String name)
    {
        this.name = name;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setNumberOfFrames(int numberOfFrames)
    {
        this.numberOfFrames = numberOfFrames;
    }

    public void setMillisecondsBetweenFrames(int millisecondsBetweenFrames)
    {
        this.millisecondsBetweenFrames = millisecondsBetweenFrames;
    }

    @Override
    public String toString()
    {
        return this.name;
    }	

}
