package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data

public class CarData {
    @Id
    @SequenceGenerator(name="carData_seq",sequenceName="carData_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carData_seq")
    @Column(name="carDataID",unique = true, nullable = false)
    private @NonNull Long carID;

    @NotNull
    @Size(min = 3,max = 15)
    @Column(unique = true)
    private String model;

    @NotNull
    @Size(min = 4,max = 10)
    @Pattern(regexp = "^[1-9][0-9]*cc$") // ขึ้นต้นด้วยตัวใหญ่เสมอแล้วตามด้วยอักษรตัวเล็กจะมีก็ได้ไม่มีก็ได้

    private String cC;



    @ManyToOne
    @JoinColumn(name = "branchID" ,nullable = false)
    private BranchCar branchCar;

    @ManyToOne
    @JoinColumn(name = "colorID" ,nullable = false)
    private CarColor carColor;

    @ManyToOne
    @JoinColumn(name = "carTypeID" ,nullable = false)
    private CarType carType;



    @ManyToOne
    @JoinColumn(name = "gearTypeID" ,nullable = false)
    private GearType gearType;

    public CarData() {
    }
    public CarData(@NonNull String model, @NonNull String cC, BranchCar branchCar, CarColor carColor, CarType carType, GearType gearType) {

        this.model = model;
        this.cC = cC;
        this.branchCar = branchCar;
        this.carColor = carColor;
        this.carType = carType;
        this.gearType = gearType;
    }
    public Long getCarID() {
        return carID;
    }

    public void setCarID(Long carID) {
        this.carID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BranchCar getBranchCar() {
        return branchCar;
    }

    public void setBranchCar(BranchCar branchCar) {
        this.branchCar = branchCar;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }



    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public String getcC() {
        return cC;
    }

    public void setcC(String cC) {
        this.cC = cC;
    }
}

