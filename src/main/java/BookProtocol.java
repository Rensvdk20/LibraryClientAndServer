import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class BookProtocol {
    private static final int WAITING = 0;
    private static final int RECEIVEDISBN = 1;

    private int state = WAITING;

    public String processInput(String input) throws Exception {
        String output = null;

        if(state == WAITING) {
            output = "Please fill in a ISBN number for the book you would like to see";
            state = RECEIVEDISBN;
        } else if(state == RECEIVEDISBN) {

            URL bookURL = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + input.replaceAll("\\s",""));

            InputStream inputStream = bookURL.openStream();
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            Result bookResult = new Gson().fromJson(reader, Result.class);

            if(bookResult.getBook() != null && !input.isEmpty()) {
                Book book = bookResult.getBook();
                BookInfo bookDetails = book.getVolumeInfo();
                output = "Title: " + bookDetails.getTitle() + ", Subtitle: " + bookDetails.getSubTitle() + ", Author(s): " + bookDetails.getAuthors();
            } else {
                output = "This ISBN number does not exist. Please try again";
            }
        }

        return output;
    }
}
