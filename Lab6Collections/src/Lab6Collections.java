import java.util.Arrays;

/**
 * Варіант: C2 = 1 → Set, C3 = 2 → двозв'язний список.
 */
public class Lab6Collections {

    public static void main(String[] args) {

        try {
            CoffeeSet set = new CoffeeSet();

            Coffee c1 = new BeanCoffee("Arabica зерно", 1.0, 400, 9);
            Coffee c2 = new GroundCoffee("Lavazza мелена", 0.8, 320, 8);
            Coffee c3 = new InstantCoffee("Nescafe розчинна", 0.5, 210, 6);
            Coffee c4 = new BeanCoffee("Colombia зерно", 1.5, 530, 10);

            set.add(c1);
            set.add(c2);
            set.add(c3);
            set.add(c4);

            set.add(c1);

            System.out.println("Вміст множини після додавання елементів:");
            for (Coffee coffee : set) {
                System.out.println(coffee);
            }

            System.out.println("\nРозмір множини: " + set.size());

            CoffeeSet single = new CoffeeSet(c1);
            System.out.println("\nМножина, створена з одного елемента:");
            for (Coffee coffee : single) {
                System.out.println(coffee);
            }

            CoffeeSet fromCollection = new CoffeeSet(
                    Arrays.asList(c1, c2, c3)
            );

            System.out.println("\nМножина, створена зі стандартної колекції:");
            for (Coffee coffee : fromCollection) {
                System.out.println(coffee);
            }

            System.out.println("\nМістить 'Lavazza мелена'? " + set.contains(c2));

            set.remove(c3);
            System.out.println("\nПісля видалення Nescafe розчинна:");
            for (Coffee coffee : set) {
                System.out.println(coffee);
            }

            set.clear();
            System.out.println("\nПісля clear(), розмір множини: " + set.size());

        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
}
