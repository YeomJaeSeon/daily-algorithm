function solution(s) {
  var answer = -1;

  const stack = [s[0]];

  for (let i = 1; i < s.length; i++) {
    if (stack.length > 0 && stack[stack.length - 1] === s[i]) {
      stack.pop();
    } else {
      stack.push(s[i]);
    }
  }

  answer = stack.length === 0 ? 1 : 0;

  return answer;
}
