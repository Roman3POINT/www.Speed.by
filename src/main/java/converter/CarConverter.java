package converter;

import model.daoclasses.CarDAO;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "com.Speed.by.CarConverter")
public class CarConverter implements Converter {

    @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
            CarDAO carDAO = new CarDAO(HibernateUtil.getSessionFactory());
            return carDAO.get(Car.class, Long.parseLong(s));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object car) {
            return ((Car) car).getId().toString();
        }
}