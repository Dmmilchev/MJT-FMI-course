package bg.sofia.uni.fmi.mjt.udemy.course;

import bg.sofia.uni.fmi.mjt.udemy.course.duration.CourseDuration;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotCompletedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.ResourceNotFoundException;

import java.util.Arrays;

public class Course implements Purchasable, Completable {
    private final String name;
    private final String description;
    private double price;
    private final Resource[] content;
    private final Category category;
    private final CourseDuration totalTime;
    private boolean isPurchased;
    private double grade;

    public Course(String name, String description, double price, Resource[] content, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.content = content;
        this.category = category;
        totalTime = CourseDuration.of(content);
        this.isPurchased = false;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the course.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the category of the course.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the content of the course.
     */
    public Resource[] getContent() {
        return content;
    }

    /**
     * Returns the total duration of the course.
     */
    public CourseDuration getTotalTime() {
        return totalTime;
    }

    /**
     * Completes a resource from the course.
     *
     * @param resourceToComplete the resource which will be completed.
     * @throws IllegalArgumentException if resourceToComplete is null.
     * @throws ResourceNotFoundException if the resource could not be found in the course.
     */
    public void completeResource(Resource resourceToComplete) throws ResourceNotFoundException {
        if (resourceToComplete == null) {
            throw new IllegalArgumentException("resource can't be null");
        }

        boolean found = false;
        for (Resource con : content) {
            if (con.equals(resourceToComplete)) {
                found = true;
                con.complete();
            }
        }

        if (!found) {
            throw new ResourceNotFoundException("resource is not in content");
        }
    }

    @Override
    public boolean isCompleted() {
        for (Resource con : content) {
            if (!con.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCompletionPercentage() {
        int completionPercentage = 0;

        for (Resource con : content) {
            completionPercentage += con.getCompletionPercentage();
        }

        return completionPercentage / content.length;
    }

    @Override
    public void purchase() {
        isPurchased = true;
    }

    @Override
    public boolean isPurchased() {
        return isPurchased;
    }

    public double getGrade() throws CourseNotCompletedException {
        if (grade == 0) {
            throw new CourseNotCompletedException("You should complete the course before assigning a grade");
        }
        return grade;
    }

    public void complete(double grade) throws CourseNotCompletedException, IllegalArgumentException {
        if (grade < 2 || grade > 6) {
            throw new IllegalArgumentException("Grade must be between 2 and 6");
        }
        if (getCompletionPercentage() < 100) {
            throw new CourseNotCompletedException("You should first complete all resources");
        }

        this.grade = grade;
    }
}
