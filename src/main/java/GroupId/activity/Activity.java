package GroupId.activity;

import GroupId.exception.ErrorFoundFile;
import GroupId.exception.ErrorInPut;
import GroupId.exception.ErrorReadFile;
import GroupId.file.Address;
import GroupId.file.JsonAddress;
import GroupId.menu.Menu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Activity {
    private final Menu menu;
    private final Map<String, Integer> addressDoubles = new HashMap<>();
    private final Map<String, Address> mapAddress = new HashMap<>();
    private String fileFormat = "";
    public Scanner scanner = new Scanner(System.in);

    public Activity() {
        this.menu = new Menu();
    }

    //запуск меню
    public void start() {
        menu.startMenu();
        if (scanner.hasNextLine()) {
            if (scanner.hasNextInt()) {
                if (scanner.nextInt() == 2) {
                    scanner.close();
                    System.out.println("Выход");
                    System.exit(0);
                } else {
                    throw new ErrorInPut("Ошибка ввода. \nПопробуйте снова.\n");
                }
            } else  {
                existsFile(scanner.nextLine());
                scanner.close();
            }
        } else {
            scanner.close();
        }
    }

    //проверка файла
    private void existsFile(String path) {
        String format;
        if (path.length() > 13 && ((format = path.substring(path.length() - 11)).equals("address.csv") ||
                format.equals("address.xml"))) {
            File file;
            if ((file = new File(path)).exists()) {
                switch (format) {
                    case ("address.csv"):
                        fileFormat = "csv";
                        readCSV(file);
                        break;
                    case ("address.xml"):
                        fileFormat = "xml";
                        readXML(file);
                        break;
                }
                start();
            } else {
                throw new ErrorFoundFile("Файл не найден. \nПопробуйте снова.\n");
            }
        } else {
            throw new ErrorInPut("Ошибка ввода. \nПопробуйте снова.\n");
        }
    }

    //чтение csv файла
    private void readCSV(File file) {
        try {
            Pattern pattern = Pattern.compile(";");
            BufferedReader csvFile = new BufferedReader(new FileReader(file));
            List<JsonAddress> jsonAddressList = csvFile
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] sl = pattern.split(line);
                        return new JsonAddress(sl[0].replaceAll("\"", ""),
                                sl[1].replaceAll("\"", ""),
                                Integer.parseInt(sl[2]),
                                Integer.parseInt(sl[3]));
                    })
                    .collect(Collectors.toList());
            addressInMap(jsonAddressList);
        } catch (IOException e) {
            throw new ErrorReadFile("Ошибка чтения файла. \nПопробуйте снова.\n");
        }
    }

    //чтение XML файла
    private void readXML(File file) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            List<JsonAddress> jsonAddressList = xmlMapper.readValue(
                    new String(new FileInputStream(file).readAllBytes(), "CP1251"),
                    new TypeReference<>() {});
            addressInMap(jsonAddressList);
        } catch (Exception e) {
            throw new ErrorReadFile("Ошибка чтения файла. \nПопробуйте снова.\n");
        }
    }

    //предварительный сбор данных в MAP
    private void addressInMap(List<JsonAddress> jsonAddressList) throws JsonProcessingException {
        addressDoubles.clear();
        mapAddress.clear();
        for (JsonAddress jsonAddress: jsonAddressList) {
            if (addressDoubles.containsKey(jsonAddress.toStringLine(fileFormat))) {
                addressDoubles.put(jsonAddress.toStringLine(fileFormat),
                        addressDoubles.get(jsonAddress.toStringLine(fileFormat)) + 1);
            } else {
                addressDoubles.put(jsonAddress.toStringLine(fileFormat), 1);
                if (mapAddress.containsKey(jsonAddress.getCity())) {
                    mapAddress.get(jsonAddress.getCity()).inputHouse(jsonAddress.getFloor());
                } else {
                    Address address = new Address(jsonAddress.getCity());
                    address.inputHouse(jsonAddress.getFloor());
                    mapAddress.put(jsonAddress.getCity(), address);
                }
            }
        }
        if (addressDoubles.containsValue(1)) {
            addressDoubles.values().removeAll(Collections.singleton(1));
        }
        outPutAddress();
    }

    //вывод данных
    private void outPutAddress() {
        StringBuilder doubles = new StringBuilder();
        for (String text: addressDoubles.keySet()) {
            doubles.append("Строка: ").append(text).append("\nколичество повторов: ")
                    .append(addressDoubles.get(text)).append("\n_____________________\n");
        }
        menu.doubles(String.valueOf(doubles));
        StringBuilder houses = new StringBuilder();
        for (String city: mapAddress.keySet()) {
            houses.append(mapAddress.get(city)).append("\n_____________________\n");
        }
        menu.houses(String.valueOf(houses));
    }
}
