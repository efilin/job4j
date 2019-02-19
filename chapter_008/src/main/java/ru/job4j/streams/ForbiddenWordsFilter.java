package ru.job4j.streams;

import java.io.*;

public class ForbiddenWordsFilter {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
            br.lines()
                    .map(str -> removeAbuseWords(str, abuse))
                    .forEach(str -> {
                        try {
                            bw.write(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String removeAbuseWords(String line, String[] abuse) {
        /*
        вариант с потоком от ментора
        try (final PrintStream writer = new PrintStream(out);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines()
                    .map(s -> Arrays.stream(abuse)
                            .reduce(s, (s1, s2) -> s1.replaceAll(s2, ""))
                    ).forEach(writer::println);
        }*/

        for (String abWord : abuse) {
            if (line.contains(abWord)) {
                line = line.replaceAll(abWord, "");
            }
        }
        return line;
    }
}
