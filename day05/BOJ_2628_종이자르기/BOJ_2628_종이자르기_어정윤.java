import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기_어정윤 {

    private static final List<Integer> WIDTH_CUTS = new ArrayList<>();
    private static final List<Integer> HEIGHT_CUTS = new ArrayList<>();
    private static final int WIDTH = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int position = Integer.parseInt(stringTokenizer.nextToken());
            if (direction == WIDTH) {
                HEIGHT_CUTS.add(position);
            } else {
                WIDTH_CUTS.add(position);
            }
        }

        WIDTH_CUTS.add(0);
        WIDTH_CUTS.add(width);
        HEIGHT_CUTS.add(0);
        HEIGHT_CUTS.add(height);
        WIDTH_CUTS.sort(Integer::compareTo);
        HEIGHT_CUTS.sort(Integer::compareTo);

        System.out.println(getMaxArea());
    }

    private static int getMaxArea() {
        int maxArea = 0;
        int preWidth = 0;
        int preHeight = 0;
        for (int currentWidth : WIDTH_CUTS) {
            for (int currentHeight : HEIGHT_CUTS) {
                int area = (currentHeight - preHeight) * (currentWidth - preWidth);
                preHeight = currentHeight;
                maxArea = Math.max(maxArea, area);
            }
            preWidth = currentWidth;
        }
        return maxArea;
    }
}
