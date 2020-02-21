import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    final int id;
    final List<Integer> books;
    final int signupDelay;
    final int shippingCapacity;
    List<Integer> selected;
    int score;

    public Library(int id, List<Integer> books, int signupDelay, int shippingCapacity, Integer[] scores) {
        this.id = id;
        this.books = books;
        this.signupDelay = signupDelay;
        this.shippingCapacity = shippingCapacity;
        books.sort(Comparator.comparingInt(t -> -scores[t]));
    }

    public int shippingDays() {
        return (books.size() + shippingCapacity - 1) / shippingCapacity;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ' ' + selected.size() + '\n' +
                selected.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public void calcScore(int daysLeft, Integer[] scores) {
        int onTime = Math.min(shippingDays(), Math.max(daysLeft - signupDelay, 0)) * shippingCapacity;
        selected = (onTime < books.size()) ? books.subList(0, onTime) : books;
        score = selected.stream().mapToInt(i -> scores[i]).sum();
    }
}
