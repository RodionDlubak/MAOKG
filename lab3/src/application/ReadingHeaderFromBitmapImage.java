package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadingHeaderFromBitmapImage {
    public PrintingImage pr;

    public HeaderBitmapImage Reading(BufferedInputStream reader) throws IOException
// �����, ���� ������ ���� ����� �������� �� ����� �� �����������
// �� ����������� �ᒺ�� ���� HeaderBitmapImage, � ����������� ��� ����������
    {
        HeaderBitmapImage hbi = new HeaderBitmapImage();
        int line;
        int i = 0;
        short type = 0;
        long size = 0;
        short res1 = 0;
        short res2 = 0;
        long offset = 0;
        long header = 0;
        long width = 0;
        long height = 0;
        short numbPanel = 0;
        short bitCount = 0;
        long compr = 0;
        long sCompIm = 0;
        long hRes = 0;
        long vRes = 0;
        long numbUCol = 0;
        long numbICol = 0;
        long half = 0;
        long temp = 0;
        while ((line = reader.read()) != -1) { // ���� �� ����� �����
            i++; // �������� �������� ������� ���� ��������� ����������
            if (i == 1) // ������� ���������
            {
                temp = reader.read();
                type += (temp * 0x100) + line;
                i++;
            }
            if (i == 2) // ���� ������� ������� ������� ����� = 2, �� ������� ����� �����
            {
                size = readLong(reader); // � ����� size �������� ��������� ������ ������ readLong
                i = i + 4; // ������ 4 �� ������� �������� ���� � �����, ��� �� ����� ���� size 4 �����
            }
            if (i == 6) //������� ����������� ���� �1
            {
                res1 = readShort(reader); // � ����� res1 �������� ��������� ������ ������ readShort
                i = i + 2; // ������ 2 �� ������� �������� ���� � �����, ��� �� ����� ���� res1 2 �����
            }
            if (i == 8) //������� ����������� ���� �2
            {
                res2 = readShort(reader);
                i = i + 2;
            }
            if (i == 10) //������� �������
            {
                offset = readLong(reader);
                i = i + 4;
            }
            if (i == 14) //������� ����� ���������
            {
                header = readLong(reader);
                i = i + 4;
            }
// ������� � 18� �� 22� ������� ������ � ������� ����������
            if (i == 18) {
                width = readLong(reader);
                i = i + 4;
                height = readLong(reader);
                i = i + 4;
                half = width;
                if ((half % 2) != 0) // �������� �� ������ ���������� ������ 2 � ���� �, �� �������� �� �������� �� 1
                    half++; // ��� ��������� �������� �������� �� ������ ����������
                half /= 2;
                if ((half % 4) != 0) // ���� �� ������� �� 4
                    half = (half / 4) * 4 + 4; // ���������� �������� �������� ������ ����������, ��� ���� ���� ������ 4
            }
            if (i == 26) //������� ������� ������
            {
                numbPanel = readShort(reader);
                i = i + 2;
            }
            if (i == 28) //������� ������� ��
            {
                bitCount = readShort(reader);
                i = i + 2;
            }
            if (i == 30) //������� ��� ����������
            {
                compr = readLong(reader);
                i = i + 4;
            }
            if (i == 34) //������� ����� ����������� ����������
            {
                sCompIm = readLong(reader);
                i = i + 4;
            }
            if (i == 38) // ������������� �������� ��������
            {
                hRes = readLong(reader);
                i = i + 4;
            }
            if (i == 42) // ����������� �������� ��������
            {
                vRes = readLong(reader);
                i = i + 4;
            }
            if (i == 46) // ������� ������� ������
            {
                numbUCol = readLong(reader);
                i = i + 4;
            }
            if (i == 50) // ������� �������� �������
            {
                numbICol = readLong(reader);
                i = i + 4;
            }
// �������� �� ������ �������� � ��'���
            hbi.setValues(type, size, res1, res2, offset, header, width,
                    height, numbPanel, bitCount, compr, sCompIm, hRes,
                    vRes, numbUCol, numbICol, half);
// �������� ���� � ������, �� ����������� �����
            if (i == offset) {
                reader.mark(1);
                break;
            }
        }
// ��������� �� �� ���� ����� ����� ���������� �����
        reader.reset();
// �������� � ������� ���� ������� ����������, � ��� ����������� ������ �����
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("./source/pixels.txt"));
        while ((line = reader.read()) != -1) {
            writer.write(line);
        }
        writer.close();
        this.pr = new PrintingImage(hbi);
        return hbi;
    }

    private short readShort(BufferedInputStream reader) throws IOException {
        short valueToReturn = 0;
        for (long j = 0x1; j <= 0x1000; j *= 0x100) // ���� �� 1 �� 8 � ������ j*4 - �������� ������� �������� ����� = 2
        {
            valueToReturn += (reader.read() * j); // ������ �� ��������� ����� �������� ������ ������� ���������� � 10-�� ������ ��������
        }
        return valueToReturn;
    }

    private long readLong(BufferedInputStream reader) throws IOException {
        long valueToReturn = 0;
        for (long j = 0x1; j <= 0x1000000; j *= 0x100) // ���� �� 1 �� 64 � ������ j*4 - �������� ������� �������� ����� = 4
        {
            valueToReturn += (reader.read() * j); // ������ �� ��������� ����� �������� ������ ������� ���������� � 10-�� ������ ��������
        }
        return valueToReturn;
    }
}