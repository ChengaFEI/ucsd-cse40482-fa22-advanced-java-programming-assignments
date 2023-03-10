package database.query;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Lesson7Database {

    public static void main(String[] args) throws IOException {
        // Retrieve the parameter from the terminal.
        if (args.length != 1) { System.out.println("Invalid parameter! (Only 1 param needed)"); }
        else {
            // Create an inputStream with the input SQL file.
            try (Scanner scanner = new Scanner(Paths.get(args[0]), StandardCharsets.UTF_8)) {
                // Create the database connection.
                try (Connection connection = getConnection();
                        // Initialize the database statement.
                        Statement statement = connection.createStatement()) {
                    System.out.println("Connecting to the database: Done.");

                    // Execute SQL scripts in the input file.
                    String sqlScript;
                    while (scanner.hasNextLine()) {
                        // Truncate the trailing semicolon.
                        sqlScript = scanner.nextLine();
                        if (!sqlScript.startsWith("--")) {
                            if (sqlScript.endsWith(";")) {
                                sqlScript = sqlScript.substring(0, sqlScript.length()-1);
                            }
                            statement.executeUpdate(sqlScript);
                        }
                    }
                    System.out.println("Populating the database: Done.");

                    // Query all entries in the table.
                    System.out.println("Query of Lessons table results:");
                    statement.executeQuery("SELECT * FROM Lessons");
                    try (ResultSet resultSet = statement.getResultSet()) {
                        showResultSet(resultSet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new SQLException(e);
                    }

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    throw new IOException(ioe);
                } catch (SQLException sqle) {
                    for (Throwable t: sqle) { t.printStackTrace(); }
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                    throw new RuntimeException(cnfe);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
    }

    /**
     * Load database properties, initialize the database, and return a database connection.
     * @return Derby database connection initialized with the file called "database.properties".
     * @require The file "database.properties" is located in this same directory.
     * @throws IOException if the file "jdbc.properties" is not found.
     * @throws SQLException if the database connection is failed.
     */
    private static Connection getConnection()
            throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("ij.properties"))) {
            properties.load(inputStream);
        }
        System.out.println("Database connection info:");
        String driver = properties.getProperty("ij.driver");
        Class.forName(driver);
        String protocol = properties.getProperty("ij.protocol");
        String database = properties.getProperty("ij.database");
        String url = protocol + database;
        System.out.println("driver: " + driver);
        System.out.println("url: " + url);
        return DriverManager.getConnection(protocol+database);
    }

    /**
     * Display columns titles and all entries in the data table.
     * @param resultSet - the result table to display.
     * @throws SQLException if it fails to retrieve the metadata of the table.
     */
    private static void showResultSet(ResultSet resultSet) throws SQLException {
        // Retrieve the table's metadata.
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        // Display columns' titles.
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnLabel(i) + "\t");
        }
        System.out.println();
        // Display columns' entries.
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }
    }
}
