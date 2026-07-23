package com.ivanna.charactercodex.entity;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="armors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Pattern(
            regexp = "^\\d+d\\d+$",
            message = "defense must follow base dice notation, e.g. '1d4', '2d6', '1d8'"
    )
    @Column(nullable = false, length = 20)
    private String defense;
}
