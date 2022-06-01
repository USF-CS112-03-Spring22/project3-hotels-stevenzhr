import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;



public class QueryProcessor {
    // FILL IN CODE:
    // Add instance variables(s) as needed
    private static HashMap<String, HotelInfo> hotelList;
    // Constructor - it should take a parameter - a reference to the class that stores hotels
    public QueryProcessor(HashMap<String, HotelInfo> hotelList) {
        QueryProcessor.hotelList = hotelList;
    }

    public void processQueries() {
        System.out.println("Enter a query:");
        System.out.println("show hotels");
        System.out.println("search");
        System.out.println("reserve hotelId date numberOfNights");
        System.out.println("review hotelId rating username");
        System.out.println("exit");
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        while (!query.equals("exit")) {
            // FILL IN CODE to read and parse user's query and print a response
            String[] querys = query.split(" ");
            switch (querys[0]) {
                case "show":
                    showHotels();
                    break;
                case "search":
                    System.out.println("Enter the name of the hotel: ");
                    String hotelName = sc.nextLine();
                    searchHotel(hotelName);
                    break;
                case "reserve":
                    reserveHotel(querys);
                    break;
                case "review":
                    reviewHotel(querys);
                    break;
                default:
                    System.out.println("Invalid command. ");
                    break;
            }
            query = sc.nextLine();
        }
        sc.close();
    }


    /**
     * Shwo all hotel's name, ID and address. 
     */
    public void showHotels() {
        for (HotelInfo hotel : hotelList.values()) {
            utilities.printLines(HotelsUtil.getHotelInfo(hotel, false));
            System.out.println();
        }
    }

    /**
     * Search hotel by name, print out hotel's information and reviews. 
     * @param hotelName
     */
    public void searchHotel(String hotelName) {
        HotelInfo hotel = hotelList.get(HotelsUtil.getHotelIDbyName(hotelList, hotelName));
        if (hotel != null) {
            List<String> sout_strs = HotelsUtil.getHotelInfo(hotel, true);
            utilities.printLines(sout_strs);
        }
        else
        {
            System.out.println("No such hotel.");
        }
    }

    /**
     * Parse the input strings. Base on the input add a new review for the hotel. 
     * @param strings
     */
    public void reviewHotel(String[] strings) {
        // Verify the syntax. Check rating is a number. 
        if (strings.length != 4 || !utilities.isNumeric(strings[2])) {
            System.out.println("Invalid command. ");
            return;
        }
        // Get guest name in Strings
        String guestName = strings[3];
        for (int i = 4; i < strings.length; i++) {
            guestName = guestName.concat(" ").concat(strings[i]);
        }
        // Get hotel by hotel ID
        HotelInfo hotel = hotelList.get(strings[1]);
        if (hotel != null) {
            System.out.println("Enter the text of the review:");
            Scanner sc = new Scanner(System.in);
            String review = sc.nextLine();
            hotel.addReview(guestName, utilities.getCurrentDate(), strings[2], review);
        }
        else
        {
            System.out.println("No such hotel.");
            return;
        }
    }

    public void reserveHotel(String[] strings) {
        // Command: 
        //     String[0] = reserve; String[1] = hotelId
        //     String[2] = date;    String[3] = numberOfNight

        // Verify the syntax. Check numberOfNight is a number. 
        if (strings.length != 4 || !utilities.isNumeric(strings[3])) {
            System.out.println("Invalid command. ");
            return;
        }
        // Verify the date format. 
        if (!utilities.isValidDate(strings[2])) {
            System.out.println("Invalid date. ");
            return;
        }
        // Get reserve date list. 
        String[] reserveDates = utilities.calculateDate(strings[2], strings[3]);
        // Get hotel by hotel ID
        HotelInfo hotel = hotelList.get(strings[1]);
        if (hotel != null) {
            // Try to add reserve information into hotelInfo obj.
            if (hotel.addReserve(reserveDates)) {
                // Success add reservations. Print result.
                String sout_str = new StringBuilder().append("[").append(reserveDates[0]).toString();
                for (int i = 1; i < reserveDates.length; i++) {
                    sout_str = sout_str.concat(", ").concat(reserveDates[i]);
                }
                sout_str = sout_str.concat("]");
                System.out.println(sout_str);
                System.out.println("Reservation successful");
                return;
            }
            else
            {
                System.out.println("The hotel is not available on some of these days");
                return;
            }
        }
        else
        {
            System.out.println("No such hotel.");
            return;
        }

    }

    public static void main(String[] args) throws IOException {
        // FILL IN CODE:
        String hotelFilename = args[0]; // the path to the json file with hotels is given as a command line argument

        // Create an instance of the class that stores hotels and load hotels from the json file
        HashMap<String, HotelInfo> hotelList = HotelsUtil.getHotelListFromJson(hotelFilename);

        // Add a parameter to the QueryProcessor constructor:
        // pass a reference to your class that contains hotels
        QueryProcessor qp = new QueryProcessor(hotelList);
        qp.processQueries();

        // Note that you are expected to create additional classes and methods

    }



}
