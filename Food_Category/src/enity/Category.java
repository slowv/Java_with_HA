package enity;

import java.util.ArrayList;
import java.util.UUID;

public class Category {
    private String id;
    private String code;
    private String name;
    private String description;

    public Category() {
    }

    public Category(String code,String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public  Category findCategory(ArrayList<Category> categories, String code){
        Category category = new Category();
        if (!categories.isEmpty()){
            for (Category category1 : categories) {
                if (code.equals(category1.code)) {
                    category =  category1;
                    break;
                }
                category = null;
            }
        }
        return category;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
