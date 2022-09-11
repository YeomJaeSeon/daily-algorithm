function solution(lines) {
  var answer = 0;
  const answerMap = {};
  let max = -1;
  for (let i = 0; i < lines.length; i++) {
    const splited = lines[i].split(' ');
    const s = splited[1];
    const t = splited[2];
    const hourMinuteSecond = s.substring(0, 8).split(':');
    const milliSeconds = s.substring(9);
    const hour = hourMinuteSecond[0];
    const minute = hourMinuteSecond[1];
    const second = hourMinuteSecond[2];
    const sum =
      1000 * (3600 * hour + 60 * minute + second * 1) + milliSeconds * 1;
    const tMilliSecond = t.substring(0, t.length - 1) * 1000;
    const startTime = sum - tMilliSecond + 1;
    for (let j = startTime; j <= sum; j++) {
      if (Object.keys(answerMap).includes(j)) {
        answerMap[j] += 1;
      } else {
        answerMap[j] = 1;
      }
      max = Math.max(max, answerMap[j]);
    }
  }
  return max;
}
