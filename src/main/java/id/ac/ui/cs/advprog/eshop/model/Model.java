package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Model {
    private String id;
    private String name;
    private int quantity;

    public Model() {
        this.id = java.util.UUID.randomUUID().toString();
    }
}
