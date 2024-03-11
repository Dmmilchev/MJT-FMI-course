import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
import bg.sofia.uni.fmi.mjt.udemy.course.duration.ResourceDuration;

public class Main {
    public static void main(String[] args) {
        ResourceDuration rd = new ResourceDuration(50);
        Resource r = new Resource("Deyan", rd);
        Resource r2 = new Resource("Deyan", rd);

        System.out.println(r.equals(r2));
        System.out.println(r.hashCode());
        System.out.println(r2.hashCode());
    }
}