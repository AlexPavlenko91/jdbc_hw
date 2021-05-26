package com.my_company;

import java.sql.*;

public class AppCars {
    public static void main(String[] args){

        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/cars_db?useUnicode=true&amp";
            conn = DriverManager.getConnection(url, "root", "root");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * from car");

            while (resultSet.next())
                System.out.println(resultSet.getInt(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3)
                        + "\t" + resultSet.getString(4)
                        + "\t" + resultSet.getString(5)
                        + "\t" + resultSet.getString(6)
                        + "\t" + resultSet.getString(7));

            resultSet = statement.executeQuery("SELECT `manufacture_name` from car group by `manufacture_name`");
            while (resultSet.next())
                System.out.println(resultSet.getString(1));

            resultSet = statement.executeQuery("SELECT `manufacture_name`, COUNT(`id`) from car group by `manufacture_name`");
            while (resultSet.next())
                System.out.println(resultSet.getString(1)
                        + "\t" + resultSet.getInt(2));

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
