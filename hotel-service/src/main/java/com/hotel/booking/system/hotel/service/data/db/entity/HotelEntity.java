package com.hotel.booking.system.hotel.service.data.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class HotelEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 6868561067067809596L;

  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @Size(max = 50)
  @NotNull
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Size(max = 500)
  @NotNull
  @Column(name = "description", nullable = false, length = 500)
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
  @ManyToOne
  @JoinColumn(name = "locality_id", nullable = false)
  private LocalityEntity locality;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private HotelCategoryEntity category;

  @OneToMany(mappedBy = "hotel", orphanRemoval = true)
  private Set<RoomEntity> rooms = new LinkedHashSet<>();

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final HotelEntity that = (HotelEntity) o;

    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
