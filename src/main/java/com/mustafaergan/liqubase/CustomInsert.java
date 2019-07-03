package com.mustafaergan.liqubase;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class CustomInsert  implements CustomTaskChange {
    private String name;
    private String city;

    @Override
    public void execute(Database database) throws CustomChangeException {
        JdbcConnection databaseConnection = (JdbcConnection) database.getConnection();
        try {
            PreparedStatement stmt = databaseConnection.prepareStatement("INSERT INTO CUSTOMER VALUES (?, ?, ?)");
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, getName());
            stmt.setString(3, getCity());
            stmt.executeUpdate();
            stmt.close();
        } catch (DatabaseException e) {
            throw new CustomChangeException(e);
        } catch (SQLException e) {
            throw new CustomChangeException(e);
        }
    }

    @Override
    public void setUp() {
        // Do nothing
    }
    @Override
    public String getConfirmationMessage() {
        return null;
    }


    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        // Do nothing
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
