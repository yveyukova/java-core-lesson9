public class DefaultCourse implements Course{
    private String name;
    public DefaultCourse(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
