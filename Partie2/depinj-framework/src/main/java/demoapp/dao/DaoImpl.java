package demoapp.dao;

import framework.annotations.Component;

@Component("d")
public class DaoImpl implements IDao {
    public double getValue() {
        return 5;
    }
}