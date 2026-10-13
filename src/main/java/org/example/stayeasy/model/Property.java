package org.example.stayeasy.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    private String title;
    private String description;
    private String adress;

    //tillåter totalt 10 siffror med 2 decimaler
    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    private Long hostId;
    private Long maxGuests;

    public Property(String title, Long id, String description, String adress, BigDecimal pricePerNight, Long hostId, Long maxGuests) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.adress = adress;
        this.pricePerNight = pricePerNight;
        this.hostId = hostId;
        this.maxGuests = maxGuests;
    }

    public Property() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Long maxGuests) {
        this.maxGuests = maxGuests;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", hostId=" + hostId +
                ", maxGuests=" + maxGuests +
                '}';
    }
}
