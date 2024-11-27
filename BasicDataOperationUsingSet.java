import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet надає методи для виконання основних операцiй з даними типу Long.
 * 
 * <p>Цей клас зчитує данi з файлу "list/Long.data", сортує їх та виконує пошук значення в масивi та множинi.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив Long.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi Long.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi Long.</li>
 *   <li>{@link #searchSet()} - Виконує пошук значення в множинi Long.</li>
 *   <li>{@link #findMinAndMaxInSet()} - Знаходить мiнiмальне та максимальне значення в множинi Long.</li>
 *   <li>{@link #compareArrayAndSet()} - Порiвнює елементи масиву та множини.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingSet(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #longValueToSearch} - Значення Long для пошуку.</li>
 *   <li>{@link #longArray} - Масив Long.</li>
 *   <li>{@link #longSet} - Множина Long.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingSet "2024-03-16T00:12:38Z"
 * }
 * </pre>
 */
public class BasicDataOperationUsingSet {
    static final String PATH_TO_DATA_FILE = "list/long.data";

    Long longValueToSearch;
    Long[] longArray;
    Set<Long> longSet = new HashSet<>();

    public static void main(String[] args) {  
        BasicDataOperationUsingSet basicDataOperationUsingSet = new BasicDataOperationUsingSet(args);
        basicDataOperationUsingSet.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingSet(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String valueToSearch = args[0];
        this.longValueToSearch = Long.parseLong(valueToSearch);

        longArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        longSet = new HashSet<>(Arrays.asList(longArray));
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та множину об'єктiв Long з файлу, сортує їх та виконує пошук значення.
     */
    private void doDataOperation() {
        // операцiї з масивом дати та часу
        searchArray();
        findMinAndMaxInArray();

        sortArray();

        searchArray();
        findMinAndMaxInArray();

        // операцiї з HashSet дати та часу
        searchSet();
        findMinAndMaxInSet();
        compareArrayAndSet();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(longArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв Long та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    private void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(longArray);

        Utils.printOperationDuration(startTime, "сортування масиву чисел");
    }

    /**
     * Метод для пошуку значення в масивi чисел.
     */
    private void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.longArray, longValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масивi чисел");

        if (index >= 0) {
            System.out.println("Значення '" + longValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + longValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi Long.
     */
    private void findMinAndMaxInArray() {
        if (longArray == null || longArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Long min = longArray[0];
        Long max = longArray[0];

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної чисел в масивi");

        for (Long longValue : longArray) {
            if (longValue < min) {
                min = longValue;
            }
            if (longValue > max) {
                max = longValue;
            }
        }

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Метод для пошуку значення в множинi чисел.
     */
    private void searchSet() {
        long startTime = System.nanoTime();

        boolean isFound = this.longSet.contains(longValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в HashSet чисел");

        if (isFound) {
            System.out.println("Значення '" + longValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Значення '" + longValueToSearch + "' в HashSet не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в множинi Long.
     */
    private void findMinAndMaxInSet() {
        if (longSet == null || longSet.isEmpty()) {
            System.out.println("HashSet порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Long min = Collections.min(longSet);
        Long max = Collections.max(longSet);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної чисел в HashSet");

        System.out.println("Мiнiмальне значення в HashSet: " + min);
        System.out.println("Максимальне значення в HashSet: " + max);
    }

    /**
     * Порiвнює елементи масиву та множини.
     */
    private void compareArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + longArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + longSet.size());

        boolean allElementsMatch = true;
        for (Long longValue : longArray) {
            if (!longSet.contains(longValue)) {
                allElementsMatch = false;
                break;
            }
        }

        if (allElementsMatch) {
            System.out.println("Всi елементи масиву присутнi в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву присутнi в HashSet.");
        }
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу Long.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв Long з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв Long.
     */
    static Long[] readArrayFromFile(String pathToFile) {
        Long[] tempArray = new Long[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Long longValue = Long.parseLong(line);
                tempArray[index++] = longValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Long[] finalArray = new Long[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв Long у файл.
     * 
     * @param longArray Масив об'єктiв Long.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Long[] longArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Long longValue : longArray) {
                writer.write(longValue.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}