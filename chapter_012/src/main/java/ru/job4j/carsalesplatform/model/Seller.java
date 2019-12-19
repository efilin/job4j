package ru.job4j.carsalesplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @NaturalId
    private String login;
    private String password;
    private long phone;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellingCar> carsForSale = null;
}
