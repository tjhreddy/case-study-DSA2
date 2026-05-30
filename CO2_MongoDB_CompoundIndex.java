public class CO2_MongoDB_CompoundIndex {

    public static void main(String[] args) {

        System.out.println("MongoDB Compound Index Analysis");

        System.out.println("\nQuery 1: user_id = 42 AND timestamp range");
        System.out.println("Index Usable: Yes");

        System.out.println("\nQuery 2: user_id = 42");
        System.out.println("Index Usable: Yes");

        System.out.println("\nQuery 3: timestamp only");
        System.out.println("Index Usable: No (Inefficient)");

        double entries = 2e9;

        double height = Math.log(entries) / Math.log(100);

        System.out.println("\nEstimated B+ Tree Height = "
                + Math.ceil(height));

        int pageReads = 4;

        System.out.println("Approximate Cost = "
                + pageReads + " page reads");

        System.out.println("\nConclusion:");
        System.out.println("Prefix field user_id must be used "
                + "for efficient index access.");
    }
}
