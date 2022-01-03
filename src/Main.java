import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Course javaCourse = new DefaultCourse("Java");
        Course htmlCourse = new DefaultCourse("HTML");
        Course jsCourse = new DefaultCourse("JavaScript");
        Course pythonCourse = new DefaultCourse("Python");
        Course cppCourse = new DefaultCourse("C++");
        Course rubyCourse = new DefaultCourse("Ruby");

        List<Course> courses1 = new ArrayList<Course>();
        courses1.add(javaCourse);
        courses1.add(htmlCourse);
        courses1.add(jsCourse);
        Student student1 = new DefaultStudent("Иван", courses1);

        List<Course> courses2 = new ArrayList<Course>();
        courses2.add(javaCourse);
        courses2.add(pythonCourse);
        courses2.add(cppCourse);
        Student student2 = new DefaultStudent("Петр", courses2);

        List<Course> courses3 = new ArrayList<Course>();
        courses3.add(javaCourse);
        Student student3 = new DefaultStudent("Анна", courses3);

        List<Course> courses4 = new ArrayList<Course>();
        courses4.add(cppCourse);
        courses4.add(htmlCourse);
        courses4.add(jsCourse);
        courses4.add(pythonCourse);
        Student student4 = new DefaultStudent("Ксения", courses4);

        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        System.out.println("Список студентов:");
        for (Student student : students) {
            System.out.println(student.getName());
            for (Course course : student.getAllCourses()) {
                System.out.println("..." + course.getName());
            }
        }
        System.out.println("====================================");
        System.out.println("Список уникальных курсов:");
        for (Course course : getCourses(students)) {
            System.out.println("..." + course.getName());
        }
        System.out.println("====================================");
        System.out.println("Список из трех самых любознательных:");
        for (Student student : getTopStudent(students)) {
            System.out.println(student.getName());
        }
        System.out.println("Список студентов, посещающих курс JAVA:");
        for (Student student : getStudentsByCourse(students, javaCourse)) {
            System.out.println(student.getName());
        }
    }

    public static List<Course> getCourses(List<Student> students) {
        return students.stream()
                .filter(Objects::nonNull)
                .map(Student::getAllCourses)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Student> getTopStudent(List<Student> students) {
        return students.stream()
                .sorted((s1, s2) -> {
                    List<Course> c1 = s1.getAllCourses();
                    List<Course> c2 = s2.getAllCourses();
                    return Integer.compare(
                            c2.size(),
                            c1.size()
                    );
                })
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentsByCourse(List<Student> students, Course course) {
        return students.stream()
                .filter(Objects::nonNull)
                .filter(student -> {
                    List<Course> courses = student.getAllCourses();
                    return courses.contains(course);
                })
                .collect(Collectors.toList());
    }
}


