import java.util.*;

class PokerGame {
    public static void main(String[] args) {

        String[] rank = {
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        String[] suits = {
                "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C",
                "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D",
                "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H",
                "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"};

        // Преобразуем массивы в списки для использования Collections.shuffle()
        List<String> ranksList = new ArrayList<>(List.of(rank));
        List<String> suitsList = new ArrayList<>(List.of(suits));

        // Перемешиваем списки
        Collections.shuffle(ranksList);
        Collections.shuffle(suitsList);

        // Объединение списков обратно в массив
        String[] combinedArray = new String[rank.length + suits.length];
        for (int i = 0; i < rank.length; i++) {
            combinedArray[i * 2] = ranksList.get(i);
            combinedArray[(i * 2) + 1] = suitsList.get(i);
        }

        // Карта для сопоставления буквенных значений
        Map<String, Integer> letterToNumber = new HashMap<>();
        letterToNumber.put("J", 11);
        letterToNumber.put("Q", 12);
        letterToNumber.put("K", 13);
        letterToNumber.put("A", 14);

        // Преобразование строк в числа
        int[] convertedRanks = new int[ranksList.size()];
        for (int i = 0; i < ranksList.size(); i++) {
            String rankValue = ranksList.get(i); // Используйте ranksList вместо rank
            if (letterToNumber.containsKey(rankValue)) {
                convertedRanks[i] = letterToNumber.get(rankValue);
            } else {
                convertedRanks[i] = Integer.parseInt(rankValue);
            }
        }

        //Сортировка массива от 0 значения до 7 по возрастающей
        Arrays.sort(convertedRanks, 0, 7);

        // Выводим объединенный массив
            Scanner sc = new Scanner(System.in);
            System.out.println("Вы готовы начать игру? ");
            String scan1 = sc.nextLine();

            System.out.println("Ваша рука:");
             for (int k = 0; k < 4; k++) {
                System.out.print(combinedArray[k] + "");
            }
            System.out.println();

            System.out.println("Стол:");
            for (int k = 4; k < 10; k++) {
                System.out.print(combinedArray[k] + "");
            }
            System.out.println();

            System.out.println("Выложить карту (Тёрн)?");
            String scan2 = sc.nextLine();

            System.out.println("Ваша рука:");
            for (int k = 0; k < 4; k++) {
                System.out.print(combinedArray[k] + "");
            }

            System.out.println();

            System.out.println("Стол:");
            for (int k = 4; k < 12; k++) {
                System.out.print(combinedArray[k] + "");
            }
            System.out.println();

            System.out.println("Выложить карту (Ривер)?");
            String scan3 = sc.nextLine();

            System.out.println("Ваша рука:");
            for (int k = 0; k < 4; k++) {
                System.out.print(combinedArray[k] + "");
            }
            System.out.println();

            System.out.println("Стол:");
            for (int k = 4; k < 14; k++) {
                System.out.print(combinedArray[k] + "");
            }
            System.out.println();

            System.out.println("Определить победителя?");
            String scan4 = sc.nextLine();

            //Поиск комбинаций для первого игрока.
        // Считаем количество совпадений для первого элемента
        int countFirstMatches = 0;
        String firstElement = combinedArray[0];
        for (int i = 1; i < Math.min(13, combinedArray.length); i++) {
            if (firstElement.equals(combinedArray[i])) {
                countFirstMatches++;
            }
        }
        // Считаем количество совпадений для второго элемента
        int countSecondMatches = 0;
        String secondElement = combinedArray[1];
        for (int i = 2; i < Math.min(13, combinedArray.length); i++) {
            if (secondElement.equals(combinedArray[i])) {
                countSecondMatches++;
            }
        }

        // Считаем количество совпадений для третьего элемента
        int countTreeMatches = 0;
        String treeElement = combinedArray[2];
        for (int i = 3; i < Math.min(13, combinedArray.length); i++) {
            if (treeElement.equals(combinedArray[i])) {
                countTreeMatches++;

            }
        }
        // Считаем количество совпадений для четвёртого элемента
        int countForMatches = 0;
        String forElement = combinedArray[3];
        for (int i = 4; i < Math.min(13, combinedArray.length); i++) {
            if (forElement.equals(combinedArray[i])) {
                countForMatches++;
            }
        }

        // Проверяем условие "Стрит" для первой карты руки
        boolean isStraight = true;
        for (int i = 1; i < 5; i++) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight = false;
                break;
            }
        }

        // Проверяем условие "Стрит" для второй карты руки
        boolean isStraight2 = true;
        for (int i = 2; i < 6; i++) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight2 = false;
                break;
            }
        }

        // Проверяем Флешь Рояль для первой карты руки
        boolean isFirstCardTen = convertedRanks[0] == 10;
        boolean isStraight3 = true;
        for (int i = 1; i < 5; i++) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight3 = false;
                break;
            }
        }

        // Проверяем Флешь Рояль для первой карты руки
        boolean isFirstCardTen4 = convertedRanks[0] == 10;
        boolean isStraight4 = true;
        for (int i = 1; i < 5; i++) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight4 = false;
                break;
            }
        }

        // Находим комбинации для второго игрока
        System.out.println("Рука противника");
        for (int k = 14; k < 18; k++) {
            System.out.print(combinedArray[k] + "");
        }
        System.out.println(); // Переход на новую строку

        //Поиск комбинаций для второго игрока.
        // Считаем количество совпадений для первого элемента у противника
        int countFirstMatches2 = 0;
        String firstElement2 = combinedArray[14];

        for (int i = 4; i < Math.min(13, combinedArray.length); i++) {
            if (firstElement2.equals(combinedArray[i])) {
                countFirstMatches2++;
            }

        }

        // Считаем количество совпадений для второго элемента у противника
        int countSecondMatches2 = 0;
        String secondElement2 = combinedArray[15];

        for (int i = 4; i < Math.min(13, combinedArray.length); i++) {
            if (secondElement2.equals(combinedArray[i])) {
                countSecondMatches2++;
            }
        }

        // Считаем количество совпадений для третьего элемента у противника
        int countTreeMatches2 = 0;
        String treeElement2 = combinedArray[16];

        for (int i = 4; i < Math.min(13, combinedArray.length); i++) {
            if (treeElement2.equals(combinedArray[i])) {
                countTreeMatches2++;
            }
        }

        // Считаем количество совпадений для четвёртого элемента у противника
        int countForMatches2 = 0;
        String forElement2 = combinedArray[17];
        for (int i = 4; i < Math.min(13, combinedArray.length); i++) {
            if (forElement2.equals(combinedArray[i])) {
                countForMatches2++;
            }
        }
        // Проверяем условие "Стрит" для первой карты руки
        boolean isStraight5 = true;
        for (int i = 9; i > 4; i--) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight5 = false;
                break;
            }
        }
        // Проверяем условие "Стрит" для второй карты руки
        boolean isStraight6 = true;
        for (int i = 8; i >3; i--) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight6 = false;
                break;
            }
        }

        // Проверяем Флешь Рояль для первой карты руки
        boolean isFirstCardTen7 = convertedRanks[0] == 10;
        boolean isStraight7 = true;
        for (int i = 9; i > 4; i--) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight7 = false;
                break;
            }
        }

        // Проверяем Флешь Рояль для первой карты руки
        boolean isFirstCardTen8 = convertedRanks[10] == 10;
        boolean isStraight8 = true;
        for (int i = 8; i > 3; i--) {  // Проверяем только первые 5 карт
            if (convertedRanks[i] != convertedRanks[i - 1] + 1) {
                isStraight8 = false;
                break;
            }
        }

        // Весовые коэффициенты для разных комбинаций
        Map<String, Integer> weights = new HashMap<>();
        weights.put("Флеш рояль", 9);
        weights.put("Стрит-флешь", 8);
        weights.put("Каре", 7);
        weights.put("Фул-хаус", 6);
        weights.put("Флеш", 5);
        weights.put("Стрит", 4);
        weights.put("Тройка", 3);
        weights.put("Две пары", 2);
        weights.put("Пара", 1);
        weights.put("Рука", 0);


        // Определение комбинаций для первого игрока
        String combo1 = "";
        if ((isStraight3 || isStraight4) && (countSecondMatches == 4 || countForMatches == 4)) {
            combo1 = "Флеш рояль";
        } else if ((isStraight || isStraight2) && (countSecondMatches == 4 || countForMatches == 4)) {
            combo1 = "Стрит-флешь";
        } else if (countFirstMatches == 3 && countTreeMatches == 3) {
            combo1 = "Каре";
        } else if ((countFirstMatches == 1 && countTreeMatches == 2) || (countFirstMatches == 2 && countTreeMatches == 1)) {
            combo1 = "Фул-хаус";
        } else if (countSecondMatches == 4 || countForMatches == 4) {
            combo1 = "Флеш";
        } else if (isStraight || isStraight2) {
            combo1 = "Стрит";
        } else if (countFirstMatches == 2 || countTreeMatches == 2) {
            combo1 = "Тройка";
        } else if (countFirstMatches == 1 && countTreeMatches == 1) {
            combo1 = "Две пары";
        } else if (countFirstMatches == 1 || countTreeMatches == 1) {
            combo1 = "Пара";
        } else if ((countFirstMatches2 == 0)  ||  (countTreeMatches2 == 0)) {
            combo1 = "Рука";
        }

        System.out.println("Комбинация: " + combo1);

        // Определение комбинаций для второго игрока
        String combo2 = "";
        if ((isFirstCardTen7  || isFirstCardTen8) &&
                (countSecondMatches == 4 || countForMatches == 4)) {
            combo2 = "Флеш рояль";
        } else if (((isStraight5 || isStraight6) &&
                (countSecondMatches2 == 4 || countForMatches2 == 4))) {
            combo2 = "Стрит-флешь";
        } else if (countFirstMatches2 == 3 && countTreeMatches2 == 3) {
            combo2 = "Каре" ;
        } else if ((countFirstMatches2 == 1 && countTreeMatches2 == 2) ||
                (countFirstMatches2 == 2 && countTreeMatches2 == 1)) {
            combo2 = "Фул-хаус" ;
        } else if (countSecondMatches2== 4 || countForMatches2 == 4)  {
            combo2 = "Флеш";
        } else if (isStraight5 || isStraight6 ) {
            combo2 = "Стрит";
        } else if (countFirstMatches2 == 2 || countTreeMatches2 == 2) {
            combo2 = "Тройка";
        } else if (countFirstMatches2 == 1 && countTreeMatches2 == 1) {
            combo2 = "Две пары" ;
        } else if ((countFirstMatches2 == 1 || countTreeMatches2 == 1)){
            combo2 = "Пара";
        } else if  ((countFirstMatches2 == 0)  ||  (countTreeMatches2 == 0)) {
            combo2 = "Рука";
        }

        System.out.println("Комбинация: " + combo2);

        // Сравниваем комбинации
        int weight1 = weights.get(combo1);
        int weight2 = weights.get(combo2);

        if (weight1 > weight2) {
            System.out.println("Первый игрок победил с комбинацией " + combo1);
        } else if (weight2 > weight1) {
            System.out.println("Второй игрок победил с комбинацией " + combo2);
        } else {
            System.out.println("Ничья! Оба игрока имеют одинаковую комбинацию " + combo1 );
        }
    }
}

