package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Role extends AbsEntity {
    private String name;

    public Role(String userName) {
        this.name = userName;
    }
}
