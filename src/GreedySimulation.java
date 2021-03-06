import java.util.Comparator;

class GreedySimulation extends Simulation {

    @Override
    int simulate() {
        int total = 0;
        while (daysLeft > 0 && !libraries.isEmpty()) {
            Library library = selectLibrary();
            if (library.score == 0) break;
            daysLeft -= library.signupDelay;
            for (Library lib : libraries) {
                lib.books.removeAll(library.selected);
            }
            total += library.score;
            selected.add(library);
        }
        return total;
    }

    private Library selectLibrary() {
        for (Library library : libraries) {
            library.calcScore(daysLeft);
        }
        libraries.sort(Comparator.comparingInt(l -> -l.score));
        return libraries.remove(0);
    }
}
