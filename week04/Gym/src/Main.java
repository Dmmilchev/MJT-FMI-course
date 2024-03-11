import bg.sofia.uni.fmi.mjt.gym.member.*;
import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;
import bg.sofia.uni.fmi.mjt.gym.workout.Workout;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SequencedCollection;

public class Main {
    public static void main(String[] args) {
        Member member = new Member(new Address(5, 10),
                "Deyan",
                21,
                "0212",
                Gender.MALE);

        Exercise firstExercise = new Exercise("biceps curls", 4, 12);
        Exercise secondExercise = new Exercise("triceps extensions", 4, 12);
        Workout arms = new Workout(new ArrayList<Exercise>());
        arms.exercises().add(firstExercise);
        arms.exercises().add(secondExercise);
        member.setWorkout(DayOfWeek.FRIDAY, arms);

        System.out.println(member.getName());
        System.out.println(member.getTrainingProgram());
    }
}