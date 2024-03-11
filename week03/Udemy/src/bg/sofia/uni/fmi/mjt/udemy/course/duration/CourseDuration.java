package bg.sofia.uni.fmi.mjt.udemy.course.duration;

import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
public record CourseDuration(int hours, int minutes) {
    public static CourseDuration of(Resource[] content) {
        int totalMinutes = 0;
        for (Resource con : content) {
            totalMinutes += con.getDuration().minutes();
        }

        if (totalMinutes > 24*60) {
            throw new IllegalArgumentException("Course duration must be under 24 hours");
        }

        return new CourseDuration(totalMinutes/60, totalMinutes%60);
    }
}
