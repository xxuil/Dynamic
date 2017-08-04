package lab3;


import java.util.*;

public class Program3 implements IProgram3 {

    private int numClasses;
    private int maxGrade;
    GradeFunction gf;
    private int[][] grades;
    private int[][] hours;
    private HashMap<Integer, int[]> hourMap;
    private HashMap<Integer, int[]> gradeMap;

    public Program3() {
        this.numClasses = 0;
        this.maxGrade = 0;
        this.gf = null;
        this.grades = null;
        this.hours = null;
        this.hourMap = new HashMap<Integer, int[]>();
        this.gradeMap = new HashMap<Integer, int[]>();
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
    }
    
    public int[] computeHours(int totalHours) {
        if(hourMap.get(totalHours) == null)
            solution(totalHours);

        return hourMap.get(totalHours);
    }

    public int[] computeGrades(int totalHours) {
        return gradeMap.get(totalHours);
    }

    private void solution(int totalHours){
        grades = new int[totalHours + 1][numClasses + 1];
        hours = new int[totalHours + 1][numClasses + 1];

        if(totalHours == 0 || numClasses == 0)
            return;

        for(int i = 1; i <= numClasses; i++){
            //Two for loops
            for(int h = 0; h <= totalHours; h++){
                //find hour that maximizes grade
                int maxGrade = 0;
                int hour = 0;

                for(int j = 0; j <= h; j++){
                    int grade = grades[h - j][i - 1] + gf.grade(i - 1, j);

                    if(grade > maxGrade) {
                        maxGrade = grade;
                        hour = j;
                    }
                }

                if(maxGrade > grades[h][i - 1]){
                    grades[h][i] = maxGrade;
                    hours[h][i] = hour;
                }

                else{
                    grades[h][i] = grades[h][i - 1];
                    hours[h][i] = -1;
                }
            }
        }

        int[] hourList = new int[numClasses];

        int h = totalHours;
        int i = numClasses;

        while (i >= 0) {
            if (hours[h][i] >= 0) {
                break;
            }
            i--;
        }

        do {
            hourList[i-1] = hours[h][i];
            h = h - hours[h][i];
            i--;
        } while (i > 0 && hours[h][i] >= 0);

        hourMap.put(totalHours, hourList);

        int[] gradeList = new int[numClasses];

        for(int k = 0; k < numClasses; k++) {
            gradeList[k] = gf.grade(k, hourList[k]);
        }

        gradeMap.put(totalHours, gradeList);
    }

}
