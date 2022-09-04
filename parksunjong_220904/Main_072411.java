package programmers_072411;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main_072411 {
	public static void main(String args[]) {
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
//		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = { 2, 3, 4 };

		Solution sol = new Solution();
		String[] answer = sol.solution(orders, course);

		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}

	}
}

class Solution {
	// 전역변수로 map 설정
	public static Map<String, Integer> map = new HashMap<String, Integer>();

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		char[] chars = {};

		// 음식 정렬해주기
		for (int i = 0; i < orders.length; i++) {
			// 음식별로 char array에 넣음
			chars = orders[i].toCharArray();
			// 음식 순서 정렬
			Arrays.sort(chars);
			// set 수만큼 조합 실행
			for (int j = 0; j < course.length; j++) {
				Print_Combination(chars, chars.length, course[j]);
			}
		}

		List<String> list = new ArrayList<String>();
		// 세트 수 만큼 반복
		for (int i = 0; i < course.length; i++) {
			// 가장 많이 나온 조합을 위해 max 설정
			int max = 0;
			// 전역변수로 설정한 map 탐색
			for (Entry<String, Integer> entrySet : map.entrySet()) {
				// key에 등록된 조합의 길이 ex) XY, XW == 2 // XAY, AYX == 3 가 course[i]번째와 같을 때
				if (entrySet.getKey().length() == course[i]) {
					// 그때 value값이 1보다 크고 설정한 맥스값보다 크면
					if (entrySet.getValue() > 1 && max <= entrySet.getValue()) {
						// 맥스 값 지정
						max = entrySet.getValue();
					}
				}
				
			}
			// 각 세트의 길이별 맥스 값을 설정한 다음 다시 전체 map 전체 탐색해서
			for (Entry<String, Integer> entrySet : map.entrySet()) {
				// key에 등록된 조합의 길이 ex) XY, XW == 2 // XAY, AYX == 3 가 course[i]번째와 같을 때
				if (entrySet.getKey().length() == course[i]) {
					// map에 등록된 value가 max 값과 같으면
					if(entrySet.getValue() == max) {
						// list에 넣는다.
						list.add(entrySet.getKey());
					}
				}
			}
		}
		
		// 사전 정렬
		Collections.sort(list);
		answer = new String[list.size()];
		// answer에 값 넣어줌
		for(int i = 0;i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}
	
	// 배열로 조합 만들어주는 코드 (구글링... 잘이해안가긴함)
	static void CombinationPossible(char Input_Array[], char Empty_Array[], int Start_Element, int End_Element,
			int Array_Index, int r) {
		String result = "";
		if (Array_Index == r) {
			for (int x = 0; x < r; x++) {
				result += Empty_Array[x];
			}
			if (!map.containsKey(result)) {
				map.put(result, 1);
			} else {
				map.put(result, map.get(result) + 1);
			}
			return;
		}

		for (int y = Start_Element; y <= End_Element && End_Element - y + 1 >= r - Array_Index; y++) {
			Empty_Array[Array_Index] = Input_Array[y];
			CombinationPossible(Input_Array, Empty_Array, y + 1, End_Element, Array_Index + 1, r);
		}
	}

	static void Print_Combination(char Input_Arrary[], int n, int r) {
		char Empty_Array[] = new char[r];
		CombinationPossible(Input_Arrary, Empty_Array, 0, n - 1, 0, r);

	}
}
