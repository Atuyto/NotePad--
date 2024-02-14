package fr.wcs.notepad__.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.*;

@Entity(tableName = "catalogue")
public class Catalogue {
    @PrimaryKey(autoGenerate = true)
    private long catalogueId;

    @ColumnInfo(name = "name")
    private String name;

    public Catalogue() {
        this.name = "Dossier";
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(long catalogueId) {
        this.catalogueId = catalogueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
