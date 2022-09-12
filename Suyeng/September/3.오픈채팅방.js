function solution(record) {
  const userInfo = {};
  const action = [];
  const answerMapping = {
    Enter: "님이 들어왔습니다.",
    Leave: "님이 나갔습니다.",
  };

  const data = record
    .map((data) => [...data.split(" ")])
    .flatMap((state, i) => ({
      state: state[0],
      id: state[1],
      name: state[2],
    }));

  data.forEach((v) => {
    if (v.state !== "Change") {
      action.push([v.state, v.id]);
    }
    if (v.name) {
      userInfo[v.id] = v.name;
    }
  });

  return action.map(([state, name]) => {
    return `${userInfo[name]}${answerMapping[state]}`;
  });
}

const record = [
  "Enter uid1234 Muzi",
  "Enter uid4567 Prodo",
  "Leave uid1234",
  "Enter uid1234 Prodo",
  "Change uid4567 Ryan",
];

console.log(solution(record));

// function solution(record) {
//   var nick = {},
//     a = record.map((v) => v.split(" "));
//   a.slice()
//     .reverse()
//     .forEach((v) => {
//       if (v[2] && !nick[v[1]]) {
//         nick[v[1]] = v[2];
//       }
//     });
//   return a
//     .filter((v) => {
//       return v[0] !== "Change";
//     })
//     .map((v) => {
//       return v[0] === "Enter"
//         ? nick[v[1]] + "님이 들어왔습니다."
//         : nick[v[1]] + "님이 나갔습니다.";
//     });
// }
