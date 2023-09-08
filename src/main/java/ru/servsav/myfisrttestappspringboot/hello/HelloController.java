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
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
            defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "s",
            defaultValue = "") String s) {
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(s);
        return String.format("В ArrayList добавлен элемент %s",s);
    }
    @GetMapping("/show-array")
    public String showArrayList(String string) {
        String format = null;
        if (list != null) {
            format = String.format("Содержимое ArrayList: %s", list.toString());
        }
        return format;
    }

    // генерация уникального ключа на основе значения для HashMap<String, String>
    private static String generateUniqueKey(String value) {
        return value + "_" + UUID.randomUUID().toString();
    }
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "s",
            defaultValue = "") String s) {
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

        }
        return format;
    }
}
