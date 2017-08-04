package lab3;


import java.util.*;

public class Program3 implements IProgram3 {

    private int numClasses;
    private int maxGrade;
    GradeFunction gf;

    private int[][] grades;
    private int[][] hours;

    private int[] hourList;
    private int[] gradeList;

    public Program3() {
        this.numClasses = 0;
        this.maxGrade = 0;
        this.gf = null;
        this.grades = null;
        this.hours = null;
        this.hourList = null;
        this.gradeList = null;
    }
    /* Initialization Method
     * n = number of courses
     * g = max grade we can get for one class
     * gf =  grade function
     */
    public void initialize(int n, int g, GradeFunction gf) {
        this.numClasses = n;
        this.maxGrade = g;
        this.gf = gf;
        this.grades = null;
        this.hours = null;
        this.hourList = null;
        this.gradeList = null;
    }

    public int[] computeHours(int totalHours) {

        dynamicSolution(totalHours);

        hourList = new int[numClasses];

        int i = numClasses;
        int h = totalHours;
        int tempHour = hours[i][h];

        while(i > 0 && h >= 0){
            if(tempHour == -1)
                tempHour = 0;
            hourList[i - 1] = tempHour;
            i--;
            h = h - tempHour;
            tempHour = hours[i][h];
        }

        return hourList;
    }


    public int[] computeGrades(int totalHours) {
        gradeList = new int[numClasses];

        for(int i = 0; i < numClasses; i++){
            gradeList[i] = gf.grade(i, hourList[i]);
        }

        return gradeList;
    }


    private void dynamicSolution(int totalHours){
        grades = new int[numClasses + 1][totalHours + 1];
        hours = new int[numClasses + 1][totalHours + 1];

        if(totalHours == 0 || numClasses == 0)
            return;

        for(int i = 1; i <= numClasses; i++){
            //Two for loops
            for(int h = 0; h <= totalHours; h++){
                //find hour that maximizes grade
                int maxGrade = 0;
                int hour = 0;

                for(int j = 0; j <= h; j++){
                    int grade = grades[i - 1][h - j] + gf.grade(i - 1, j);

                    if(grade > maxGrade) {
                        maxGrade = grade;
                        hour = j;
                    }
                }

                if(maxGrade > grades[i - 1][h]){
                    grades[i][h] = maxGrade;
                    hours[i][h] = hour;
                }

                else{
                    grades[i][h] = grades[i - 1][h];
                    hours[i][h] = -1;
                }
            }
        }
    }
}