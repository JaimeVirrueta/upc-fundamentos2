package Models;

import java.util.ArrayList;

public class Order extends Model {
    private int customerId;
    private int vehicleId;
    private int professionalId;
    private int bayId;
    private String startDate;
    private String endDate;
    private int mileage;
    private String orderType;
    private Double igv = 0.18;
    private ArrayList<Product> products;

    private ArrayList<Professional> professionals;


    public Order() {
        this.products = new ArrayList<>();
        this.professionals = new ArrayList<>();
    }

    public Order(
            int id,
            String name,
            int customerId,
            int vehicleId,
            int bayId,
            int professionalId,
            String orderType,
            String startDate,
            String endDate,
            int mileage
    ) {
        super.setCodigo(id);
        super.setNombre(name);
        this.setCustomerId(customerId);
        this.setVehicleId(vehicleId);
        this.setBayId(bayId);
        this.setProfessionalId(professionalId);
        this.setOrderType(orderType);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setMileage(mileage);
        this.products = new ArrayList<>();
        this.professionals = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public ArrayList<Professional> getProfessionals() {
        return professionals;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    public void addProfessional(Professional professional) {
        this.professionals.add(professional);
    }

    public void removeProfessional(Professional professional) {
        this.professionals.remove(professional);
    }
    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getBayId() {
        return this.bayId;
    }

    public void setBayId(int bayId) {
        this.bayId = bayId;
    }

    public int getProfessionalId() {
        return this.professionalId;
    }

    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String date) {
        this.endDate = date;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

}
