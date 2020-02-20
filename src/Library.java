import java.util.Set;

public class Library {
    private final Set<Integer> books;
    private final int signupDelay;
    private final int shippingCapacity;

    public Library(Set<Integer> books, int signupDelay, int shippingCapacity) {
        this.books = books;
        this.signupDelay = signupDelay;
        this.shippingCapacity = shippingCapacity;
    }
}
