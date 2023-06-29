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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel_category")
public class HotelCategoryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -1126889002441038883L;
  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;
  @NotNull
  @Size(max = 50)
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  @OneToMany(mappedBy = "category")
  private Set<HotelEntity> hotels = new LinkedHashSet<>();

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final HotelCategoryEntity that = (HotelCategoryEntity) o;

    if (!this.id.equals(that.id)) return false;
    if (!Objects.equals(this.name, that.name)) return false;
    return Objects.equals(this.hotels, that.hotels);
  }

  @Override
  public int hashCode() {
    int result = this.id.hashCode();
    result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
    result = 31 * result + (this.hotels != null ? this.hotels.hashCode() : 0);
    return result;
  }
}
