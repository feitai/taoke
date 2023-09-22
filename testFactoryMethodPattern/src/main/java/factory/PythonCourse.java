package factory;

public class PythonCourse implements ICourse{
    @Override
    public void record() {
        System.out.println("record Python 课程");
    }
}
