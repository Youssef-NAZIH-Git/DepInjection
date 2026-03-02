package demoapp.metier;

import demoapp.dao.IDao;
import framework.annotations.Autowired;
import framework.annotations.Component;

@Component
public class MetierImpl
        implements IMetier {
    @Autowired
    private IDao dao;
    public double calcul() {
        double nb=dao.getValue();
        return 2*nb;
    }}