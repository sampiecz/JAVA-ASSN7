JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

CLASSES = \
Animation.java\
AnimationPanel.java\
AnimationApp.java

default: classes

classes: $(CLASSES:.java=.class)

clean: ; $(RM) *.class
