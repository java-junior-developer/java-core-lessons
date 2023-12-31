package lesson02;

// ����� � ������������� public ����� ���� ������ ���� � �����
// ��� ������ ����� ������ ��������������� ����� �����
public class DataTypes {

    // ����������� Ctrl + /
    /* Ctrl + Shift + /
     �������������
     �����������
    */

    // ����� ����� � ����������
    // ��� ����� ����� ���������� �� ����� �����������
    public static void main(String[] args) {
        // � ����� ������ ���������� �������� ����� � �������
        System.out.println("���������� �����");

        // ���������� ����������:
        // 1. �������� �� ��� ������
        // 2. ��� ����������

        int yearOfBirth; // ��������� ���������� yearOfBirth ���� int
        int length, height; // ��������� ��� ���������� (length � height) ���� int

        // ������������� ���������� (�������� ���������� ������������� ������ ���)
        // ��� ��������� ��������� � ���������� ��� ������ �� �����������
        yearOfBirth = 1990;
        length = 20;
        height = 12;

        // ��������� �������� ����������
        yearOfBirth = 2000;

        // ������������� ���������� � ������ ����������
        int numberOfPears = 10; // �������� ���������� ���� int, ���������������� �� ��������� 10
        int numberOfApples = 30, numberOfBananas = 40; // �������� ��� ���������� ���� int,
        // ������ ��������� �������� 30, ������ 40

        // ���������� � ���������� numberOfBananas, ��������� ����� ��������
        numberOfBananas = 100;

        // ��������� �������� ���������� ��� ������ � �������
        System.out.println(numberOfApples);

        System.out.println("numberOfBananas " + numberOfBananas);

        // ���� ������:
        // 1. ����������� ���� ������
        // 2. ��������� ���� ������

        // ������������� ����
        byte readByte = 56; // �������� ���������� ���� byte, ���������������� �� ��������� 56
        short userAge = 35; // �������� ���������� ���� short, ���������������� �� ��������� 35
        long temp = 12l, planetAge = 400_000_000_000L; // �������� ���������� ���� long, ���������������� �� ���������� 12 � 400
        // l ��� L ���� ��������� �� ��� long.
        // ���� �������� ������� ������� �� ���������� ��� int �������� � L �����������, ��� �� ��������������

        System.out.println(planetAge);

        // ����� � ��������� ������
        // (���� � ��������� ����� ������� �������� ���������� ����� � ��������� ������,
        // ����� ������������ ��������������� ������ ������ float � double)
        float outsideTemp = -12.5f, catAge = 4.5F; // �������� ���������� ���� float, ���������������� �� ���������� 12.5 � 4.5
        // f ��� F ���� ��������� �� float. f ��� F ����� ��������� ��� ����������� �� ��������

        double weight = 200.5; // �������� ���������� ���� double, ���������������� �� ��������� 200.5

        System.out.println(outsideTemp);

        // ������ ������������� ������������ ��� �������� ������
        long population = 8_147_701_961L;
        double maxTemperature = 45_350.231;

        System.out.println(population);
        System.out.println(maxTemperature);

        // ���������� ���
        boolean isActive = true; // �������� ���������� ���� boolean �� ��������� true, �� TRUE ��� True
        boolean isConnected = false; // �������� ���������� ���� boolean �� ��������� true, �� FALSE ��� False

        System.out.println(isActive);

        // ���������� ��� (������ �������)
        char aLetter = 'a';
        System.out.println(aLetter);

        // ���������� ����� - ����������, ����� ���������� ������ ���� ������������� �������� ������� ����
        // ��������, ���� ���� ����������

        // �������������� (�����������) ���������� ����� ��������, ���� ������� ��� ������
        int numberOfStones01 = 12_000;
        long numberOfStones02 = numberOfStones01; // ������� ��� long ������ ���� int
        // �������� ���������� ������������� ���������� � ���� long,
        // ����� �������� long ���� ������������� ���������� numberOfStones02

        System.out.println(numberOfStones01);
        System.out.println(numberOfStones02);

        // ����� (��������) ���������� ����� ������������, ���� ������� ��� ������
        int distance = 1000;
        byte smallDistance = (byte) distance; // (byte) - ����� ���������� � ���� byte
        // 1000 ������� �� ���������� �������� byte, ������� �� ���������� � ������� ������.

        System.out.println(smallDistance);


        // ���������� ���������� ����� var �������� ������� � java 10
        // ����������, ���������� ����� var ������ ���� ������������������� � ������ ����������
        // ����� var ����� ��������� ������ ��������� ���������� �������

        var version = 10; // // ������������� ������� ������������ ��� int �� ���������
        var bigNumber = 10L; // long ��-�� L �������
        var connected = true; // boolean, �.�. true/false ����� ������ ������ ����� ��� boolean
        var balance = 4000.8; // ����� � ��������� ������ ������������ ��� double �� ���������
        var numberOfHours = 50.5F; // float ��-�� F �������
        var price = (short) 290; // short ��-�� ����������

    }
}
