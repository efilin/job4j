package ru.job4j.finder;

public class Args {
    private String[] args;

    private String directory;
    private String inputFilename;
    private String mask;
    private String regExp;
    private String outputFilename;


    public Args(String[] args) {
        this.args = args;
        validate();
    }

    public void validate() {
        if (args.length == 0) {
            System.out.println("Не найдено ключей!\n"
                    + "Ключи:\n"
                    + "        -d - директория в которая начинать поиск.\n"
                    + "        -n - имя файл, маска, либо регулярное выражение.\n"
                    + "        -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.\n"
                    + "        -o - результат записать в файл."
                    + "Пример использования: java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-d")) {
                    directory = args[i + 1];
                } else if (args[i].equals("-n")) {
                    inputFilename = args[i + 1];
                } else if (args[i].equals("-o")) {
                    outputFilename = args[i + 1];
                } else if (args[i].equals("-m")) {
                    mask = inputFilename;
                    inputFilename = null;
                } else if (args[i].equals("-r")) {
                    regExp = inputFilename;
                    inputFilename = null;
                } else if (args[i].equals("-f")) {
                    regExp = null;
                    mask = null;
                }
            }
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getInputFilename() {
        return inputFilename;
    }

    public String getMask() {
        return mask;
    }

    public String getRegExp() {
        return regExp;
    }

    public String getOutputFilename() {
        return outputFilename;
    }
}
