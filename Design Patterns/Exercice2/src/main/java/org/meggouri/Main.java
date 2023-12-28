package org.meggouri;

import org.meggouri.compresers.*;
import org.meggouri.filters.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ImageProcessingFramework framework = new ImageProcessingFramework();

        System.out.print("Entrez le nom de la classe d'implémentation pour l'opération de filtrage : ");
        String filterClassName = scanner.next();

        System.out.print("Entrez le nom de la classe d'implémentation pour l'opération de compression : ");
        String compressorClassName = scanner.next();

        try {
            System.out.println(filterClassName);
            System.out.println(compressorClassName);
            Class<?> filterClass = Class.forName("org.meggouri.filters."+filterClassName);
            ImageFilter filter = (ImageFilter) filterClass.getDeclaredConstructor().newInstance();
            framework.setFilter(filter);

            Class<?> compressorClass = Class.forName("org.meggouri.compresers."+compressorClassName);
            ImageCompressor compressor = (ImageCompressor) compressorClass.getDeclaredConstructor().newInstance();
            framework.setCompressor(compressor);

            int[] imageData = {0, 1, 0, 1, 2, 0, 1, 5, 1, 0, 5, 8, 9, 5, 4, 8, 5, 4, 0, 1};

            int[] filteredData = framework.applyFilter(imageData);

            int[] compressedData = framework.compressImage(filteredData);

            System.out.println("Résultat du filtrage : " + arrayToString(filteredData));
            System.out.println("Résultat de la compression : " + arrayToString(compressedData));

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}