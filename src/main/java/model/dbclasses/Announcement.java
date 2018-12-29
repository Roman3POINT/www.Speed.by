package model.dbclasses;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "ANNOUNCEMENT")
public class Announcement extends SpeedBy {

    @NotNull
    @Column(name = "Price", nullable = false)
    private int price;

    @NotNull
    @Column(name = "Engine", nullable = false, length = 20)
    private String engine;

    @NotNull
    @Column(name = "Mileage", nullable = false)
    private int mileage;

    @NotNull
    @Column(name = "Color", nullable = false)
    private String color;

    @NotNull
    @Column(name = "DataOfIssue", nullable = false)
    private Date dateOfIssue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CarBrand_ID")
    private CarBrand cars;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "User_ID", referencedColumnName = "ID")
    private User user;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarBrand getCars() {
        return cars;
    }

    public void setCars(CarBrand cars) {
        this.cars = cars;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.price == ((Announcement) obj).getPrice());
    }
}
