package bg.sofia.uni.fmi.mjt.gym.member;

import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;
import bg.sofia.uni.fmi.mjt.gym.workout.Workout;

import java.time.DayOfWeek;

import java.util.*;


public class Member implements GymMember, Comparable<Member>{
    private final Address address;
    private final String name;
    private final int age;
    private final String personalIdNumber;
    private final Gender gender;
    private Map<DayOfWeek, Workout> trainingProgram;

    public Member(Address address, String name, int age, String personalIdNumber, Gender gender) {
        this.address = address;
        this.name = name;
        this.age = age;
        this.personalIdNumber = personalIdNumber;
        this.gender = gender;
        trainingProgram = new HashMap<DayOfWeek, Workout>(7);
    }
    /**
     * Returns the member's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the member's age.
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * Returns the member's id number.
     */
    @Override
    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    /**
     * Returns the member's gender.
     */
    @Override
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the member's address.
     */
    @Override
    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable Map representing the workout a member does on the DayOfWeek.
     */
    @Override
    public Map<DayOfWeek, Workout> getTrainingProgram() {
        return Map.copyOf(trainingProgram);
    }

    /**
     * Sets the workout for a specific day.
     *
     * @param day     - DayOfWeek on which the workout will be trained
     * @param workout - the workout to be trained
     * @throws IllegalArgumentException if day or workout is null.
     */
    @Override
    public void setWorkout(DayOfWeek day, Workout workout) throws IllegalArgumentException{
        if (day == null) {
            throw new IllegalArgumentException("Day can't bd null");
        }
        if (workout == null) {
            throw new IllegalArgumentException("Workout can't be null");
        }

        trainingProgram.put(day, workout);
    }

    /**
     * Returns a collection of days in undefined order on which the workout finishes with a specific exercise.
     *
     * @param exerciseName - the name of the exercise.
     * @throws IllegalArgumentException if exerciseName is null or empty.
     */
    @Override
    public Collection<DayOfWeek> getDaysFinishingWith(String exerciseName) {
        EnumSet<DayOfWeek> daysThatFinishWith = EnumSet.noneOf(DayOfWeek.class);
        for (Map.Entry<DayOfWeek, Workout> entry : trainingProgram.entrySet()) {
            if (entry.getValue().exercises().getLast().name().equals(exerciseName)) {
                daysThatFinishWith.add(entry.getKey());
            }
        }
        return daysThatFinishWith;
    }

    /**
     * Adds an Exercise to the Workout trained on the given day. If there is no workout set for the day,
     * the day is considered a day off and no exercise can be added.
     *
     * @param day      - DayOfWeek to train the exercise.
     * @param exercise - the trained Exercise.
     * @throws DayOffException          if the Workout on this day is null.
     * @throws IllegalArgumentException if day or exercise is null
     */
    @Override
    public void addExercise(DayOfWeek day, Exercise exercise) {
        if(!trainingProgram.containsKey(day)) {
            throw new DayOffException("Today is a rest day");
        }
        if (day == null) {
            throw new IllegalArgumentException("Day can't bd null");
        }
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise can't be null");
        }

        trainingProgram.get(day).exercises().add(exercise);
    }

    /**
     * Adds Exercises to the Workout trained on the given day. If there is no workout set for the day, the day is
     * considered a day off and no exercise can be added.
     *
     * @param day       - DayOfWeek to train the exercise.
     * @param exercises - list of the trained Exercises
     * @throws DayOffException          if the Workout on this day is null
     * @throws IllegalArgumentException if day is null or exercises is null or empty
     */
    @Override
    public void addExercises(DayOfWeek day, List<Exercise> exercises) {
        if(!trainingProgram.containsKey(day)) {
            throw new DayOffException("Today is a rest day");
        }
        if (day == null) {
            throw new IllegalArgumentException("Day can't be null");
        }
        if (exercises == null) {
            throw new IllegalArgumentException("Exercises can't be null");
        }
        if (exercises.isEmpty()) {
            throw new IllegalArgumentException("Exercises can't be null");
        }

        trainingProgram.get(day).exercises().addAll(exercises);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Member other) {
        return this.name.compareTo(other.name);
    }
}
