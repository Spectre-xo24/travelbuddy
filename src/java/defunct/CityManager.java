/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defunct;

import JavaClasses.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darkness
 */
public class CityManager {
    
    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cities");
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                int cityID = resultSet.getInt("cityid");
                String cityName = resultSet.getString("cityname");
                double lat = resultSet.getFloat("lat");
                double lng = resultSet.getFloat("lng");
                cities.add(new City(cityID, cityName, lat, lng));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    
    public void addCity(City city) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO cities (cityID,cityname,lat,lng) VALUES(?,?,?,?)")){
            preparedStatement.setInt(1,city.getCityID());
            preparedStatement.setString(2,city.getCityName());
            preparedStatement.setDouble(3,city.getLat());
            preparedStatement.setDouble(4,city.getLng());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void addDefaultCities() throws SQLException {
        City london = new City("London",51.5,-0.1);
        City birmingham = new City("Birmingham",52.4,-1.9);
        addCity(london);
        addCity(birmingham);
    }
}
