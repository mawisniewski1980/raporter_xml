public class TestCase {

    private String name;
    private String classname;
    private String time;

    public TestCase() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                ", name='" + name + '\'' +
                ", classname='" + classname + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}