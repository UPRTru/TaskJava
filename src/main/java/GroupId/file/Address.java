package GroupId.file;

public class Address {
    private final String city;
    private Integer house1;
    private Integer house2;
    private Integer house3;
    private Integer house4;
    private Integer house5;

    public Address(String city) {
        this.city = city;
        house1 = 0;
        house2 = 0;
        house3 = 0;
        house4 = 0;
        house5 = 0;
    }

    public void inputHouse(Integer house) {
        switch (house) {
            case 1:
                house1++;
                break;
            case 2:
                house2++;
                break;
            case 3:
                house3++;
                break;
            case 4:
                house4++;
                break;
            case 5:
                house5++;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Город: ").append(city);
        if (house1 > 0) {
            result.append("\n Одноэтажные: ").append(house1);
        }
        if (house2 > 0) {
            result.append("\n Двухэтажные: ").append(house2);
        }
        if (house3 > 0) {
            result.append("\n Трехэтажные: ").append(house3);
        }
        if (house4 > 0) {
            result.append("\n Четырехэтажные: ").append(house4);
        }
        if (house5 > 0) {
            result.append("\n Пятиэтажные: ").append(house5);
        }
        result.append("\n");
        return String.valueOf(result);
    }
}
