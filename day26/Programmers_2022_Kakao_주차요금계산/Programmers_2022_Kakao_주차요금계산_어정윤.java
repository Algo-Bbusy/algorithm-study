import java.util.*;

public class Programmers_2022_Kakao_주차요금계산_어정윤 {

    private static final int BASIC_TIME = 0;
    private static final int BASIC_PAY = 1;
    private static final int UNIT_TIME = 2;
    private static final int UNIT_PAY = 3;
    private static final int TIME = 0;
    private static final int CAR_NUMBER = 1;
    private static final int MINUTE = 1;
    private static final String END_OF_THE_DAY = "23:59";
    private static final String SPACE = " ";
    private static final String DELIMITER = ":";

    public int[] solution(int[] fees, String[] records) {
        return calculatePayment(fees, getParkingTimeInfos(records));
    }

    private int[] calculatePayment(int[] fees, Map<String, Integer> timeInfos) {
        List<String> carNumbers = new ArrayList<>(timeInfos.keySet());
        Collections.sort(carNumbers);
        int[] answer = new int[carNumbers.size()];
        int idx = 0;
        for (String carNumber : carNumbers) {
            int accumulatedTime = timeInfos.get(carNumber);
            if (accumulatedTime <= fees[BASIC_TIME]) {
                answer[idx++] = fees[BASIC_PAY];
            } else {
                int additionalTime = accumulatedTime - fees[BASIC_TIME];
                int additionalPay = (int) Math.ceil((double) additionalTime / fees[UNIT_TIME]) * fees[UNIT_PAY];
                answer[idx++] = fees[BASIC_PAY] + additionalPay;
            }
        }
        return answer;
    }

    private Map<String, Integer> getParkingTimeInfos(String[] records) {
        Map<String, String> parkingInfos = new HashMap<>();
        Map<String, Integer> timeInfos = new HashMap<>();
        for (String record : records) {
            String[] parkingInfo = record.split(SPACE);
            String carNumber = parkingInfo[CAR_NUMBER];
            String time = parkingInfo[TIME];
            if (!parkingInfos.containsKey(carNumber)) {
                parkingInfos.put(carNumber, time);
            } else {
                String inTime = parkingInfos.remove(carNumber);
                updateTimeInfos(timeInfos, carNumber, getAccumulatedTime(time, inTime));
            }
        }
        for (Map.Entry<String, String> leftCar : parkingInfos.entrySet()) {
            updateTimeInfos(timeInfos, leftCar.getKey(), getAccumulatedTime(END_OF_THE_DAY, leftCar.getValue()));
        }
        return timeInfos;
    }

    private void updateTimeInfos(Map<String, Integer> timeInfos, String carNumber, int accumulatedTime) {
        if (timeInfos.containsKey(carNumber)) {
            timeInfos.put(carNumber, timeInfos.get(carNumber) + accumulatedTime);
        } else {
            timeInfos.put(carNumber, accumulatedTime);
        }
    }

    private int getAccumulatedTime(String outTime, String inTime) {
        String[] out = outTime.split(DELIMITER);
        String[] in = inTime.split(DELIMITER);
        int parkingTime = Integer.parseInt(out[TIME]) - Integer.parseInt(in[TIME]);
        int parkingMinute = Integer.parseInt(out[MINUTE]) - Integer.parseInt(in[MINUTE]);
        if (parkingMinute < 0) {
            parkingMinute += 60;
            parkingTime--;
        }
        return parkingTime * 60 + parkingMinute;
    }
}
