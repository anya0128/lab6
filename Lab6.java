import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Lab6 {
    static class Actor {
        String name;
        List<Film> films = new ArrayList<>();
        Actor(String name) {
            this.name = name;
        }
    }
    static class Film {
        String title;
        List<Actor> actors = new ArrayList<>();
        Film(String title) {
            this.title = title;
        }
    }
    static class MovieDatabase {
        List<Film> films = new ArrayList<>();
        List<Actor> actors = new ArrayList<>();
    }
    public static void main(String[] args) {
        MovieDatabase db1 = new MovieDatabase();
        Actor daniel = new Actor("Деніел Редкліфф");
        Actor emma = new Actor("Емма Вотсон");
        Actor rupert = new Actor("Руперт Грінт");
        Actor tom = new Actor("Том Фелтон");
        Actor alan = new Actor("Алан Рікман");
        db1.actors.add(daniel);
        db1.actors.add(emma);
        db1.actors.add(rupert);
        db1.actors.add(tom);
        db1.actors.add(alan);
        Film film1 = new Film("Гаррі Поттер і філософський камінь");
        Film film2 = new Film("Гаррі Поттер і таємна кімната");
        Film film3 = new Film("Гаррі Поттер і в'язень Азкабану");
        addActor(db1, film1, daniel);
        addActor(db1, film1, emma);
        addActor(db1, film1, rupert);
        addActor(db1, film1, tom);
        addActor(db1, film1, alan);
        addActor(db1, film2, daniel);
        addActor(db1, film2, emma);
        addActor(db1, film2, rupert);
        addActor(db1, film2, alan);
        addActor(db1, film3, daniel);
        addActor(db1, film3, emma);
        addActor(db1, film3, rupert);
        addActor(db1, film3, tom);
        db1.films.add(film1);
        db1.films.add(film2);
        db1.films.add(film3);
        run(db1, daniel);

        MovieDatabase db2 = new MovieDatabase();
        Actor a = new Actor("Актор А");
        Actor b = new Actor("Актор Б");
        Actor c = new Actor("Актор В");
        Actor newcomer = new Actor("Новачок");
        db2.actors.add(a);
        db2.actors.add(b);
        db2.actors.add(c);
        db2.actors.add(newcomer);
        Film first = new Film("Перший фільм");
        Film second = new Film("Другий фільм");
        addActor(db2, first, a);
        addActor(db2, first, b);
        addActor(db2, second, b);
        addActor(db2, second, c);
        db2.films.add(first);
        db2.films.add(second);
        run(db2, b);

        MovieDatabase db3 = new MovieDatabase();
        Actor lonely = new Actor("Самотній актор");
        db3.actors.add(lonely);
        run(db3, lonely);
    }
    static void run(MovieDatabase db, Actor selectedActor) {
        System.out.println("Чи є актор без фільмів: " + (hasActorWithoutFilms(db) ? "так" : "ні"));
        System.out.println("Разом з актором " + selectedActor.name + " знімались: "
                + namesOf(findCoStars(selectedActor)));
        Film mostActorsFilm = findFilmWithMostActors(db);
        if (mostActorsFilm != null) {
            System.out.println("Найбільше акторів у фільмі: " + mostActorsFilm.title
                    + " (" + mostActorsFilm.actors.size() + ")");
        } else {
            System.out.println("У базі немає жодного фільму");
        }
        System.out.println();
    }
    static boolean hasActorWithoutFilms(MovieDatabase db) {
        for (Actor actor : db.actors) {
            if (actor.films.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("rawtypes")
    static List<Actor> findCoStars(Actor selectedActor) {
        List<Actor> coStars = new ArrayList<>();
        Iterator filmIterator = selectedActor.films.iterator();
        while (filmIterator.hasNext()) {
            Film film = (Film) filmIterator.next();
            Iterator actorIterator = film.actors.iterator();
            while (actorIterator.hasNext()) {
                Actor actor = (Actor) actorIterator.next();
                if (actor != selectedActor && !coStars.contains(actor)) {
                    coStars.add(actor);
                }
            }
        }
        return coStars;
    }
    static Film findFilmWithMostActors(MovieDatabase db) {
        Film mostActorsFilm = null;
        Iterator<Film> filmIterator = db.films.iterator();
        while (filmIterator.hasNext()) {
            Film film = filmIterator.next();
            if (mostActorsFilm == null || film.actors.size() > mostActorsFilm.actors.size()) {
                mostActorsFilm = film;
            }
        }
        return mostActorsFilm;
    }
    static String namesOf(List<Actor> list) {
        StringBuilder sb = new StringBuilder("[");
        for (Actor actor : list) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(actor.name);
        }
        return sb.append("]").toString();
    }
    static void addActor(MovieDatabase db, Film film, Actor actor) {
        if (!film.actors.contains(actor)) {
            film.actors.add(actor);
        }
        if (!actor.films.contains(film)) {
            actor.films.add(film);
        }
    }
}
