import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Simulation {
    final ArrayList<Library> libraries = new ArrayList<>();
    final ArrayList<Library> selected = new ArrayList<>();
    int B;
    int L;
    int daysLeft;
    Integer[] S;

    abstract int simulate();

    public void read(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            B = scanner.nextInt();
            L = scanner.nextInt();
            daysLeft = scanner.nextInt();

            S = new Integer[B];
            for (int i = 0; i < B; i++) {
                S[i] = scanner.nextInt();
            }

            selected.clear();
            libraries.clear();
            for (int i = 0; i < L; i++) {
                int N = scanner.nextInt();
                int T = scanner.nextInt();
                int M = scanner.nextInt();
                ArrayList<Integer> books = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    int ID = scanner.nextInt();
                    books.add(ID);
                }
                libraries.add(new Library(i, books, T, M, S));
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
