package ru.servsav.myfisrttestappspringboot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {
    ArrayList<String> list;
    HashMap<String, String> hashMap;

    //Реализация простейшего Spring Boot Application
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
            defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    //Реализация дополнительного функционала

    //1. Создайте GET-метод updateArrayList(String s),
    // который по url “/update-array” принимает аргумент и записывает его в ArrayList<String >,
    // реализуйте логику - если это первый вызов метода, то создается пустой ArrayList,
    // если он не пустой, то туда записывается значение.
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "s", defaultValue = "") String s) {
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(s);
        return String.format("В ArrayList добавлен элемент: %s",s);
    }

    //2. Создайте GET метод showArrayList(),
    // который по url “/show-array” возвращается все элементы хранящиеся в ArrayList <String >, созданном в п. 1
    @GetMapping("/show-array")
    public String showArrayList(String string) {
        String format = null;
        if (list != null) {
            format = String.format("Содержимое ArrayList: %s", list.toString());
        } else format="В ArrayList нет элементов \n";
        return format;
    }

   // 3. Создайте GET-метод updateHashMap(String s),
    // который по url “/update-map” принимает аргумент и записывает его в HashMap<String, String>,
    // реализуйте логику - если это первый вызов метода, то создается пустой HashMap,
    // если он не пустой, то туда записывается значение.

    // генерация уникального ключа на основе значения для HashMap<String, String>
    private static String generateUniqueKey(String value) {
        return value + "_" + UUID.randomUUID().toString();
    }
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "s", defaultValue = "") String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        String key = generateUniqueKey(s); //генерация ключа на основе значения

        // Проверка на уникальность ключа если совпадает, генерируем заново.
        while (hashMap.containsKey(key)) {
            key = generateUniqueKey(s);
        }
        hashMap.put(key, s); // записываем ключ и значение в мапу

        return String.format("В HashMap добавлен элемент %s",s);
    }

    //4. Создайте GET метод showHashMap (),
    // который по url “/show-map” возвращается все элементы хранящиеся в HashMap<String, String>, созданном в п. 3
    @GetMapping("/show-map")
    public String showHashMap(String s) {
        String format ="";
        String val ="";
        String key ="";
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                 key = entry.getKey();
                 val = entry.getValue();
                 format = format + String.format
                         ("[ Ключ: " + key + ", Значение: " + val +" ]\n");
            }
        }else format="В hashMap нет элементов \n";
        return format;
    }

    //5. Создайте GET метод showAllLenght (),
    // который по url “/show-all-lenght” возвращает текст,
    // в котором указано количество элементов в ArrayList и HashMap
    @GetMapping("/show-all-length")
    public String showAllLength(String s) {
        String format ="";
        if (list != null ) {
         format="Кол-во элементов в ArrayList = " + list.size() + " \n";
        } else format="В ArrayList нет элементов \n";
        if (hashMap != null ) {
            format=format + "Кол-во элементов в hashMap = " + hashMap.size() + " \n";
        } else format=format + "В hashMap нет элементов \n";
        return format;
    }
}
