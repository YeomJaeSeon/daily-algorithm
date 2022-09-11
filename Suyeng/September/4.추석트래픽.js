// 초당 최대 처리량
// 임의 시간으로부터 1초간 처리하는 요청의 최대 개수
// 응답 완료 시간 S, 처리시간 T

// 시간 문자열을 파싱해서
// (시작시간 , 종료시간) 의 형태로 저장하고
//  로그의 종료시간 endTime 을 기준으로 1초 내에
// 다른 로그값이 포함되어 있는지 확인한다.

function getTimes(lines) {
  const lineData = lines.map((line) => {
    const [, time, processTime] = line.split(" ");
    const [h, m, s] = time.split(":");
    const end = (h * 3600 + m * 60 + s * 1) * 1000 + 1;
    const elapsed = processTime.replace("s", "") * 1000;

    return { start: end - elapsed + 1, end: end };
  });
  return lineData;
}

function solution(lines) {
  const lineData = getTimes(lines);
  lineData.sort((a, b) => a.start - b.start);

  let count = 0;

  lineData.map((line) => {
    count = 0;
    for (let i = 0; i < line.length; i++) {}
  });

  return count;
}

const lines = [
  "2016-09-15 20:59:57.421 0.351s",
  "2016-09-15 20:59:58.233 1.181s",
  "2016-09-15 20:59:58.299 0.8s",
  "2016-09-15 20:59:58.688 1.041s",
  "2016-09-15 20:59:59.591 1.412s",
  "2016-09-15 21:00:00.464 1.466s",
  "2016-09-15 21:00:00.741 1.581s",
  "2016-09-15 21:00:00.748 2.31s",
  "2016-09-15 21:00:00.966 0.381s",
  "2016-09-15 21:00:02.066 2.62s",
];

console.log(solution(lines));
