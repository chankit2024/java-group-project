import java.util.Scanner;

public class Main {
    static String[] inventoryIds = new String[100];
    static String[] inventoryNames = new String[100];
    static double[] inventoryPrices = new double[100];
    static int[] inventoryQuantities = new int[100];
    static int inventorySize = 0;

    static String[] cartNames = new String[100];
    static double[] cartPrices = new double[100];
    static int[] cartQuantities = new int[100];
    static int cartSize = 0;

    static int lastUsedId = 0;

    // แม็ค นายชาญกิจ สมผิว 672110227

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (true) {
            System.out.println("\n--- ระบบจัดการร้านค้า ---");
            System.out.println("1. แสดงรายการสินค้าในคลัง");
            System.out.println("2. เพิ่มสินค้าในคลัง");
            System.out.println("3. ลบสินค้าในคลัง");
            System.out.println("4. แก้ไขข้อมูลสินค้าในคลัง");
            System.out.println("5. เพิ่มสินค้าในตะกร้าจากคลัง");
            System.out.println("6. ลบสินค้าในตะกร้า");
            System.out.println("7. แก้ไขสินค้าในตะกร้า");
            System.out.println("8. แสดงรายการสินค้าในตะกร้า");
            System.out.println("9. ยืนยันการสั่งซื้อในตะกร้า");
            System.out.println("10. ออกจากโปรแกรม");
            System.out.print("เลือกเมนู: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("ตัวเลือกไม่ถูกต้อง\nกรุณาลองใหม่ !!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1: displayInventoryProducts(); break;
                case 2: addInventoryProduct(); break;
                case 3: removeInventoryProduct(); break;
                case 4: editInventoryProduct(); break;
                case 5: addToCart(); break;
                case 6: removeFromCart(); break;
                case 7: editCartProduct(); break;
                case 8:
                    System.out.println("\n---------------\n");
                    displayCartProducts();
                    System.out.println();
                    System.out.println("ยอดรวมในตะกร้า: " + calculateCartTotal());
                    System.out.println("\n---------------\n");
                    break;
                case 9: confirmPurchase(); break;
                case 10:
                    System.out.println("ออกจากโปรแกรม...");
                    return;
                default:
                    System.out.println("ตัวเลือกไม่ถูกต้อง\nกรุณาลองใหม่ !!");
            }
        }
    }

    // แม็ค นายชาญกิจ สมผิว 672110227

    static String generateNewId() {
        lastUsedId++;
        return "" + lastUsedId;
    }

    // แม็ค นายชาญกิจ สมผิว 672110227

    static void addInventoryProduct() {

        Scanner scanner = new Scanner(System.in);

        try {
            if (inventorySize >= 100) {
                System.out.println("คลังสินค้าเต็ม!");
                return;
            }

            System.out.print("ชื่อสินค้า: ");
            String name = scanner.nextLine();

            for (int i = 0; i < inventorySize; i++) {
                if (inventoryNames[i].equals(name)) {
                    System.out.println("\n---------------\n");
                    System.out.println("พบชื่อสินค้าซ้ำ");
                    System.out.println("\n---------------\n");
                    return;
                }
            }

            System.out.print("ราคา: ");
            double price = scanner.nextDouble();
            if (price < 0) {
                System.out.println("\n---------------\n");
                System.out.println("ราคาต้องไม่ติดลบ!");
                System.out.println("\n---------------\n");
                scanner.nextLine();
                return;
            }

            System.out.print("จำนวนในคลัง: ");
            int quantity = scanner.nextInt();
            if (quantity < 0) {
                System.out.println("\n---------------\n");
                System.out.println("จำนวนต้องไม่ติดลบ!");
                System.out.println("\n---------------\n");
                scanner.nextLine();
                return;
            }
            scanner.nextLine();

            String newId = generateNewId();
            inventoryIds[inventorySize] = newId;
            inventoryNames[inventorySize] = name;
            inventoryPrices[inventorySize] = price;
            inventoryQuantities[inventorySize] = quantity;
            inventorySize++;

            System.out.println("\n---------------\n");
            System.out.println("เพิ่มสินค้าในคลังเรียบร้อย!");
            System.out.println("ID สินค้าคือ: " + newId);
            System.out.println("\n---------------\n");

        } catch (Exception e) {
            System.out.println("\n---------------\n");
            System.out.println("ข้อมูลสินค้าไม่ถูกต้อง\nกรุณาลองใหม่ !!");
            scanner.nextLine();
            System.out.println("\n---------------\n");
        }
    }

    // อั๋น นายอนุกูล สมโภชน์ 672110164

    static int findIndexInInventory(String nameOrId) {
        for (int i = 0; i < inventorySize; i++) {
            if (inventoryNames[i].equals(nameOrId) || inventoryIds[i].equals(nameOrId))
                return i;
        }
        return -1;
    }

    // อั๋น นายอนุกูล สมโภชน์ 672110164

    static int findIndexInCart(String name) {
        for (int i = 0; i < cartSize; i++) {
            if (cartNames[i].equals(name)) return i;
        }
        return -1;
    }

    // อั๋น นายอนุกูล สมโภชน์ 672110164

    static void displayInventoryProducts() {
        System.out.println("\n---------------\n");
        if (inventorySize == 0) {
            System.out.println("ไม่มีสินค้าในคลัง!");
        } else {
            for (int i = 0; i < inventorySize; i++) {
                System.out.println("ID: " + inventoryIds[i] +
                        ", ชื่อสินค้า: " + inventoryNames[i] +
                        ", ราคา: " + inventoryPrices[i] +
                        ", จำนวนในคลัง: " + inventoryQuantities[i]);
            }
        }
        System.out.println("\n---------------");
    }

    // เฟียต นายพัทธพล ดวงใจ 672110151

    static void removeInventoryProduct() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("ID หรือชื่อสินค้าที่ต้องการลบ: ");
        String nameOrId = scanner.nextLine();
        int index = findIndexInInventory(nameOrId);

        if (index != -1) {
            for (int i = index; i < inventorySize - 1; i++) {
                inventoryIds[i] = inventoryIds[i + 1];
                inventoryNames[i] = inventoryNames[i + 1];
                inventoryPrices[i] = inventoryPrices[i + 1];
                inventoryQuantities[i] = inventoryQuantities[i + 1];
            }
            inventorySize--;
            System.out.println("\n---------------\n");
            System.out.println("ลบสินค้าในคลังเรียบร้อย!");
            System.out.println("\n---------------\n");
        } else {
            System.out.println("\n---------------\n");
            System.out.println("ไม่พบสินค้านี้ในคลัง!");
            System.out.println("\n---------------\n");
        }
    }

    // เฟียต นายพัทธพล ดวงใจ 672110151

    static void editInventoryProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("ID หรือชื่อสินค้าที่ต้องการแก้ไข: ");
        String nameOrId = scanner.nextLine();
        int index = findIndexInInventory(nameOrId);

        try {
            if (index != -1) {
                System.out.print("ราคาใหม่: ");
                double newPrice = scanner.nextDouble();
                if (newPrice < 0) {
                    System.out.println("\n---------------\n");
                    System.out.println("ราคาต้องไม่ติดลบ!");
                    System.out.println("\n---------------\n");
                    scanner.nextLine();
                    return;
                }

                System.out.print("จำนวนใหม่ในคลัง: ");
                int newQuantity = scanner.nextInt();
                if (newQuantity < 0) {
                    System.out.println("\n---------------\n");
                    System.out.println("จำนวนต้องไม่ติดลบ!");
                    System.out.println("\n---------------\n");
                    scanner.nextLine();
                    return;
                }
                scanner.nextLine();

                inventoryPrices[index] = newPrice;
                inventoryQuantities[index] = newQuantity;
                System.out.println("\n---------------\n");
                System.out.println("แก้ไขข้อมูลสินค้าเรียบร้อย!");
                System.out.println("\n---------------\n");
            } else {
                System.out.println("\n---------------\n");
                System.out.println("ไม่พบสินค้านี้ในคลัง!");
                System.out.println("\n---------------\n");
            }
        } catch (Exception e) {
            System.out.println("\n---------------\n");
            System.out.println("ข้อมูลสินค้าไม่ถูกต้อง\nกรุณาลองใหม่ !!");
            scanner.nextLine();
            System.out.println("\n---------------\n");
        }
    }

    // เฟียต นายพัทธพล ดวงใจ 672110151

    static void addToCart() {

        Scanner scanner = new Scanner(System.in);
        if (cartSize >= 100) {
            System.out.println("ตะกร้าเต็ม!");
            return;
        }
        System.out.print("ID หรือชื่อสินค้าที่ต้องการเพิ่มในตะกร้า: ");
        String nameOrId = scanner.nextLine();
        int inventoryIndex = findIndexInInventory(nameOrId);

        try {
            if (inventoryIndex != -1 && inventoryQuantities[inventoryIndex] > 0) {
                System.out.print("จำนวนที่ต้องการเพิ่มในตะกร้า: ");
                int quantity = scanner.nextInt();
                if (quantity < 0) {
                    System.out.println("\n---------------\n");
                    System.out.println("จำนวนต้องไม่ติดลบ!");
                    System.out.println("\n---------------\n");
                    scanner.nextLine();
                    return;
                }
                scanner.nextLine();

                int cartIndex = findIndexInCart(inventoryNames[inventoryIndex]);
                if (cartIndex != -1) {
                    int totalInCart = cartQuantities[cartIndex] + quantity;
                    if (totalInCart <= inventoryQuantities[inventoryIndex]) {
                        cartQuantities[cartIndex] = totalInCart;
                    } else {
                        System.out.println("\n---------------\n");
                        System.out.println("จำนวนสินค้าในคลังไม่พอ!");
                        System.out.println("\n---------------\n");
                        return;
                    }
                } else {
                    if (quantity <= inventoryQuantities[inventoryIndex]) {
                        cartNames[cartSize] = inventoryNames[inventoryIndex];
                        cartPrices[cartSize] = inventoryPrices[inventoryIndex];
                        cartQuantities[cartSize] = quantity;
                        cartSize++;
                    } else {
                        System.out.println("\n---------------\n");
                        System.out.println("จำนวนสินค้าในคลังไม่พอ!");
                        System.out.println("\n---------------\n");
                        return;
                    }
                }

                System.out.println("\n---------------\n");
                System.out.println("เพิ่มสินค้าในตะกร้าเรียบร้อย!");
                System.out.println("\n---------------\n");
            } else {
                System.out.println("\n---------------\n");
                System.out.println("ไม่พบสินค้าหรือสินค้าหมดในคลัง!");
                System.out.println("\n---------------\n");
            }
        } catch (Exception e) {
            System.out.println("\n---------------\n");
            System.out.println("ข้อมูลสินค้าไม่ถูกต้อง\nกรุณาลองใหม่ !!");
            scanner.nextLine();
            System.out.println("\n---------------\n");
        }
    }

    // กิ่ง นางสาวภูริชญา หลำสวัสดิ์ 672110239

    static void displayCartProducts() {
        if (cartSize == 0) {
            System.out.println("ไม่มีสินค้าในตะกร้า!");
        } else {
            for (int i = 0; i < cartSize; i++) {
                String productId = inventoryIds[findIndexInInventory(cartNames[i])];
                System.out.println("ID: " + productId +
                                 ", ชื่อสินค้า: " + cartNames[i] +
                                 ", ราคา: " + cartPrices[i] +
                                 ", จำนวนในตะกร้า: " + cartQuantities[i]);
            }
        }
    }

    // กิ่ง นางสาวภูริชญา หลำสวัสดิ์ 672110239

    static void removeFromCart() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("ชื่อสินค้าที่ต้องการลบจากตะกร้า: ");
        String name = scanner.nextLine();
        int index = findIndexInCart(name);

        if (index != -1) {
            for (int i = index; i < cartSize - 1; i++) {
                cartNames[i] = cartNames[i + 1];
                cartPrices[i] = cartPrices[i + 1];
                cartQuantities[i] = cartQuantities[i + 1];
            }
            cartSize--;
            System.out.println("\n---------------\n");
            System.out.println("ลบสินค้าในตะกร้าเรียบร้อย!");
            System.out.println("\n---------------\n");
        } else {

            System.out.println("\n---------------\n");
            System.out.println("ไม่พบสินค้านี้ในตะกร้า!");
            System.out.println("\n---------------\n");
        }
    }

    // กล้า นายธนาโชติ คำชุม 672110226

    static void editCartProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("ชื่อสินค้าที่ต้องการแก้ไขในตะกร้า: ");
        String name = scanner.nextLine();
        int cartIndex = findIndexInCart(name);
        int inventoryIndex = findIndexInInventory(name);

        try {
            if (cartIndex != -1) {
                System.out.print("จำนวนใหม่ในตะกร้า: ");
                int newQuantity = scanner.nextInt();
                if (newQuantity < 0) {
                    System.out.println("\n---------------\n");
                    System.out.println("จำนวนต้องไม่ติดลบ!");
                    System.out.println("\n---------------\n");
                    scanner.nextLine();
                    return;
                }
                scanner.nextLine();

                if (newQuantity == 0) {

                    for (int i = cartIndex; i < cartSize - 1; i++) {
                        cartNames[i] = cartNames[i + 1];
                        cartPrices[i] = cartPrices[i + 1];
                        cartQuantities[i] = cartQuantities[i + 1];
                    }
                    cartSize--;
                    System.out.println("\n---------------\n");
                    System.out.println("ลบสินค้าออกจากตะกร้าเนื่องจากจำนวนเป็น 0");
                    System.out.println("\n---------------\n");
                } else if (inventoryIndex != -1 && newQuantity <= inventoryQuantities[inventoryIndex]) {
                    cartQuantities[cartIndex] = newQuantity;
                    System.out.println("\n---------------\n");
                    System.out.println("แก้ไขสินค้าในตะกร้าเรียบร้อย!");
                    System.out.println("\n---------------\n");
                } else {
                    System.out.println("\n---------------\n");
                    System.out.println("สินค้าไม่พอ! จำนวนในคลังมีเพียง " + inventoryQuantities[inventoryIndex] + " ชิ้น");
                    System.out.println("\n---------------\n");
                }
            } else {
                System.out.println("\n---------------\n");
                System.out.println("ไม่พบสินค้านี้ในตะกร้า!");
                System.out.println("\n---------------\n");
            }
        } catch (Exception e) {
            System.out.println("\n---------------\n");
            System.out.println("ข้อมูลสินค้าผิดพลาด\nกรุณาลองใหม่ !!");
            scanner.nextLine();
            System.out.println("\n---------------\n");
        }
    }

    // กล้า นายธนาโชติ คำชุม 672110226

    static double calculateCartTotal() {
        double total = 0;
        for (int i = 0; i < cartSize; i++) {
            total += cartPrices[i] * cartQuantities[i];
        }
        return total;
    }

    // กล้า นายธนาโชติ คำชุม 672110226

    static void confirmPurchase() {
        if (cartSize == 0) {
            System.out.println("\n---------------\n");
            System.out.println("ไม่มีสินค้าในตะกร้า!");
            System.out.println("\n---------------\n");
        } else {
            double total = calculateCartTotal();
            System.out.println("\n---------------\n");
            displayCartProducts();
            System.out.println();
            System.out.println("ยอดรวมทั้งหมด: " + total);


            for (int i = 0; i < cartSize; i++) {
                String productName = cartNames[i];
                int cartQuantity = cartQuantities[i];
                int inventoryIndex = findIndexInInventory(productName);

                if (inventoryIndex != -1) {
                    inventoryQuantities[inventoryIndex] -= cartQuantity;
                }
            }


            cartSize = 0;

            System.out.println("การสั่งซื้อเสร็จสมบูรณ์ ขอบคุณที่ใช้บริการ!");
            System.out.println("\n---------------\n");
        }
    }
}