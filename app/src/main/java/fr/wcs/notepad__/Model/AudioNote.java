package fr.wcs.notepad__.Model;

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
public class AudioNote {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "catalogueId")
    private long catalogueId;

    @ColumnInfo(name = "favorite")
    private boolean favorite;

    @ColumnInfo(name = "lastModif")
    private LocalDate lastModif;

    @ColumnInfo(name = "isDeleted")
    private boolean isDeleted;

    public AudioNote(){}

    public AudioNote(String title, String path, Long catalogueId) {
        this.title = title;
        this.path = path;
        this.catalogueId = catalogueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(long catalogueId) {
        this.catalogueId = catalogueId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public LocalDate getLastModif() {
        return lastModif;
    }

    public void setLastModif(LocalDate lastModif) {
        this.lastModif = lastModif;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}
