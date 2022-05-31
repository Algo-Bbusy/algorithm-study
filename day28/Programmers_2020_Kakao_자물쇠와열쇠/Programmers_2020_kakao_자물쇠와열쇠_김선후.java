package kakao2020;

public class Programmers_2020_kakao_자물쇠와열쇠 {
	//자물쇠와 열쇠
	//문제 풀이 접근법 자체는 금방 떠올랐으나 원하는 기능을 구현하는데 어려움을 겪어서 해당 기능을 구현하기 위해서 구글링을 하였습니다.
	//구글링 결과 이미지 패딩 알고리즘이라는 것이 있어서 해당 알고리즘을 이용하여 구현하였습니다.
	//처음에 접근한 방법은 key 혹은 lock을 시작점(0,0)에 두고 브루트포스 알고리즘을 통해 홈에 맞출때까지 진행하는 방식입니다.
	//끝까지 맞는 부분이 없으면 key 혹은 lock 을 90도 회전한 후 다시 브루트포스 탐색을 진행하는 방식으로 접근법 자체는 쉬웠던 문제 같습니다.
	//다만 배열의 회전 알고리즘을 구현할 수 있는지와 패딩알고리즘을 공부하지 않았을 경우엔 두 배열간의 매칭과정이 어려운 것 같습니다.
	//Solution 1.
	//1. key의 길이 + lock의 길이*2 -2(양측 한칸씩)이 전체 패드 길이가 된다.
	//2. 0 90 180 270도 네번의 회전을 진행한 후 패딩과정과 홈이 맞는지를 체크한다.
	//풀이시간 2시간 23분 13초
	public boolean solution(int[][] key, int[][] lock) {
	    int padLength = lock.length - 1;

	    for (int d = 0; d < 4; d++) {
	      key = rotate(key);
	      int[][] paddedKey = padding(key, padLength);
	      for (int y = 0; y < paddedKey.length - padLength; y++) {
	        for (int x = 0; x < paddedKey.length - padLength; x++) {
	          if (isValid(lock, paddedKey, y, x)) {
	            return true;
	          }
	        }
	      }
	    }

	    return false;
	  }

	  private boolean isValid(int[][] lock, int[][] paddedKey, int y, int x) {
	    for (int ly = 0; ly < lock.length; ly++) {
	      for (int lx = 0; lx < lock.length; lx++) {
	        if (lock[ly][lx] + paddedKey[y + ly][x + lx] != 1) {
	          return false;
	        }
	      }
	    }
	    return true;
	  }

	  private int[][] padding(int[][] key, int padLength) {
	    int[][] result = new int[key.length + padLength * 2][key.length + padLength * 2];

	    for (int y = 0; y < key.length; y++) {
	      for (int x = 0; x < key.length; x++) {
	        result[padLength + y][padLength + x] = key[y][x];
	      }
	    }
	    return result;
	  }

	  private int[][] rotate(int[][] key) {
	    int[][] result = new int[key.length][key.length];
	    for (int y = 0; y < key.length; y++) {
	      for (int x = 0; x < key.length; x++) {
	        result[y][x] = key[key.length - 1 - x][y];
	      }
	    }
	    return result;
	  }

}
