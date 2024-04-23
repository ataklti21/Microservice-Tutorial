package com.atuka.eurekaserver.managecustomer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_Id_Sequence",
            sequenceName = "customer_Id_Sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_Id_Sequence")
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
}
