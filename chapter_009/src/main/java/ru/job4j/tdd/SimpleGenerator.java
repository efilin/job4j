package ru.job4j.tdd;


        /*Исправить код класс SimpleGenerator.
        1. Генератор должен получать входную строку с ключами в тексте и список значений по этим ключам.
        Например. Входящая строка String template = "I am a ${name}, Who are ${subject}? "
        и список значений ассоциированных по ключу name -> "Petr", subject -> "you"
        На выходе должна быть строка - "I am Petr, Who are you?"
        Другой пример. " Help, ${sos}, ${sos}, ${sos}", sos -> "Aaaa". Должно получится " Help, Ааа, Ааа, Ааа"
        2. Программа должна учитывать. что ключей нет в карте и кидать исключение.
        3. Программа должна учитываться. что в карте есть лишние ключи и тоже кидать исключение.
        В качестве карты можно использовать Map<String, String>, либо Pair[] - Pair - объект из библиотеки guava.*/

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Template {

    private static final String REGEX = "\\$\\{(.*?)}";
    private static final Pattern PATTERN = Pattern.compile(REGEX);


    @Override
    public String generate(String template, Map<String, String> data) {
        Set<String> usedKeys = new HashSet<>();
        Matcher m = PATTERN.matcher(template);
        String result = null;
        while (m.find()) {
            final String key = m.group(1);
            if (!data.containsKey(key)) {
                throw new UnsupportedOperationException("Ключ не найден!");
            }
            final String value = data.get(key);
            usedKeys.add(key);
            result = m.replaceFirst(value);
            m = PATTERN.matcher(result);
        }
        if (!usedKeys.containsAll(data.keySet())) {
            throw new UnsupportedOperationException("Лишние ключи в карте!");
        }
        return result;
    }
}
