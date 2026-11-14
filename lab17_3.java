package block3;

import java.util.Scanner;

// 1. Класс узла кольцевого списка
class CircularNode {
    String name;
    int age;
    CircularNode next;

    public CircularNode() {
        this.next = null;
    }

    // a. Считывание данных с консоли
    public void readFromConsole(Scanner sc) {
        System.out.print("Введите имя студента: ");
        this.name = sc.nextLine();
        System.out.print("Введите возраст: ");
        this.age = sc.nextInt();
        sc.nextLine();
    }

    // b. Вывод данных на экран
    public void printToScreen() {
        System.out.println("Студент: " + name + "; Возраст: " + age);
    }
}

// 2. Контейнерный класс (Кольцевой список)
class CircularLinkedList {
    private CircularNode head;
    private CircularNode tail;

    // a. Создание пустого списка
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // b. Добавление элемента в конец
    public void add(CircularNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
        System.out.println("Студент добавлен в картотеку.");
    }

    // c. Удаление элемента по имени
    public void remove(String targetName) {
        if (isEmpty()) {
            System.out.println("Список пуст, удалять нечего.");
            return;
        }

        CircularNode current = head;
        CircularNode prev = tail;

        do {
            if (current.name.equalsIgnoreCase(targetName)) {
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!current.name.equalsIgnoreCase(targetName)) {
            System.out.println("Студент с таким именем не найден.");
            return;
        }
        // 1. В списке остался один элемент
        if (head == tail) {
            head = null;
            tail = null;
        }
        // 2. Удаляем первый элемент (голову)
        else if (current == head) {
            head = head.next;
            tail.next = head;
        }
        // 3. Удаляем последний элемент (хвост)
        else if (current == tail) {
            tail = prev;
            tail.next = head;
        }
        // 4. Удаляем из середины
        else {
            prev.next = current.next;
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
        CircularNode current = head;

        do {
            current.printToScreen();
            current = current.next;
        } while (current != head);
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
public class lab17_3 {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
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
                    CircularNode newNode = new CircularNode();
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
