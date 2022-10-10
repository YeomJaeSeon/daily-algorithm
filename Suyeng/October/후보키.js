function getCombination(arr, num) {
  const result = [];
  if (num === 1) return arr.map((v) => [v]);

  arr.forEach((v, idx, origin) => {
    const rest = origin.slice(idx + 1);
    const combinations = getCombination(rest, num - 1);
    const attached = combinations.map((combination) => [v, ...combination]);
    result.push(...attached);
  });
  return result;
}

function solution(relation) {
  let answer = 0;
  // 1. 인덱스의 모든 조합을 구해준다.
  const indexArr = Array.from({ length: relation[0].length }, (_, i) => i);
  const combinationArr = [];
  for (let i = 0; i < indexArr.length; i++) {
    combinationArr.push(...getCombination(indexArr, i + 1));
  }

  // 조합을 돌면서 유일성을 만족하는지 확인

  return answer;
}

const relation = [
  ["100", "ryan", "music", "2"],
  ["200", "apeach", "math", "2"],
  ["300", "tube", "computer", "3"],
  ["400", "con", "computer", "4"],
  ["500", "muzi", "music", "3"],
  ["600", "apeach", "music", "2"],
];
console.log(solution(relation));
