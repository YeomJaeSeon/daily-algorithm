function solution(gems) {
  const gemSize = new Set(gems).size;
  const gemMap = new Map();
  const gemLength = [];

  gems.forEach((gem, i) => {
    gemMap.delete(gem);
    gemMap.set(gem, i + 1);
    if (gemMap.size === gemSize) {
      gemLength.push([gemMap.values().next().value, i + 1]);
    }
  });

  gemLength.sort(([a1, a2], [b1, b2]) => {
    if (a2 - a1 !== b1 - b2) {
      return a2 - a1 - (b2 - b1);
    } else {
      return a2 - b2;
    }
  });
  return gemLength[0];
}

const gems = [
  "DIA",
  "RUBY",
  "RUBY",
  "DIA",
  "DIA",
  "EMERALD",
  "SAPPHIRE",
  "DIA",
];

console.log(solution(gems));
