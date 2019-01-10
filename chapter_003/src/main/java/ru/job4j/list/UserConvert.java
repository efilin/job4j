package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * В классе UserConvert написать метод public HashMap<Integer, User> process(List<User> list) {},
 * который принимает в себя список пользователей и конвертирует его в Map с ключом Integer id и соответствующим ему User.
 *
 * Изменено на Stream API
 */

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        /*HashMap<Integer, User> result = new HashMap<>();
        for (User user :list) {
            result.put(user.getId(), user);
        }*/
        return list.stream()
                .collect(Collectors.toMap(User::getId, user -> user, (a, b) -> a, HashMap::new));
    }
}
