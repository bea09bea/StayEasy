package org.example.stayeasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, message = "Title must be at least 2 characters long")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Adress cannot be blank")
    @Column(nullable = false)
    private String adress;

    //validerar värdet innan det sparas
    @Digits(integer = 8, fraction = 2,
            message = "Price can have max 8 digits and 2 decimals")
    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.01",
            message = "Price per night must be greater than 0")
    //tillåter totalt 10 siffror med 2 decimaler
    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @NotNull(message = "Host ID is required")
    @Positive(message = "Host ID must be positive")
    private Long hostId;

    @NotNull(message = "Max guests is required")
    @Min(value = 1, message = "Max guests must be at least 1")
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
