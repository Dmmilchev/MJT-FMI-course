package bg.sofia.uni.fmi.mjt.udemy;

import bg.sofia.uni.fmi.mjt.udemy.account.Account;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotFoundException;
import bg.sofia.uni.fmi.mjt.udemy.exception.AccountNotFoundException;
public class Udemy implements LearningPlatform{
    private static final int MAX_ACCOUNT_COUNT = 100;
    private static final int MAX_COURSE_COUNT = 100;

    private Account[] accounts;
    private int accountCount;
    private Course[] courses;
    private int courseCount;

    //In my opinion it is better for accounts and courses variables not to be final and
    //courseCount and accountCount to exist, because
    //this way code can be extended and you can easily add functions that
    //add accounts and courses which is intuitive for a task like this.
    public Udemy(Account[] accounts, Course[] courses) throws IllegalArgumentException {
        this.accountCount = accounts.length;
        this.accounts = accounts;
        this.courseCount = courses.length;
        this.courses = courses;

        if (accountCount > MAX_ACCOUNT_COUNT ||
            courseCount > MAX_COURSE_COUNT) {
            throw new IllegalArgumentException("Accounts and courses must be at maximum a hundred");
        }
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getCourseCount() {
        return courseCount;
    }

    /**
     * Returns the course with the given name.
     *
     * @param name the exact name of the course.
     * @throws IllegalArgumentException if name is null or blank.
     * @throws CourseNotFoundException  if course with the specified name is not present in the platform.
     */
    @Override
    public Course findByName(String name) throws CourseNotFoundException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name could not be null");
        }

        Course result = null;
        boolean found = false;
        for (int i=0; i<courseCount; ++i) {
            if(name.equals(courses[i].getName())) {
                result = courses[i];
                found = true;
                break;
            }
        }

        if (!found) {
            throw new CourseNotFoundException("Course is not in database");
        }

        return result;
    }

    private int findCourseByKeywordCount(String keyword) {
        int count = 0;
        for (int i=0; i<courseCount; ++i) {
            if(courses[i].getName().contains(keyword)) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Returns all courses which name or description containing keyword.
     * A keyword is a word that consists of only small and capital latin letters.
     *
     * @param keyword the exact keyword for which we will search.
     * @throws IllegalArgumentException if keyword is null, blank or not a keyword.
     */
    @Override
    public Course[] findByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword can't be null");
        }
        if (!keyword.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Keyword must contain only letters");
        }

        Course[] result = new Course[findCourseByKeywordCount(keyword)];
        int resultElementCount = 0;
        for (int i=0; i<courseCount; ++i) {
            if (courses[i].getName().contains(keyword)) {
                result[resultElementCount] = courses[i];
                resultElementCount++;
            }
        }

        return result;
    }

    private int getCountOfCoursesByCategory(Category category) {
        int count = 0;
        for (int i=0; i<courseCount; ++i) {
            if (courses[i].getCategory().equals(category)) {
                ++count;
            }
        }
        return count;
    }
    /**
     * Returns all courses from a given category.
     *
     * @param category the exact category the courses for which we want to get.
     * @throws IllegalArgumentException if category is null.
     */
    @Override
    public Course[] getAllCoursesByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        Course[] result = new Course[getCountOfCoursesByCategory(category)];
        int resultElementCount = 0;

        for (int i=0; i<courseCount; ++i) {
            if (courses[i].getCategory().equals(category)) {
                result[resultElementCount] = courses[i];
                resultElementCount++;
            }
        }

        return result;
    }

    /**
     * Returns the account with the given name.
     *
     * @param name the exact name of the account.
     * @throws IllegalArgumentException if name is null or blank.
     * @throws AccountNotFoundException if account with such a name does not exist in the platform.
     */
    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        for (int i=0; i<accountCount; ++i) {
            if (accounts[i].getUsername().equals(name)) {
                return accounts[i];
            }
        }

        throw new AccountNotFoundException("Could not find account with this name in database");
    }

    /**
     * Returns the course with the longest duration in the platform.
     * Returns null if the platform has no courses.
     */
    @Override
    public Course getLongestCourse() {
        if (courseCount == 0) {
            return null;
        }

        int localChamp = courses[0].getTotalTime().hours()*60 +
                        courses[0].getTotalTime().minutes();
        Course result = courses[0];

        for (int i=1; i<courseCount; ++i) {
            int curr = courses[i].getTotalTime().hours()*60 +
                    courses[i].getTotalTime().minutes();
            if (localChamp < curr) {
                localChamp = curr;
                result = courses[i];
            }
        }

        return result;
    }

    /**
     * Returns the cheapest course from the given category.
     * Returns null if the platform has no courses.
     *
     * @param category the exact category for which we want to find the cheapest course.
     * @throws IllegalArgumentException if category is null.
     */
    @Override
    public Course getCheapestByCategory(Category category) {
        if (courseCount == 0) {
            return null;
        }

        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        double localChamp = courses[0].getPrice();
        Course result = courses[0];

        for (int i=1; i<courseCount; ++i) {
            if (localChamp < courses[i].getPrice()) {
                localChamp = courses[i].getPrice();
                result = courses[i];
            }
        }

        return result;
    }
}
