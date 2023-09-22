package simplefactory;

import factory.ICourse;
import factory.JavaCourse;
import factory.PythonCourse;

public class CourseFactory {
//    public ICourse create(String name) {
//        if ("java".equals(name)) {
//            return new JavaCourse();
//        } else if ("python".equals(name)) {
//            return new PythonCourse();
//        } else {
//            return null;
//        }
//    }




    public ICourse create( Class<? extends ICourse> clazz) {
        try {
            if(clazz != null) {
                return clazz.newInstance();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public ICourse create(String classname) {
     try {
         if(!(null!=classname) || classname.equals("")) {
             return (ICourse) Class.forName(classname).newInstance();
         }
     }catch (Exception e) {
         e.printStackTrace();
     }
     return null;
    }
}