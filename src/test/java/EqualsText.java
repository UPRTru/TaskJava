public class EqualsText {
    public static final String up = """
                Введите путь к файлу address.csv или address.xml\r
                Для выхода из приложения: 2\s
                \r
                """;
    private static final String csv = """
            Дублирующиеся записи:
            \r
            Строка: "Барнаул";"Дальняя улица";56;2
            количество повторов: 3
            _____________________
            \r
            """;
    private static final String xml = """
            Дублирующиеся записи:
            \r
            Строка: <item city="Барнаул" street="Дальняя улица" house="56" floor="2" />
            количество повторов: 3
            _____________________
            \r
            """;
    private static final String down = """
            Отображение, сколько в каждом городе: 1, 2, 3, 4 и 5 этажных зданий:
            \r
            Город: Балаково
             Двухэтажные: 1

            _____________________
            Город: Барнаул
             Двухэтажные: 1

            _____________________
            Город: Братск
             Пятиэтажные: 1

            _____________________
            \r
            """;

    public static String txt(String format) {
        return switch (format) {
            case "csv" -> up + csv + down + up;
            case "xml" -> up + xml + down + up;
            default -> "";
        };
    }
}
