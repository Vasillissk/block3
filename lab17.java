package block3;

import java.util.Scanner;

// 1. Класс, описывающий узел списка (элемент картотеки)
class StudentNode {
    String name;
    int age;
    StudentNode next;
    public StudentNode() {
        this.next = null;
    }

    // a. Функция считывания атрибутов с консоли
    public void readFromConsole(Scanner sc) {
        System.out.print("Введите имя студента: ");
        this.name = sc.nextLine();
        System.out.print("Введите возраст: ");
        this.age = sc.nextInt();
        sc.nextLine(); // Очистка
    }

    // b. Функция вывода атрибутов на экран
    public void printToScreen() {
        System.out.println("Студент: " + name + " | Возраст: " + age);
    }
}

// 2. Контейнерный класс (Список)
class StudentList {
    private StudentNode head; // Голова списка (указатель на первый элемент)

    // a. Создание пустого списка
    public StudentList() {
        this.head = null;
    }

    // b. Добавление элемента (узла) в конец списка
    public void add(StudentNode newNode) {
        if (head == null) {
            head = newNode;
        }
        else {
            StudentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Студент добавлен в картотеку.");
    }

    // c. Удаление элемента по имени
    public void remove(String targetName) {
        if (isEmpty()) {
            System.out.println("Список пуст, удалять нечего.");
            return;
        }

        // Если удаляемый элемент - это голова
        if (head.name.equalsIgnoreCase(targetName)) {
            head = head.next; // Головой становится следующий элемент
            System.out.println("Студент " + targetName + " удален.");
            return;
        }

        // Поиск элемента в середине или конце списка
        StudentNode current = head;
        while (current.next != null && !current.next.name.equalsIgnoreCase(targetName)) {
            current = current.next;
        }

        // Если нашли (current.next не равен null)
        if (current.next != null) {
            current.next = current.next.next; // "Выкидываем" узел из цепочки
            System.out.println("Студент " + targetName + " удален.");
        } else {
            System.out.println("Студент с таким именем не найден.");
        }
    }

    // d. Функция вывода всего списка на экран
    public void printList() {
        if (isEmpty()) {
            System.out.println("Картотека пуста.");
            return;
        }
        System.out.println("Текущая картотека");
        StudentNode current = head;
        while (current != null) {
            current.printToScreen();
            current = current.next;
        }
    }

    // e. Функция очистки списка
    public void clear() {
        head = null;
        System.out.println("Картотека полностью очищена.");
    }

    // f. Функция проверки списка на пустоту
    public boolean isEmpty() {
        return head == null;
    }
}

// 3. Класс Тестер
public class lab17 {
    public static void main(String[] args) {
        StudentList cardIndex = new StudentList();
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
            }
            else {
                System.out.println("Ошибка! Введите число.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    StudentNode newNode = new StudentNode();
                    newNode.readFromConsole(scanner);
                    cardIndex.add(newNode);
                    break;
                case 2:
                    System.out.print("Введите имя студента для удаления: ");
                    String nameToRemove = scanner.nextLine();
                    cardIndex.remove(nameToRemove);
                    break;
                case 3:
                    cardIndex.printList();
                    break;
                case 4:
                    cardIndex.clear();
                    break;
                case 5:
                    if (cardIndex.isEmpty()) {
                        System.out.println("Да, картотека пуста.");
                    } else {
                        System.out.println("Нет, в картотеке есть студенты.");
                    }
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
        scanner.close();
    }
}
