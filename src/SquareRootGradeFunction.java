/* EE 360c Lab 3
 * Name: Xiangxing Liu
 * EID: xl5587
 */


public class SquareRootGradeFunction implements GradeFunction {

    private int numClasses;
    private int maxGrade;

    public SquareRootGradeFunction(int n, int g){
	    this.numClasses = n;
	    this.maxGrade = g;
    }

    /*
     * This grade function gives diminishing returns as
     * you put in more hours
     */
    public int grade(int classID, int hours){
    	int root = (int) Math.round(Math.sqrt(hours));

    	return Math.min(root,maxGrade);
    }

}
