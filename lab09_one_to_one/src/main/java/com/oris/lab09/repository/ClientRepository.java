package com.oris.lab09.repository;

import com.oris.lab09.model.Client;
import com.oris.lab09.model.ClientInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    final static Logger logger = LogManager.getLogger(ClientRepository.class);
    //private final DbWork db = DbWork.getInstance();


    public Client findById(Long id) {
        Connection connection = null;
        try {
            connection = DbWork.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client, client_info WHERE client_info.id = client.id AND client.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Client(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"), new ClientInfo(resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("passport")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAll() {
        Connection connection = null;
        List<Client> clients = new ArrayList<>();
        try {
            connection = DbWork.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client, client_info WHERE client_info.id = client.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"), new ClientInfo(resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("passport")));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client addClient(Client client) {
        try {
            Connection connection = DbWork.getConnection();
            Long id = null;
            PreparedStatement statement = connection.prepareStatement("select nextval('client_seq')");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("nextval");
            }
            resultSet.close();

            if (id != null) {
                statement = connection.prepareStatement("insert into client (id, name, email) values ( ?, ?, ?)");
                statement.setLong(1, id);
                statement.setString(2, client.name());
                statement.setString(3, client.email());
                statement.executeUpdate();

                if (client.clientInfo() != null) {
                    statement = connection.prepareStatement("insert into client_info (id, phone, address, passport) values ( ?, ?, ?, ?)");
                    statement.setLong(1, id);
                    statement.setString(2, client.clientInfo().phone());
                    statement.setString(3, client.clientInfo().address());
                    statement.setString(4, client.clientInfo().passport());
                    statement.executeUpdate();
                }
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return client;
    }

}
