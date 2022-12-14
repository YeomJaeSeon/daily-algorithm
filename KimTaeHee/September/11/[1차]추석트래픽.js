function solution(lines) {
  var answer = 0;
  let max = -1;
  const answerMap = {};
  const time = [];
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
    time.push(['start', startTime]);
    time.push(['end', sum + 999]);
  }
  time.sort((a, b) => (a[1] !== b[1] ? a[1] - b[1] : -1));
  for (let t of time) {
    if (t[0] === 'start') {
      answer += 1;
    } else {
      answer -= 1;
    }
    console.log(answer);
    max = Math.max(answer, max);
  }
  return max;
}
