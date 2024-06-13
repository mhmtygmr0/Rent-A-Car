package dao;

import core.Db;
import entity.Car;
import entity.Model;

import java.sql.*;
import java.util.ArrayList;

public class CarDao {
    private final Connection connect;
    private final BrandDao brandDao;
    private final ModelDao modelDao;

    public CarDao() {
        this.connect = Db.getInstance();
        this.brandDao = new BrandDao();
        this.modelDao = new ModelDao();
    }

    public Car getById(int id) {
        Car obj = null;
        String quary = "SELECT * FROM public.car WHERE car_id = ?";
        try {
            PreparedStatement pr = this.connect.prepareStatement(quary);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) obj = this.match(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Car> findAll() {
        return this.selectByQuery("SELECT * FROM public.car ORDER BY car_id ASC");
    }

    public ArrayList<Car> selectByQuery(String query) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet rs = this.connect.createStatement().executeQuery(query);
            while (rs.next()) {
                cars.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private Car match(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setModel_id(rs.getInt("car_model_id"));
        car.setPlate(rs.getString("car_plate"));
        car.setColor(Car.Color.valueOf(rs.getString("car_color")));
        car.setKm(rs.getInt("car_km"));
        car.setModel(this.modelDao.getByID(car.getModel_id()));
        return car;
    }

    public boolean update(Car car) {
        String query = "UPDATE public.car SET " +
                "car_model_id = ? , " +
                "car_color = ? , " +
                "car_km = ? , " +
                "car_plate = ? " +
                "WHERE car_id = ?";

        try {
            PreparedStatement pr = connect.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            pr.setInt(5, car.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(Car car) {
        String query = "INSERT INTO public.car " +
                "(car_model_id, car_color, car_km, car_plate) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pr = connect.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int carId) {
        String quary = "DELETE FROM public.car WHERE car_id = ?";
        try {
            PreparedStatement pr = connect.prepareStatement(quary);
            pr.setInt(1, carId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
