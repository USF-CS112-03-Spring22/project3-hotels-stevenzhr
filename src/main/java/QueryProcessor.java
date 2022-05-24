import java.util.Scanner;

public class QueryProcessor {
    // FILL IN CODE:
    // Add instance variables(s) as needed

    // Add a constructor - it should take a parameter - a reference to the class that stores hotels

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

        }
    }

    public static void main(String[] args) {
        // FILL IN CODE:
        String hotelFilename = args[0]; // the path to the json file with hotels is given as a command line argument

        // Create an instance of the class that stores hotels and load hotels from the json file

        // Add a parameter to the QueryProcessor constructor:
        // pass a reference to your class that contains hotels
        QueryProcessor qp = new QueryProcessor();
        qp.processQueries();

        // Note that you are expected to create additional classes and methods

    }

}
