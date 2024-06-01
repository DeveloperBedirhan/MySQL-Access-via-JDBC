import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        selectData();
        insertData();
        updateData();
        deleteData();
    }

    public static void selectData() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement;
        ResultSet resultSet;
        try {
            connection = helper.GetConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM actor");
            ArrayList<Actor> actors = new ArrayList<>();
            while (resultSet.next()) {
                actors.add(new Actor(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
            for (Actor actor : actors)
                System.out.println(actor.first_name);
        } catch (SQLException exception) {
            helper.ShowErrorMessage(exception);
        } finally {
            connection.close();
        }
    }

    public static void insertData() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try {
            connection = helper.GetConnection();
            String sql = "insert into actor(first_name,last_name) values(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Morgan");
            statement.setString(2, "Freeman");
            statement.executeUpdate();
            System.out.println("Kayıt eklendi");
        } catch (SQLException exception) {
            helper.ShowErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static void updateData() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try {
            connection = helper.GetConnection();
            String sql = "update actor set first_name='Jason',last_name='Statham' where actor_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,201);
            statement.executeUpdate();
            System.out.println("Kayıt güncellendi");
        } catch (SQLException exception) {
            helper.ShowErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static void deleteData() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        try {
            connection = helper.GetConnection();
            String sql = "delete from actor where actor_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,205);
            statement.executeUpdate();
            System.out.println("Kayıt silindi");
        } catch (SQLException exception) {
            helper.ShowErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
}