package database.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hero", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Hero {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "varchar(60)")
    private String id;

    @Column(name = "name",columnDefinition = "varchar(191)", unique = true)
    @NotNull
    private String name;

    @Column
    private String description;

    @Column
    private String image;

    @Column
    private String thumbnail;

    @Column
    private long created_at;

    @Column
    private long updated_at;

    @Column
    private long deleted_at;

    @Column
    private int status;

    public Hero() {
        long now = System.currentTimeMillis();
        created_at = now;
        updated_at = now;
        status = HeroStatus.ACTIVE.getInt();
    }


    public Hero(String name, String description, String image, String thumbnail) {
        long now = System.currentTimeMillis();
        this.name = name;
        this.description = description;
        this.image = image;
        this.thumbnail = thumbnail;
        created_at = now;
        updated_at = now;
        status = HeroStatus.ACTIVE.getInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public long getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(long deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

enum  HeroStatus{
    ACTIVE(1), REMOVED(0);
    private int code;
    HeroStatus(int code){
        this.code = code;
    }

    public int getInt(){
        return code;
    }
}

