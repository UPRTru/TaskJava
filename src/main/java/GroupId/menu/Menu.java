package GroupId.menu;

public class Menu {
    public void startMenu() {
        System.out.println("Введите путь к файлу address.csv или address.xml");
        System.out.println("Для выхода из приложения: 2 \n");
    }

    public void doubles(String doubles) {
        System.out.println("Дублирующиеся записи:\n");
        if (doubles.isEmpty()) {
            System.out.println("Дублирующиеся записи не найдены.\n");
        } else {
            System.out.println(doubles);
        }
    }

    public void houses(String houses) {
        System.out.println("Отображение, сколько в каждом городе: 1, 2, 3, 4 и 5 этажных зданий:\n");
        System.out.println(houses);
    }
}
