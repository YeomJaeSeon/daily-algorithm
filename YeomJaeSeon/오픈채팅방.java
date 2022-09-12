package problems1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {
    static final String ENTER_MESSAGE = "님이 들어왔습니다.";
    static final String LEAVE_MESSAGE = "님이 나갔습니다.";
    static List<String[]> list = new ArrayList<>();
    // userId - nickName mapper map
    static Map<String, String> nickNameMapper = new HashMap<>();

    public static void main(String[] args) {
        오픈채팅방 instance = new 오픈채팅방();
        System.out.println(Arrays.toString(instance.solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
    }
    public String[] solution(String[] record) {
        // setting list & nickNameMapper
        for(String data: record){
            String[] splitData = data.split(" ");
            String userId = splitData[1];

            if(splitData[0].equals("Enter")){
                //enter
                list.add(new String[]{userId, ENTER_MESSAGE});

            }else if(splitData[0].equals("Leave")){
                //leave
                list.add(new String[]{userId, LEAVE_MESSAGE});
            }

            if(splitData.length == 3){
                nickNameMapper.put(userId, splitData[2]);
            }
        }

        return applyLatestNickName();
    }
    String[] applyLatestNickName(){
        List<String> resultList = new ArrayList<>();
        for(String[] data: list){
            String userId = data[0];
            String text = data[1];

            String latestNickName = nickNameMapper.get(userId);
            resultList.add(latestNickName + text);
        }
        
        return resultList.toArray(new String[resultList.size()]);
    }
}
