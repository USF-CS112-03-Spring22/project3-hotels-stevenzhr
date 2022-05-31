/**
 * Review class contain each review's guest name, review date, rating and text. 
 * 
 */
public class Review {
    private final String guestName;
    private final String date;
    private final String Rating;
    private final String review;
    
    public String getGuestName() {
        return guestName;
    }

    public String getDate() {
        return date;
    }

    public String getRating() {
        return Rating;
    }

    public String getReview() {
        return review;
    }

    /**
     * Print a review.
     * @return a string array contain 3 strings each represent a printline text. 
     */
    public String[] soutReview() {
        String[] soutLines = new String[3];
        soutLines[0] = new StringBuilder().append("Review by ").append(guestName)
                .append(" on ").append(date).toString();
        soutLines[1] = new StringBuilder().append("Rating: ").append(Rating).toString();
        soutLines[2] = review;
        return soutLines;
    }

    public Review(String guestName, String date, String rating, String review) {
        this.guestName = guestName;
        this.date = date;
        Rating = rating;
        this.review = review;
    }

    
}