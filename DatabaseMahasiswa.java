import java.util.Scanner;

// Class untuk merepresentasikan data mahasiswa
class Mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;
    
    // Constructor
    public Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }
    
    // Getter dan setter
    public String getNim() {
        return nim;
    }
    
    public void setNim(String nim) {
        this.nim = nim;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getJurusan() {
        return jurusan;
    }
    
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}

// Class Node untuk LinkedList
class Node {
    Mahasiswa data;
    Node next;
    
    // Constructor
    public Node(Mahasiswa data) {
        this.data = data;
        this.next = null;
    }
}

// Class untuk merepresentasikan Single Linked List
class MahasiswaLinkedList {
    private Node head;
    private int size;
    private final int MAX_SIZE = 5;
    
    // Constructor
    public MahasiswaLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isFull() {
        return size >= MAX_SIZE;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public boolean push(Mahasiswa mhs) {
        if (isFull()) {
            System.out.println("\nMaaf, database sudah penuh (maksimal 5 data)");
            return false;
        }
        
        Node newNode = new Node(mhs);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        
        size++;
        return true;
    }
    
    public void popAll() {
        head = null;
        size = 0;
        System.out.println("\nSemua data mahasiswa berhasil dihapus!");
    }
    
    public void displaySorted() {
        if (isEmpty()) {
            System.out.println("\nDatabase mahasiswa kosong");
            return;
        }
        
        // Bubble Sort
        for (int i = 0; i < size - 1; i++) {
            Node current = head;
            Node index;
            
            while (current.next != null) {
                index = current.next;
                
                if (current.data.getNim().compareTo(index.data.getNim()) > 0) {
                    Mahasiswa temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
                
                current = current.next;
            }
        }
        
        // Tampilan Data Mahasiswa
        System.out.println("\nData Mahasiswa (diurutkan berdasarkan NIM):");
        System.out.println("\n===========================================================");
        System.out.printf("%-10s | %-30s | %-50s\n", "NIM", "NAMA", "JURUSAN");
        System.out.println("-----------------------------------------------------------");
        
        Node current = head;
        while (current != null) {
            System.out.printf("%-10s | %-30s | %-50s\n", 
                    current.data.getNim(), 
                    current.data.getNama(), 
                    current.data.getJurusan());
            current = current.next;
        }
        System.out.println("===========================================================");
    }
}

// Main class
public class DatabaseMahasiswa {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        MahasiswaLinkedList database = new MahasiswaLinkedList();
        int pilihan;
        
        // Tampilan animasi kredit
        System.out.println("=================================");
        typewriter("   Made By Ravano Akbar Widodo", 30);
        System.out.println("=================================");

        
        do {
            tampilkanMenu();
            System.out.print("\nPilihan Anda: ");
            pilihan = getIntInput();
            
            switch (pilihan) {
                case 1:
                    pushData(database);
                    break;
                case 2:
                    database.displaySorted();
                    break;
                case 3:
                    database.popAll();
                    break;
                case 4:
                    typewriter("\nTerima kasih telah menggunakan program ini!", 30);
                    typewriter("\nMade By Ravano Akbar Widodo", 30);
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
            
            System.out.println(); // Baris kosong untuk spasi
            pressEnterToContinue();
        } while (pilihan != 4);
        
        scanner.close();
    }
    
    // Animasi ketik
    private static void typewriter(String text, int delay) {
        for (char ch : text.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
    
    private static void tampilkanMenu() {
        System.out.println("\n=== DATABASE MAHASISWA ===");
        System.out.println("1. Push Data Mahasiswa");
        System.out.println("2. Tampilkan Data Mahasiswa (sort by NIM)");
        System.out.println("3. Pop Semua Data Mahasiswa");
        System.out.println("4. Keluar");
        System.out.println("=========================");
        typewriter("\nMade By Ravano Akbar Widodo", 30);
    }
    
    private static void pushData(MahasiswaLinkedList database) {
        if (database.isFull()) {
            System.out.println("\nDatabase sudah penuh (maksimal 5 data)");
            return;
        }
        
        System.out.println("\n=== INPUT DATA MAHASISWA ===");
        
        String nim;
        do {
            System.out.print("\nMasukkan NIM (maksimal 10 digit): ");
            nim = scanner.nextLine();
            if (nim.length() > 10) {
                System.out.println("Error: NIM terlalu panjang (maksimal 10 digit)");
            }
        } while (nim.length() > 10);
        
        String nama;
        do {
            System.out.print("\nMasukkan Nama (maksimal 30 karakter): ");
            nama = scanner.nextLine();
            if (nama.length() > 30) {
                System.out.println("Error: Nama terlalu panjang (maksimal 30 karakter)");
            }
        } while (nama.length() > 30);
        
        String jurusan;
        do {
            System.out.print("\nMasukkan Jurusan (maksimal 50 karakter): ");
            jurusan = scanner.nextLine();
            if (jurusan.length() > 50) {
                System.out.println("Error: Jurusan terlalu panjang (maksimal 50 karakter)");
            }
        } while (jurusan.length() > 50);
        
        Mahasiswa mhs = new Mahasiswa(nim, nama, jurusan);
        if (database.push(mhs)) {
            System.out.println("\nData mahasiswa berhasil ditambahkan!");
        }
    }
    
    private static int getIntInput() {
        int input = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid. Masukkan angka: ");
            }
        }
        
        return input;
    }
    
    private static void pressEnterToContinue() {
        System.out.print("\nTekan ENTER untuk melanjutkan...");
        scanner.nextLine();
    }
}
