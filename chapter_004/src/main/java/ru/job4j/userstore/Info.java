package ru.job4j.userstore;

public class Info {
    private int addedUsers;
    private int changedUsers;
    private int deletedUsers;

    public Info(int addedUsers, int changedUsers, int deletedUsers) {
        this.addedUsers = addedUsers;
        this.changedUsers = changedUsers;
        this.deletedUsers = deletedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;

        if (addedUsers != info.addedUsers) {
            return false;
        }
        if (changedUsers != info.changedUsers) {
            return false;
        }
        return deletedUsers == info.deletedUsers;
    }

    @Override
    public int hashCode() {
        int result = addedUsers;
        result = 31 * result + changedUsers;
        result = 31 * result + deletedUsers;
        return result;
    }
}
