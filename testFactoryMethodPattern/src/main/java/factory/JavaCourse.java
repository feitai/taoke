package factory;

public class JavaCourse implements ICourse{
    @Override
    public void record() {
        System.out.println("record  Java课程");
    }
}
