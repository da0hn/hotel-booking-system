package com.hotel.booking.system.hotel.service.data.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 6868561067067809596L;

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Size(max = 50)
  @NotNull
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Size(max = 100)
  @NotNull
  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @Size(max = 9)
  @NotNull
  @Column(name = "hotel_cep", nullable = false, length = 9)
  private String hotelCep;

  @Size(max = 50)
  @NotNull
  @Column(name = "hotel_street", nullable = false, length = 50)
  private String hotelStreet;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "locality_id", nullable = false)
  private LocalityEntity locality;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private HotelCategoryEntity category;

}
