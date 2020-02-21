import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Simulation {
    final ArrayList<Library> libraries = new ArrayList<>();
    final ArrayList<Library> selected = new ArrayList<>();
    final ArrayList<Book> books = new ArrayList<>();
    int daysLeft;

    abstract int simulate();

    public void read(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int bookCount = scanner.nextInt();
            int libCount = scanner.nextInt();
            daysLeft = scanner.nextInt();

            books.clear();
            for (int i = 0; i < bookCount; i++) {
                int score = scanner.nextInt();
                books.add(new Book(i, score));
            }

            selected.clear();
            libraries.clear();
            for (int i = 0; i < libCount; i++) {
                int catalog = scanner.nextInt();
                int signupDelay = scanner.nextInt();
                int shippingCapacity = scanner.nextInt();
                ArrayList<Book> libBooks = new ArrayList<>();
                for (int j = 0; j < catalog; j++) {
                    int ID = scanner.nextInt();
                    libBooks.add(books.get(ID));
                }
                libraries.add(new Library(i, libBooks, signupDelay, shippingCapacity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(selected.size());
            for (Library lib : selected) {
                writer.println(lib);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int run(String input, String output) {
        read(input);
        int total = simulate();
        write(output);
        return total;
    }
}
