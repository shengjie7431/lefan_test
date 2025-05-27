package com.lefancrm.apicenter.model;

public class BlameQuotiety {
    private Long id;

    private String accidentBlame;

    private Double vehicleToVehicle;

    private Double vehicleToUnvehicle;

    private Double hvehicleToVehicle;

    private Double hvehicleToUnvehicle;

    public Double getHvehicleToVehicle() {
        return hvehicleToVehicle;
    }

    public void setHvehicleToVehicle(Double hvehicleToVehicle) {
        this.hvehicleToVehicle = hvehicleToVehicle;
    }

    public Double getHvehicleToUnvehicle() {
        return hvehicleToUnvehicle;
    }

    public void setHvehicleToUnvehicle(Double hvehicleToUnvehicle) {
        this.hvehicleToUnvehicle = hvehicleToUnvehicle;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccidentBlame() {
        return accidentBlame;
    }

    public void setAccidentBlame(String accidentBlame) {
        this.accidentBlame = accidentBlame;
    }

    public Double getVehicleToVehicle() {
        return vehicleToVehicle;
    }

    public void setVehicleToVehicle(Double vehicleToVehicle) {
        this.vehicleToVehicle = vehicleToVehicle;
    }

    public Double getVehicleToUnvehicle() {
        return vehicleToUnvehicle;
    }

    public void setVehicleToUnvehicle(Double vehicleToUnvehicle) {
        this.vehicleToUnvehicle = vehicleToUnvehicle;
    }
}