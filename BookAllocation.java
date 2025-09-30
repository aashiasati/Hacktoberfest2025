import java.util.Scanner;

public class BookAllocation {

    // Function to check if current allocation is possible
    public static boolean isPossible(int[] books, int students, int maxPages) {
        int studentCount = 1;
        int pageSum = 0;

        for (int pages : books) {
            if (pages > maxPages) return false; // single book exceeds limit
            if (pageSum + pages > maxPages) {
                studentCount++;
                pageSum = pages;
            } else {
                pageSum += pages;
            }
        }
        return studentCount <= students;
    }

    // Function to find minimum of maximum pages
    public static int allocateBooks(int[] books, int students) {
        int sum = 0;
        for (int pages : books) sum += pages;

        int start = 0, end = sum, result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isPossible(books, students, mid)) {
                result = mid;
                end = mid - 1; // try for better minimum
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();

        int[] books = new int[n];
        System.out.println("Enter pages in each book:");
        for (int i = 0; i < n; i++) {
            books[i] = sc.nextInt();
        }

        System.out.print("Enter number of students: ");
        int students = sc.nextInt();

        int minMaxPages = allocateBooks(books, students);

        System.out.println("✅ Minimum of maximum pages allocated to a student: " + minMaxPages);

        sc.close();
    }
}
