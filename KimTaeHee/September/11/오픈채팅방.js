function solution(record) {
  var answer = [];
  const userMap = {};
  for (let i = 0; i < record.length; i++) {
    const splited = record[i].split(' ');
    const command = splited[0];
    const uId = splited[1];
    const nickName = splited[2];
    if (command === 'Change') {
      userMap[uId] = nickName;
    } else if (command === 'Enter') {
      userMap[uId] = nickName;
      answer.push(`${uId} Enter`);
    } else if (command === 'Leave') {
      answer.push(`${uId} Leave`);
    }
  }
  for (let i = 0; i < answer.length; i++) {
    const splited = answer[i].split(' ');
    const uId = splited[0];
    const command = splited[1];
    answer[i] = `${userMap[uId]}${
      splited[1] === 'Enter' ? '님이 들어왔습니다.' : '님이 나갔습니다.'
    }`;
  }
  return answer;
}

function isExistedUser(userMap, uId) {
  return Object.keys(userMap).includes(uId);
}
