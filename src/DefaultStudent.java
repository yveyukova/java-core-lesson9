import java.util.List;

public class DefaultStudent implements  Student{
    private String name;
    private List<Course> courses;
    public DefaultStudent(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
