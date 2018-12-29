package model.dbclasses;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "CAR")
public class Car extends SpeedBy {

    @NotNull
    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "car")
    private Set<CarBrand> carBrands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CarBrand> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(Set<CarBrand> carBrands) {
        this.carBrands = carBrands;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.name == ((Car) obj).name);
    }
}
