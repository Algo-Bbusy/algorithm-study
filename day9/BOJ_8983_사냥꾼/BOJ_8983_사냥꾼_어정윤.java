import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_8983_사냥꾼_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] launchers = new int[m];
        for (int i = 0; i < m; i++) {
            launchers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        List<int[]> animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            animals.add(new int[]{Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())});
        }
        animals.sort(Comparator.comparing(animal -> animal[0]));

        int catchableAnimals = 0;
        for (int launcher : launchers) {
            for (int i = 0; i < animals.size(); i++) {
                int[] animal = animals.get(i);
                if (animal[0] < launcher - l) {
                    continue;
                }
                if (animal[0] > launcher + l) {
                    break;
                }

                if (Math.abs(launcher - animal[0]) + animal[1] <= l) {
                    catchableAnimals++;
                    animals.remove(i--);
                }
            }
        }

        System.out.println(catchableAnimals);
    }
}
