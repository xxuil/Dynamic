/* EE 360c Lab 3
 * Name: Xiangxing Liu
 * EID: xl5587
 */

public class MyGradeFunction implements GradeFunction {
    private int numClasses;
    private int maxGrade;

    public MyGradeFunction(int n, int g){
        this.numClasses = n;
        this.maxGrade = g;
    }

    /*
     * This grade function gives diminishing returns as
     * you put in more hours
     */
    public int grade(int classID, int hours){
        if(hours == 0)
            return 0;

        else if(hours > 0 && hours < 100)
            return maxGrade - hours % maxGrade;

        else return 0;
    }
}
