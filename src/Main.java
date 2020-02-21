import java.io.File;

class Main {

    public static void main(String[] args) {
        String[] examples = {"a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books", "f_libraries_of_the_world"};
        int total = 0;
        new File("outputs").mkdirs();

        Simulation sim = new GreedySimulation();
        for (String filename : examples) {
            total += sim.run("inputs/" + filename + ".txt", "outputs/" + filename + ".out");
        }
        System.out.println(total);
    }
}
