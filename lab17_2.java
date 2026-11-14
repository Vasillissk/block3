package block3;

import java.util.Scanner;

// 1. Класс, описывающий узел двусвязного списка
class DoublyNode {
    String name;
    int age;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode() {
        this.next = null;
        this.prev = null;
    }

    // a. Считывание атрибутов с консоли
    public void readFromConsole(Scanner sc) {
        System.out.print("Введите имя студента: ");
        this.name = sc.nextLine();
        System.out.print("Введите возраст: ");
        this.age = sc.nextInt();
        sc.nextLine();
    }

    // b. Вывод атрибутов на экран
    public void printToScreen() {
        System.out.println("Студент: " + name + "; Возраст: " + age);
    }
}

// 2. Контейнерный класс (Двусвязный список)
class DoublyLinkedList {
    private DoublyNode head;
    private DoublyNode tail;

    // a. Создание пустого списка
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // b. Добавление элемента в конец
    public void add(DoublyNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Студент добавлен в картотеку");
    }

    // c. Удаление элемента по имени
    public void remove(String targetName) {
        if (isEmpty()) {
            System.out.println("Список пуст, удалять нечего");
            return;
        }

        DoublyNode current = head;

        // Ищем нужный узел
        while (current != null && !current.name.equalsIgnoreCase(targetName)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Студент с таким именем не найден");
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
        }
        else if (current == head) {
            head = head.next;
            head.prev = null;
        }
        else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        System.out.println("Студент " + targetName + " удален.");
    }

    // d. Вывод всего списка
    public void printList() {
        if (isEmpty()) {
            System.out.println("Картотека пуста.");
            return;
        }
        System.out.println("Текущая картотека");
        DoublyNode current = head;
        while (current != null) {
            current.printToScreen();
            current = current.next;
        }
    }

    // e. Очистка списка
    public void clear() {
        head = null;
        tail = null;
        System.out.println("Картотека полностью очищена.");
    }

    // f. Проверка на пустоту
    public boolean isEmpty() {
        return head == null;
    }
}

// 3. Тестер
public class lab17_2 {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("МЕНЮ");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента по имени");
            System.out.println("3. Показать всех студентов");
            System.out.println("4. Очистить картотеку");
            System.out.println("5. Проверить, пуста ли картотека");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ошибка! Введите число.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    DoublyNode newNode = new DoublyNode();
                    newNode.readFromConsole(scanner);
                    list.add(newNode);
                    break;
                case 2:
                    System.out.print("Введите имя для удаления: ");
                    list.remove(scanner.nextLine());
                    break;
                case 3:
                    list.printList();
                    break;
                case 4:
                    list.clear();
                    break;
                case 5:
                    if (list.isEmpty()) {
                        System.out.println("Да, картотека пуста.");
                    } else {
                        System.out.println("Нет, в картотеке есть студенты.");
                    }
                    break;
                case 0:
                    System.out.println("Выход...");
                    break;
            }
        }
        scanner.close();
    }
}
