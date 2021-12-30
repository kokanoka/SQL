package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DataHelper {
    private DataHelper() {

    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidLogin() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthWithInvalidPassword() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getValidVerificationCode() {
        String codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        String code = null;
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            code = runner.query(conn, codeSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("00");
    }

    @SneakyThrows
    public static void clearAllData() {
        var clearCodes = "DELETE FROM auth_codes;";
        var clearCards = "DELETE FROM cards;";
        var clearUsers = "DELETE FROM users;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass")) {
            runner.execute(conn, clearCodes);
            runner.execute(conn, clearCards);
            runner.execute(conn, clearUsers);
        }
    }
}
