package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User rsl = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                rsl = user;
                break;
            }
        }
        if (rsl == null) {
            throw new UserNotFoundException("User not found");
        }
        return rsl;
    }

    public static boolean validate(User user) throws UserInvalidException {
        boolean rsl = user.isValid();
        if (!rsl || user.getUsername().length() < 3) {
            throw new UserInvalidException("the user is not valid");
        }
        return rsl;
    }

    public static void main(String[] args) {
        User[] users = {new User("Iv", true),
                new User("Pavel Valger", false),
                new User("Petr Arsentev", true)};
        try {
            User user = findUser(users, "Pavel Valger");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
