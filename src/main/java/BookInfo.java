import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.StringJoiner;

public class BookInfo {
    private String title;
    private List<String> authors;

    public String getTitle() {
        return title;
    }


    public String getAuthors() {
        StringJoiner result = new StringJoiner(", ");

        for(String author : authors) {
            result.add(author);
        }

        return result.toString();
    }
}
