import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    final int id;
    final List<Book> books;
    final int signupDelay;
    final int shippingCapacity;
    List<Book> selected;
    int score;

    public Library(int id, List<Book> books, int signupDelay, int shippingCapacity) {
        this.id = id;
        this.books = books;
        this.signupDelay = signupDelay;
        this.shippingCapacity = shippingCapacity;
        books.sort(Comparator.comparingInt(b -> -b.score));
    }

    public int shippingDays() {
        return (books.size() + shippingCapacity - 1) / shippingCapacity;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ' ' + selected.size() + '\n' +
                selected.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public void calcScore(int daysLeft) {
        int onTime = Math.min(shippingDays(), Math.max(daysLeft - signupDelay, 0)) * shippingCapacity;
        selected = (onTime < books.size()) ? books.subList(0, onTime) : books;
        score = selected.stream().mapToInt(b -> b.score).sum();
    }
}
