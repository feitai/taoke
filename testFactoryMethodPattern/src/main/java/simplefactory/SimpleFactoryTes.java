package simplefactory;

import factory.ICourse;
import factory.JavaCourse;

public class SimpleFactoryTes {
    public static void main(String[] args) {
        ICourse iCourse = new CourseFactory().create("java");
        System.out.println(iCourse);


         iCourse = new CourseFactory().create(JavaCourse.class);
        System.out.println(iCourse);

    }

}
