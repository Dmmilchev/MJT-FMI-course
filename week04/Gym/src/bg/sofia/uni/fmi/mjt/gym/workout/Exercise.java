package bg.sofia.uni.fmi.mjt.gym.workout;

public record Exercise(String name, int sets, int repetitions) {
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Exercise other) {
        if (other == null) {
            return false;
        }
        return this.name.equals(other.name);
    }
}
