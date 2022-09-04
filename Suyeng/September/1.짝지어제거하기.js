//처음엔 slice로 접근했다가 stack으로 변경
//stack 으로 넣었다가, 다음것과 비교했다가  같으면 pop, 다르면 push를 반복한다.

function solution(s) {
  let answer;
  let stack = [];

  for (const c of s) {
    if (stack.length > 0) {
      if (stack[stack.length - 1] === c) {
        stack.pop();
      } else {
        stack.push(c);
      }
    } else {
      stack.push(c);
    }
  }
  if (stack.length) {
    answer = 0;
  } else {
    answer = 1;
  }

  return answer;
}

const s = "baabaa";

console.log(solution(s));
