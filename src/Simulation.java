import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

abstract class Simulation {
    final ArrayList<Library> libraries = new ArrayList<>();
    int B;
    int L;
    int D;
    Integer[] S;

    abstract int simulate();

    public void read(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            B = scanner.nextInt();
            L = scanner.nextInt();
            D = scanner.nextInt();

            S = new Integer[B];
            for (int i = 0; i < B; i++) {
                S[i] = scanner.nextInt();
            }

            libraries.clear();
            for (int i = 0; i < L; i++) {
                int N = scanner.nextInt();
                int T = scanner.nextInt();
                int M = scanner.nextInt();
                HashSet<Integer> books = new HashSet<>();
                for (int j = 0; j < N; j++) {
                    int ID = scanner.nextInt();
                    books.add(ID);
                }
                libraries.add(new Library(books, T, M));
                S[i] = scanner.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(String filename) {
//        try (PrintWriter writer = new PrintWriter(filename)) {
//            for (Car car : cars) {
//                writer.println(car);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public int run(String input, String output) {
        read(input);
        int total = simulate();
        write(output);
        return total;
    }
}
