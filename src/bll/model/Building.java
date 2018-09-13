package bll.model;

public final class Building {

    private final String buildingNumber;


    public Building(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }
}
