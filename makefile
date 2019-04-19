JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

CLASSES = \
MazeSquare.java\
MazePanel.java\
Maze.java\
MazeApp.java

default: classes

classes: $(CLASSES:.java=.class)

clean: ; $(RM) *.class
