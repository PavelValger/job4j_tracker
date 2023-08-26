package ru.job4j.lombok;

public class PermissionUsage {
    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("test")
                .accessBy("create")
                .accessBy("update")
                .build();
        System.out.println(permission);
        permission.setId(5);
        System.out.println(permission);
        var perm = Permission.of()
                .id(5)
                .name("test")
                .build();
        System.out.println(perm.equals(permission));
    }
}
