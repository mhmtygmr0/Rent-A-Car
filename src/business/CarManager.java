package business;

import core.Helper;
import dao.CarDao;
import entity.Car;

import java.util.ArrayList;

public class CarManager {

    private final CarDao carDao;

    public CarManager() {
        this.carDao = new CarDao();
    }

    public Car getById(int id) {
        return this.carDao.getById(id);
    }
    public ArrayList<Car> findAll(){
        return this.carDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Car> cars) {
        ArrayList<Object[]> carList = new ArrayList<>();
        for (Car obj : cars) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getModel().getBrand().getName();
            rowObject[i++] = obj.getModel().getName();
            rowObject[i++] = obj.getPlate();
            rowObject[i++] = obj.getColor();
            rowObject[i++] = obj.getKm();
            rowObject[i++] = obj.getModel().getYear();
            rowObject[i++] = obj.getModel().getType();
            rowObject[i++] = obj.getModel().getFuel();
            rowObject[i++] = obj.getModel().getGear();
            carList.add(rowObject);
        }
        return carList;
    }

    public boolean save(Car car) {
        if (this.getById(car.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.carDao.save(car);
    }

    public boolean update(Car car) {
        if (this.getById(car.getId()) == null) {
            Helper.showMsg(car.getId() + "ID kayıtlı araç bulunamadı!!!");
            return false;
        }
        return this.carDao.update(car);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + "ID kayıtlı araç bulunamadı!!!");
            return false;
        }
        return this.carDao.delete(id);
    }

}
