package bg.sofia.uni.fmi.mjt.udemy.course;

import bg.sofia.uni.fmi.mjt.udemy.course.duration.ResourceDuration;

import java.util.Arrays;
import java.util.Objects;

public class Resource implements Completable{

    private String name;
    private ResourceDuration duration;
    private boolean isCompleted;
    private int completionPercent;
    public Resource(String name, ResourceDuration duration) {
        this.name = name;
        this.duration = duration;
        this.completionPercent = 0;
    }

    /**
     * Returns the resource name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total duration of the resource.
     */
    public ResourceDuration getDuration() {
        return duration;
    }

    /**
     * Marks the resource as completed.
     */
    public void complete() {
        isCompleted = true;
        completionPercent = 100;
    }

    @Override
    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public int getCompletionPercentage() {
        return completionPercent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return isCompleted == resource.isCompleted && completionPercent == resource.completionPercent && Objects.equals(name, resource.name) && Objects.equals(duration, resource.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, isCompleted, completionPercent);
    }
}
