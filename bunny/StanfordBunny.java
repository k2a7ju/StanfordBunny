package bunny;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.ArrayList;
import java.util.Iterator;

import mvc4jogl.OpenGLCompound;
import mvc4jogl.OpenGLModel;
import mvc4jogl.OpenGLObject;
import mvc4jogl.OpenGLProjection;
import mvc4jogl.OpenGLTriangle;

import utility.StringUtility;

public class StanfordBunny extends OpenGLCompound{
    public StanfordBunny(){
	super();
	String str = "./ply/StanfordBunny.ply";
	ArrayList<String> aCollection = StringUtility.readTextFromFile(str);
	int numberOfVertexes = 0;
	int numberOfFace = 0;
	Iterator anIterator = aCollection.iterator();
	int count = 35960;
	while (anIterator.hasNext()){
	    String aString = (String)anIterator.next();
	    ArrayList<String> aList = StringUtility.splitString(aString, " \t\n\r");
	    if (aList.size() == 0) { continue; }
	    String line = aList.get(0);
	    String text;
	    if (line.compareTo("element") == 0) {
		text = (String)aList.get(1);
		if(text.compareTo("vertex") == 0){
		    numberOfVertexes = Integer.valueOf((String)aList.get(2));
		    System.out.println(numberOfVertexes);
		}
		if(text.compareTo("face") == 0){
		    numberOfFace = Integer.valueOf((String)aList.get(2));
		    System.out.println(numberOfFace);
		}
	    }
	    
	    if (line.compareTo("end_header") == 0){
		double[][] vertexArray = new double[numberOfVertexes][3];
		for (int n = 0; n < numberOfVertexes; n++){
		    aString = (String)anIterator.next();
		    aList = StringUtility.splitString(aString, " \t\n\r");
		    double x = Double.valueOf(aList.get(0));
		    double y = Double.valueOf(aList.get(1));
		    double z = Double.valueOf(aList.get(2));
		    vertexArray[n][0] = x * 12 + 0.2;
		    vertexArray[n][1] = y * 12 - 0.8;
		    vertexArray[n][2] = z * 12;
		}
		for (int n = 0; n < numberOfFace; n++) {
		    aString = (String)anIterator.next();
		    aList = StringUtility.splitString(aString, " \t\n\r");
		    
		    int i = Integer.valueOf(aList.get(1));
		    int j = Integer.valueOf(aList.get(2));
		    int k = Integer.valueOf(aList.get(3));
		    double[] vertex1 = vertexArray[i];
		    double[] vertex2 = vertexArray[j];
		    double[] vertex3 = vertexArray[k];
		    OpenGLTriangle aTriangle = new OpenGLTriangle(vertex1, vertex2, vertex3);
		    aTriangle.rgb(1,0.8d ,0.8d);
		    this.add(aTriangle);
		    count++;
		}
	    }
	}
	
	return;
    }
    
    public static void main(String[] arguments){
        StanfordBunny.open(150, 150);
    }

    public static void open(int x, int y)
    {
	
	OpenGLObject aBunny = new StanfordBunny();
	OpenGLModel aModel = new OpenGLModel(aBunny);
	aModel.axesScale(1.0d);
	aModel.windowTitle("StanfordBunny");
	OpenGLProjection aProjection = aModel.projection();
	aProjection.eyePoint(-5.5852450791872d, 3.07847342734d, 15.794105252496d);
	aProjection.sightPoint(0.27455347776413d, 0.20096999406815d, -0.11261999607086d);
	aProjection.upVector(0.1018574904194d, 0.98480906061847d, -0.14062775604137d);
	aProjection.fovy(12.642721790235d);
	aModel.open(x, y);

	return;
    }
}
