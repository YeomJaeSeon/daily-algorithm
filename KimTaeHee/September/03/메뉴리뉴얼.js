function solution(orders, course) {
  var answer = [];

  for (let i = 0; i < course.length; i++) {
    let map = {};
    let max = -1;

    for (let j = 0; j < orders.length; j++) {
      const result = getCombination(orders[j].split(''), course[i]);

      for (let k = 0; k < result.length; k++) {
        const str = result[k].sort().join('');
        if (Object.keys(map).includes(str)) {
          map[str] += 1;
          max = Math.max(max, map[str]);
        } else {
          map[str] = 1;
        }
      }
    }
    const keys = Object.keys(map);
    for (let j = 0; j < keys.length; j++) {
      if (map[keys[j]] === max) {
        answer.push(keys[j]);
      }
    }
  }

  answer.sort();

  return answer;
}
function getCombination(array, selected) {
  const result = [];

  if (selected === 1) {
    return array.map((element) => [element]);
  }

  array.forEach((fixed, index, origin) => {
    const sliced = origin.slice(index + 1);
    const combination = getCombination(sliced, selected - 1);
    const attached = combination.map((element) => [fixed, ...element]);
    result.push(...attached);
  });

  return result;
}
