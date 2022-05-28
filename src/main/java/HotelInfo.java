//import java.util.HashMap;

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

    public String getName() {
        return f;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return pr;
    }

    public String getCity() {
        return ci;
    }

    public String getAddress() {
        return ad;
    }

    public HotelInfo(String f, String id, String pr, String ci, String ad) {
        this.f = f;
        this.id = id;
        this.pr = pr;
        this.ci = ci;
        this.ad = ad;
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
