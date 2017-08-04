package lab3;

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
        if(hours < 2)
            return 50;

        if(hours > 10)
            return 100;

        else return 70;
    }
}
