public class Main1 {

    public static void main(String[] args) {
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = processArray(correctArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        generateArrayIndexException();
    }
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4х4");
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + "имеет неверную длину");
            }
            for (int j = 0; j < array[i].length; j++){
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        return sum;
    }
    public static void generateArrayIndexException() {
        try {
            int[] smallArray = {1, 2, 3};
            int error = smallArray[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали системное исключение: " + e);
        }
    }
}
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {super(message);}
}
class MyArrayDataException extends Exception{
    public MyArrayDataException(int row, int col) {
        super(String.format("Ошибка в ячейке [%d][%d] :данные не являются числом", row, col));
    }
}
