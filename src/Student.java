
// Student class to manage student details
public class Student {
    private String studentId;
    private String studentName;
    private double mark;

    // Constructor to initialize student details
    public Student(String studentId, String studentName, double mark) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.mark = mark;
    }

    // Getter and Setter methods for student attributes
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getMark() {
        return this.mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
    
    // Display student information along with ranking
    public void infoStudent() {
        System.out.println(this.studentId + " - " + this.studentName + " - " + this.mark + " - " + getRanking());
    }

    // Determine ranking based on the mark
    public String getRanking() {
        if (this.mark > 0 && this.mark < 5.0) {
            return "Fail";
        } else if (this.mark >= 5.0 && this.mark < 6.5) {
            return "Medium";
        } else if (this.mark >= 6.5 && this.mark < 7.5) {
            return "Good";
        } else if (this.mark >= 7.5 && this.mark < 9.0) {
            return "Very Good";
        } else if (this.mark >= 9.0 && this.mark <= 10.0) {
            return "Excellent";
        } else {
            return "Enter invalid mark";
        }
    }
    
}





	
