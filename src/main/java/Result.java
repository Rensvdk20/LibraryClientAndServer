import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    private String kind;
    private String totalItems;
    @SerializedName("items")
    private List<Book> books;

    public Book getBook() {
        if(books != null) {
            return books.get(0);
        } return null;
    }
}
