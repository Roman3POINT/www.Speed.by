package converter;

import model.daoclasses.CarBrandDAO;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "com.Speed.by.CarBrandConverter")
public class CarBrandConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CarBrandDAO carBrandDAO = new CarBrandDAO(sessionFactory);
        return carBrandDAO.get(CarBrand.class, Long.parseLong(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object carBrand) {
        return ((CarBrand) carBrand).getId().toString();
    }
}