package com.hotel.booking.system.hotel.service.data.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
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
@Builder
@Table(name = "hotel_category")
public class HotelCategoryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -1126889002441038883L;

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotNull
  @Size(max = 50)
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "category")
  private Set<HotelEntity> hotels = new LinkedHashSet<>();

}
