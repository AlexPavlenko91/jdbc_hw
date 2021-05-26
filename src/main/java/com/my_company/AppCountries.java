package com.my_company;

import java.sql.*;

public class AppCountries {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/countries_db?useUnicode=true&amp";
            conn = DriverManager.getConnection(url, "root", "root");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from country");

            while (resultSet.next())
                System.out.println(resultSet.getInt(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3));
            System.out.println("---------------------------");

            resultSet = statement.executeQuery("SELECT * from big_city INNER JOIN country " +
                    "on big_city.id_country = country.id");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4)
                        + "\t" + resultSet.getString(5)
                        + "\t" + resultSet.getString(6));
            System.out.println("---------------------------");

            resultSet = statement.executeQuery("SELECT * from big_city where is_capital = 1");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4)
                        + "\t" + resultSet.getString(5)
                        + "\t" + resultSet.getString(6));
            System.out.println("---------------------------");

            resultSet = statement.executeQuery("SELECT * from big_city where id_country = 1 and is_capital = 1");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4)
                        + "\t" + resultSet.getString(5)
                        + "\t" + resultSet.getString(6));
            System.out.println("---------------------------");


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
