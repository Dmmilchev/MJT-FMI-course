package bg.sofia.uni.fmi.mjt.udemy.account;

import java.util.Arrays;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
import bg.sofia.uni.fmi.mjt.udemy.exception.*;

public abstract class AccountBase implements Account{
    private static final int MAX_COURSE_COUNT = 100;
    private final String username;
    private double balance;
    private int courseCount;
    private Course[] courses;


    public AccountBase(String username, double balance) {
        this.balance = balance;
        this.username = username;
        courses = new Course[MAX_COURSE_COUNT];
    }


    /**
     * Returns the username of the account.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Adds the given amount of money to the account's balance.
     *
     * @param amount the amount of money which will be added to the account's balance.
     * @throws IllegalArgumentException if amount has a negative value.
     */
    @Override
    public void addToBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must have a non-negative value");
        }
        balance += amount;
    }

    /**
     * Returns the balance of the account.
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Buys the given course for the account.
     *
     * @param course the course which will be bought.
     * @throws IllegalArgumentException          if the account buyer is of type BusinessAccount and course has category which is not among the permitted for this account
     * @throws InsufficientBalanceException      if the account does not have enough funds in its balance.
     * @throws CourseAlreadyPurchasedException   if the course is already purchased for this account.
     * @throws MaxCourseCapacityReachedException if the account has reached the maximum allowed course capacity.
     */
    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {
        if (balance < course.getPrice()) {
            throw new InsufficientBalanceException("Balance is less than the price of the course");
        }
        if (course.isPurchased()) {
            throw new CourseAlreadyPurchasedException("Course is already bought");
        }
        if (courseCount >= MAX_COURSE_COUNT) {
            throw new MaxCourseCapacityReachedException("Course max capacity is reached");
        }

        courses[courseCount] = course;
        courseCount++;
        course.purchase();
        balance -= course.getPrice();
    }

    private void completeOneResourceFromCourse(Course course,Resource resourceToComplete) throws ResourceNotFoundException{
        if (!Arrays.asList(course.getContent()).contains(resourceToComplete)) {
            throw new ResourceNotFoundException("Resource is not in course's content");
        }

        resourceToComplete.complete();
    }
    /**
     * Completes the given resources that belong to the given course provided that the course was previously purchased by this account.
     *
     * @param course              the course in which the resources will be completed.
     * @param resourcesToComplete the resources which will be completed.
     * @throws IllegalArgumentException    if course or resourcesToComplete are null.
     * @throws CourseNotPurchasedException if course is not currently purchased for this account.
     * @throws ResourceNotFoundException   if a certain resource could not be found in the course.
     */
    @Override
    public void completeResourcesFromCourse(Course course, Resource[] resourcesToComplete) throws CourseNotPurchasedException, ResourceNotFoundException {
        if (course == null || resourcesToComplete == null) {
            throw new IllegalArgumentException("Course or resources is null");
        }
        if (!Arrays.asList(courses).contains(course)) {
            throw new CourseNotPurchasedException("You have to first buy the course");
        }
        for (Resource resource : resourcesToComplete) {
            completeOneResourceFromCourse(course, resource);
        }
    }

    /**
     * Completes the whole course.
     *
     * @param course the course which will be completed.
     * @param grade  the grade with which the course will be completed.
     * @throws IllegalArgumentException    if grade is not in range [2.00, 6.00] or course is null.
     * @throws CourseNotPurchasedException if course is not currently purchased for this account.
     * @throws CourseNotCompletedException if there is a resource in the course which is not completed.
     */
    @Override
    public void completeCourse(Course course, double grade) throws CourseNotPurchasedException, CourseNotCompletedException {
        if (course == null) {
            throw new IllegalArgumentException("Course should not be null");
        }
        if (grade < 2 || grade > 6) {
            throw new IllegalArgumentException("Grade must be between 2 and 6");
        }
        if (!Arrays.asList(courses).contains(course)) {
            throw new CourseNotPurchasedException("You should first buy the course");
        }

        course.complete(grade);
    }

    /**
     * Gets the course with the least completion percentage.
     * Returns null if the account does not have any purchased courses.
     */
    @Override
    public Course getLeastCompletedCourse() {
        if(courseCount == 0) {
            return null;
        }

        int localChamp = courses[0].getCompletionPercentage();
        Course result = courses[0];
        for (int i=1; i<courseCount; ++i) {
            if (courses[i].getCompletionPercentage() < localChamp) {
                result = courses[i];
                localChamp = result.getCompletionPercentage();
            }
        }
        return result;
    }

    public boolean equals(AccountBase other) {
        if (other == null) {
            return false;
        }
        return username.equals(other.username);
    }

    public int hashCode() {
        return username.hashCode();
    }
}
