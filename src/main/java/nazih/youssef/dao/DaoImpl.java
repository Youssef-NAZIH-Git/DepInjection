package nazih.youssef.dao;

import org.springframework.stereotype.Component;

@Component("d")
public class DaoImpl implements IDao {
    public double getValue() {
        return 5;
    }
}