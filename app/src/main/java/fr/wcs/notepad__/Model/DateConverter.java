package fr.wcs.notepad__.Model;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;


/**
 * <h1>DateConverter</h1>
 * <p>
 *     Cette class permet de convertir la date en une chaine de caractère
 *
 * </p>
 */
public class DateConverter {


    /**
     *
     * @param value récupere un string sout forma date et le trasforme en LocalDate
     * @return LocalDate ou null si la <b>value</b> est pas dans le bon format
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        return value == null ? null : LocalDate.parse(value);
    }

    /**
     *
     * @param date récupère une LocalDate
     * @return la date en chaine de caratère
     */
    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        return date == null ? null : date.toString();
    }
}