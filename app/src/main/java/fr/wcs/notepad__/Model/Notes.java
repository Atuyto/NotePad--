package fr.wcs.notepad__.Model;


import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.room.*;

import java.time.LocalDate;
@Entity(foreignKeys = {
        @ForeignKey(
                entity = Catalogue.class,
                parentColumns = "catalogueId",
                childColumns = "catalogueId",
                onDelete = ForeignKey.CASCADE
        )
})
@TypeConverters(DateConverter.class)
public class Notes {

    @PrimaryKey(autoGenerate = true)
    private Long idNotes;

    private String title;

    private String containerText;

    private long catalogueId;

    private LocalDate lastModif;

    private boolean isDeleted;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notes() {
        this.title = "";
        this.containerText = "";
        this.lastModif = LocalDate.now();
        isDeleted = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notes(String title, String containerText) {
        this.title = title;
        this.containerText = containerText;
        this.lastModif = LocalDate.now();
        isDeleted = false;
    }

    public Long getIdNotes() {
        return idNotes;
    }

    public void setIdNotes(Long idNotes) {
        this.idNotes = idNotes;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContainerText() {
        return containerText;
    }

    public void setContainerText(String containerText) {
        this.containerText = containerText;
    }

    public LocalDate getLastModif() {
        return lastModif;
    }

    public void setLastModif(LocalDate lastModif) {
        this.lastModif = lastModif;
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(long catalogueId) {
        this.catalogueId = catalogueId;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}