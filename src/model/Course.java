package model;

public class Course {
    private String name;
    private String category;
    private int numberOfStudents;
    private int numberOfReviews;

    public Course(String name, String category, int numberOfStudents, int numberOfReviews) {
        this.name = name;
        this.category = category;
        this.numberOfStudents = numberOfStudents;
        this.numberOfReviews = numberOfReviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfReviews=" + numberOfReviews +
                '}';
    }
}
