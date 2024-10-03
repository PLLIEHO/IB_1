import java.io.File;
import java.util.Scanner;
//D:\IB_lab1_Kardano\kardano_test.txt
//D:\IB_lab1_Kardano\kardano_dec.txt
//D:\IB_lab1_Kardano\kardano_dec_322.txt

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите полный путь до файла: ");
        String fileName = sc.nextLine();

        System.out.println("Введите ключ (можно оставить пустым, будет использован вшитый ключ): ");
        String inkey = sc.nextLine();

        System.out.println("Введите 1, если хотите зашифровать данные. Введите 2, если хотите расшифровать данные");
        String encDecLine = sc.nextLine();

        sc.close();

        String input = "";
        int key = 44648;
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNext()){
                input += fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (Exception e){
            System.out.println("Возникла ошибка при чтении файла. Убедитесь, что вы указали верный путь!");
            return;
        }

        if(inkey == null || inkey.isEmpty()){
            key = 44648;
        }
        else {
            try{
                key = Integer.parseInt(inkey);
                if(key == 0 || key > 65534) throw new Exception();
            } catch (Exception e){
                System.out.println("Ошибка при чтении ключа. Убедитесь, что вы ввели десятичное число!");
                return;
            }
        }
        //String input = "Разве так плохо знать основы криптографии?";
        boolean encryptFlag = true;
        try{
            int temp = Integer.parseInt(encDecLine);
            if(temp == 1) encryptFlag = true;
            else if(temp == 2) encryptFlag = false;
            else throw new Exception();
        } catch (Exception e){
            System.out.println("Значение флага шифровки/расшифровки нераспознано. Повторите ввод, убедитесь, что ввели значение 1 или 2");
            return;
        }

        EncryptDecrypter encDec = new EncryptDecrypter(key, input);

        String res;
        if(encryptFlag){
            res = encDec.encrypt();
        }
        else {
            res = encDec.decrypt();
        }

        System.out.println("Данные, прочитанные из файла: ");
        if(!encryptFlag) {
            char[] charBuff1 = input.toCharArray();

            int counter = 0;
            for (char c : charBuff1) {
                counter++;
                System.out.print(c);
                if (counter == 4) {
                    System.out.print('\n');
                    counter = 0;
                }
            }
        }
        else {
            System.out.println(input);
        }

        System.out.print('\n');

        System.out.println("Ключ в двоичной форме: ");
        char[] charBuff2 = encDec.decimalToBinary().toCharArray();
        int counter = 0;
        for(char c : charBuff2){
            counter++;
            System.out.print(c);
            if(counter == 4){
                System.out.print('\n');
                counter = 0;
            }
        }

        System.out.print('\n');

        if(!encryptFlag) System.out.println("Полученная шифрограмма: ");
        else System.out.println("Полученная расшифровка: ");
        char[] charBuff3 = res.toCharArray();
        counter = 0;
        for(char c : charBuff3){
            counter++;
            System.out.print(c);
            if(counter == 4){
                System.out.print('\n');
                counter = 0;
            }
        }

        System.out.print('\n');

        if(!encryptFlag) System.out.println("Полученная шифрограмма без разбивки на строки: ");
        else System.out.println("Полученная расшифровка без разбивки на строки: ");
        System.out.println(res);
        //System.out.println(res);
    }
}