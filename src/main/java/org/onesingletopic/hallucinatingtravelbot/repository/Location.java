package org.onesingletopic.hallucinatingtravelbot.repository;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Location{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Float lat_coord;
    private Float lon_coord;
    private String description;

    protected Location() {}

    public Location(String name, Float lat_coord, Float lon_coord, String description) {
        this.name = name;
        this.lat_coord = lat_coord;
        this.lon_coord = lon_coord;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Location[id=%d, name='%s', lat_coord='%f', lat_coord='%f', description='%s']",
                id, name, lat_coord, lon_coord, description);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Float getLat_coord() {
        return lat_coord;
    }

    public Float getLon_coord() {
        return lon_coord;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLat_coord(Float lat_coord) {
        this.lat_coord = lat_coord;
    }

    public void setLon_coord(Float lon_coord) {
        this.lon_coord = lon_coord;
    }

    public void setDescription(String text) {
        this.description = text;
    }
}
