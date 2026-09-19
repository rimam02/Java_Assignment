package marks;

import java.util.ArrayList;

public class StudentMarksModel {

    private ArrayList<StudentMarks> students;

    public StudentMarksModel() {
        students = new ArrayList<>();
    }

    public void addStudent(StudentMarks student) {
        students.add(student);
    }

    public StudentMarks getStudent(int index) {
        return students.get(index);
    }

    public void updateStudent(
            int index,
            int rollNo,
            String name,
            double marks) {

        StudentMarks student = students.get(index);

        student.setRollNo(rollNo);
        student.setName(name);
        student.setMarks(marks);
    }

    public void deleteStudent(int index) {
        students.remove(index);
    }

    public ArrayList<StudentMarks> getStudents() {
        return students;
    }

    public double calculateAverage() {

        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (StudentMarks student : students) {
            total += student.getMarks();
        }

        return total / students.size();
    }
}
