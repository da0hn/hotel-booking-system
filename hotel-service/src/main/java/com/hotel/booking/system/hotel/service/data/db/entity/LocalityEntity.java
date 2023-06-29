package com.hotel.booking.system.hotel.service.data.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "locality")
public class LocalityEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 7998171707396579897L;

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Size(max = 50)
  @NotNull
  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Size(max = 50)
  @NotNull
  @Column(name = "state", nullable = false, length = 50)
  private String state;

  @Size(max = 50)
  @NotNull
  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @OneToMany(mappedBy = "locality")
  private Set<HotelEntity> hotels = new LinkedHashSet<>();

}
