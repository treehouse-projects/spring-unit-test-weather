package com.teamtreehouse.domain;

import javax.persistence.*;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String formattedAddress;
    private String placeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public static class FavoriteBuilder {
        private Long id;
        private String formattedAddress;
        private String placeId;

        public FavoriteBuilder(Long id) {
            this.id = id;
        }

        public FavoriteBuilder() {}

        public FavoriteBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FavoriteBuilder withAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
            return this;
        }

        public FavoriteBuilder withPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Favorite build() {
            Favorite fave = new Favorite();
            fave.setId(id);
            fave.setFormattedAddress(formattedAddress);
            fave.setPlaceId(placeId);
            return fave;
        }
    }
}
