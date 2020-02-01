package top.inewbie.pojo;

import java.util.List;

public class AllCourses {
    List<Course> unSelectedCourseList ;
    List<Course> selectedCourseList ;

    public AllCourses(List<Course> unSelectedCourseList, List<Course> selectedCourseList) {
        this.unSelectedCourseList = unSelectedCourseList;
        this.selectedCourseList = selectedCourseList;
    }

    public List<Course> getUnSelectedCourseList() {
        return unSelectedCourseList;
    }

    public void setUnSelectedCourseList(List<Course> unSelectedCourseList) {
        this.unSelectedCourseList = unSelectedCourseList;
    }

    public List<Course> getSelectedCourseList() {
        return selectedCourseList;
    }

    public void setSelectedCourseList(List<Course> selectedCourseList) {
        this.selectedCourseList = selectedCourseList;
    }
}
