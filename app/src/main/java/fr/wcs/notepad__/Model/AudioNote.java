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

    public AudioNote(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public AudioNote setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AudioNote setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPath() {
        return path;
    }

    public AudioNote setPath(String path) {
        this.path = path;
        return this;
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public AudioNote setCatalogueId(long catalogueId) {
        this.catalogueId = catalogueId;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public AudioNote setFavorite(boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    public LocalDate getLastModif() {
        return lastModif;
    }

    public AudioNote setLastModif(LocalDate lastModif) {
        this.lastModif = lastModif;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public AudioNote setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
