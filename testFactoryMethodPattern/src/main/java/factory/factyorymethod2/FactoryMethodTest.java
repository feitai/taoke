package factory.factyorymethod2;

import factory.ICourse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory factory = new PythonCourseFactory();
        ICourse course = factory.create();
        course.record();

        factory = new JavaCourseFactory();
        course =factory.create();
        course.record();



        List list = new ArrayList();
        List list1 = new LinkedList();
        list1.iterator();
    }
}
