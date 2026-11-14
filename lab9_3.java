package block3;

// Класс узла списка (Студент)
class ListNode {
    int id; // Для простоты сортируем по ID
    ListNode next;

    public ListNode(int id) {
        this.id = id;
        this.next = null;
    }
}

public class lab9_3 {

    // ГЛАВНЫЙ МЕТОД СОРТИРОВКИ СЛИЯНИЕМ ДЛЯ СПИСКА
    public static ListNode mergeSort(ListNode head) {
        // Базовый случай: если список пуст или состоит из 1 элемента
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Находим середину списка (метод "черепахи и зайца")
        ListNode middle = getMiddle(head);
        ListNode nextOfMiddle = middle.next;
        middle.next = null; // Разрываем список на две половины

        // 2. Рекурсивно сортируем обе половины
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(nextOfMiddle);

        // 3. Сливаем отсортированные половины
        return sortedMerge(left, right);
    }

    // Вспомогательный метод: поиск середины
    private static ListNode getMiddle(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Вспомогательный метод: слияние двух списков
    private static ListNode sortedMerge(ListNode a, ListNode b) {
        ListNode result = null;

        // Базовые случаи
        if (a == null) return b;
        if (b == null) return a;

        // Сравниваем элементы
        if (a.id <= b.id) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    // Метод для вывода списка на экран
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.id + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Создаем первый список (неотсортированный)
        ListNode list1 = new ListNode(15);
        list1.next = new ListNode(10);
        list1.next.next = new ListNode(5);

        // Создаем второй список (неотсортированный)
        ListNode list2 = new ListNode(20);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(2);

        System.out.println("Список 1:");
        printList(list1);
        System.out.println("Список 2:");
        printList(list2);

        // 1. Сначала отсортируем каждый список отдельно (если они не отсортированы)
        list1 = mergeSort(list1);
        list2 = mergeSort(list2);

        // 2. Теперь объединяем два отсортированных списка в один
        // Для этого используем тот же метод sortedMerge
        ListNode mergedHead = sortedMerge(list1, list2);

        System.out.println("\nОбъединенный отсортированный список:");
        printList(mergedHead);
    }
}
