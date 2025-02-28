package id.ac.ui.cs.advprog.eshop.lib.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ModelAbstract {
    private String id;
    private String name;
    private int quantity;

    public ModelAbstract() {
        this.id = java.util.UUID.randomUUID().toString();
    }
}
