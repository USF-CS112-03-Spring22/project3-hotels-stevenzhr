import java.util.ArrayList;
import java.util.List;

public class HotelInfo {
    // Hotel name
    private final String f;
    // Hotel id
    private final String id;
    // State
    private final String pr;
    // City
    private final String ci;
    // Street address
    private final String ad;
    // Review list
    private List<Review> reviewList;
    // Reserve list
    private List<String> reserveList;

    public String getId() {
        return id;
    }
    public String getName() {
        return f;
    }
    public String getAddress() {
        return ad;
    }
    public String getCity() {
        return ci;
    }
    public List<Review> getReviewList() {
        return reviewList;
    }

    public HotelInfo(String f, String id, String pr, String ci, String ad) {
        this.f = f;
        this.id = id;
        this.pr = pr;
        this.ci = ci;
        this.ad = ad;
        this.reviewList = new ArrayList<>();
        this.reserveList = new ArrayList<>();
    }

    /**
     * Add a review into the reviewList
     * @param guestName
     * @param date
     * @param rating
     * @param review
     */
    public void addReview(String guestName, String date, String rating, String review) {
        // Later Upgrade: add info verification process. 
        if (reviewList == null) {
            this.reviewList = new ArrayList<>();
        }
        reviewList.add(new Review(guestName, date, rating, review));
    }

    /**
     * Try to add a list of reserve into reserveList. If success return true, else return false. 
     * <p><b>Date type has to be verified before pass to the method.</b></p>
     * @param date
     * @return operation status
     */
    public boolean addReserve(String[] reserveDates) {
        if (reserveList == null) {
            reserveList = new ArrayList<>();
        }
        // Detection date conflict
        for (String date : reserveDates) {
            if (reserveList.contains(date)) {
                return false;
            }
        }
        // Add reserve information
        for (String date : reserveDates) {
            reserveList.add(date);
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("{Hotel")
                .append("name: ").append(f)
                .append(", id: ").append(id)
                .append(", state: ").append(pr)
                .append(", city: ").append(ci)
                .append(", address: ").append(ad)
                .append("}").toString();
    }
}

