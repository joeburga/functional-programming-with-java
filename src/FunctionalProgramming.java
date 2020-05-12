import model.Course;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FunctionalProgramming {

    public static void main(String[] args) {

        long time = System.currentTimeMillis();

        Integer result = incrementBy1AndMultiplyBy10.apply(4);
        System.out.println("incrementBy1AndMultiplyBy10: " + result);
        System.out.println("incrementBy1AndMultiplyByNumber: " + incrementBy1AndMultiplyByNumber.apply(4, 10));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .filter(isEven)
                .map(number -> incrementBy1AndMultiplyBy10.apply(number))
                .forEach(System.out::println);

        List<Integer> evenNumbers = numbers
                .stream()
                .filter(isEven)
                .collect(Collectors.toList());
        System.out.println("evenNumbers: " + evenNumbers);

        List<Integer> evenNumbersAndMultipleOf = numbers
                .stream()
                .filter(number -> isEvenAndMultipleOf.test(number,4))
                .collect(Collectors.toList());
        System.out.println("evenNumbersAndMultipleOf: " + evenNumbersAndMultipleOf);

        System.out.println("sumList: " + sumList.apply(numbers));
        System.out.println("maxList: " + maxList.apply(numbers));

        List<Course> courses = Arrays.asList(
                new Course("Spring", "Framework", 98, 20000),
                new Course("SpringBoot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Hibernate", "Framework", 92, 25000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)
        );

        System.out.println("NameOfCoursesDelimiterWithComma: " + courses
                .stream()
                .map(Course::getName)
                .collect(Collectors.joining(",")));
        System.out.println("sortedByNumbersOfStudentsIncreasing: " + courses
                .stream()
                .sorted(Comparator.comparingInt(Course::getNumberOfStudents))
                .collect(Collectors.toList())
        );
        System.out.println("sortedByNumbersOfStudentsIncreasing: " + courses
                .stream()
                .sorted(compareByNumberOfStudentsIncreasing)
                .collect(Collectors.toList())
        );
        System.out.println("sortedByNumbersOfStudentsAndNumberOfReviewsIncreasing: " + courses
                .stream()
                .sorted(compareByIncreasingNumberOfStudentsAndNumberOfReviewsIncreasing)
                .collect(Collectors.toList())
        );

        Predicate<Course> courseNumberOfStudentsGreaterThan95Predicate = course -> course.getNumberOfStudents() > 95;
        System.out.println("maxNumberOfReviewsThatNumberOfStudentsIsGreaterThan95: " + courses
                .stream()
                .filter(courseNumberOfStudentsGreaterThan95Predicate)
                .mapToInt(Course::getNumberOfReviews)
                .max()
        );

        Comparator<Object> reversedSortedAndPrintNumberValue = Comparator.comparingInt(number -> {
            System.out.println("Number before sorted: " + number);
            return (int) number;
        }).reversed();
        System.out.println("maxNumberOfReviewsThatNumberOfStudentsIsGreaterThan95: " + courses
                .stream()
                .filter(courseNumberOfStudentsGreaterThan95Predicate)
                .map(Course::getNumberOfReviews)
                .peek(System.out::println)
                .sorted(reversedSortedAndPrintNumberValue)
                .peek(number -> System.out.println("Number after sorted: " + number))
                .collect(Collectors.toList())
        );

        System.out.println(System.currentTimeMillis() - time + "millis");
    }

    static Predicate<Integer> isEven = number -> number % 2 == 0;
    static BiPredicate<Integer, Integer> isEvenAndMultipleOf = (number, multipleOf) -> number % 2 == 0 && number % multipleOf == 0;

    static Function<Integer, Integer> incrementBy1AndMultiplyBy10 = number -> (number + 1) * 10;

    static BiFunction<Integer, Integer, Integer> incrementBy1AndMultiplyByNumber = (number, numberMultiply)
            -> (number + 1) * numberMultiply;

    static Function<List<Integer>, Integer> sumList = numbers -> numbers
            .stream()
            .reduce(0, Integer::sum);
//          .reduce(0, (n1,n2) -> n1 + n2);

    static Function<List<Integer>, Integer> maxList = numbers -> numbers
            .stream()
            .reduce(Integer.MIN_VALUE, Integer::max);

    static Comparator<Course> compareByNumberOfStudentsIncreasing = Comparator.comparing(Course::getNumberOfStudents);
    static Comparator<Course> compareByIncreasingNumberOfStudentsAndNumberOfReviewsIncreasing = Comparator.comparing(Course::getNumberOfStudents)
        .thenComparing(Course::getNumberOfReviews);

}


