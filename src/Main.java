import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        //selectDemo();
        //insertDemo();
        //updateDemo();
        //deleteDemo();
    }

    public static void selectDemo() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Code, Name, Continent, Region from country");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region")));
            }
            System.out.println(countries.size());

        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
    }

    public static void insertDemo() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "insert into city (Name, CountryCode, District, Population) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Düzce");
            statement.setString(2,"TUR");
            statement.setString(3,"Turkey");
            statement.setInt(4,150000);
            int result = statement.executeUpdate();
            System.out.println("Kayıt eklendi!");
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }

    }

    public static void updateDemo() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "update city set population = 110000, district = 'Turkey' where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 4080);
            int result = statement.executeUpdate();
            System.out.println("Kayıt güncellendi!");
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static void deleteDemo() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "delete from city where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 4080);
            int result = statement.executeUpdate();
            System.out.println("Kayıt silindi!");
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
}