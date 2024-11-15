package com.example.crowdfunding.project;

import com.example.crowdfunding.business.Business;
import com.example.crowdfunding.project.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "Projects")
public class Project {

    @MongoId(value = FieldType.OBJECT_ID)
    @JsonProperty
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @JsonProperty
    private String description;

    @JsonProperty
    private Category category;

    private BigDecimal raised;

    @DecimalMin(value ="0.00", inclusive = false)
    @Digits(integer = 7, fraction = 2)
    @JsonProperty
    private BigDecimal goal;

    public LocalDate daysLeft;

    @Valid
    @JsonProperty
    private Business projectOwner;

    public Project(String name, String description, Category category, BigDecimal goal, Business projectOwner) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.goal = goal;
        this.projectOwner = projectOwner;
    }


}
