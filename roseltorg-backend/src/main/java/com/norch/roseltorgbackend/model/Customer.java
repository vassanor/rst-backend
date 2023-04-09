package com.norch.roseltorgbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "inn")
    private Long inn;

    @Column(name = "procedure_qty")
    private Integer procedure_qty;

    @Column(name = "win_part, %")
    private Integer win_part;

    @Column(name = "registration_date")
    private String registration_date;

    @Column(name = "license_activity_type")
    private String license_activity_type;

    @Column(name = "avg_staff_qty")
    private Integer avg_staff_qty;

    @Column(name = "life_time, year")
    private Integer life_time;

    @Column(name = "msp_type")
    private String msp_type;
    @Column(name = "msp_category")
    private String msp_category;



}
