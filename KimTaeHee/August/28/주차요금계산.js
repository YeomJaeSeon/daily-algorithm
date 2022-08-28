function solution(fees, records) {
  var answer = [];
  const people = {};

  for (let i = 0; i < records.length; i++) {
    const splited = records[i].split(" ");
    const inTime = splited[0];
    const carNumber = splited[1];
    const state = splited[2];

    if (Object.keys(people).includes(carNumber)) {
      if (state === "IN" && people[carNumber].inTime === 0) {
        people[carNumber].inTime = inTime;
      } else if (state === "OUT" && people[carNumber].inTime !== 0) {
        const inHour = people[carNumber].inTime.split(":")[0];
        const inMinute = people[carNumber].inTime.split(":")[1];

        const diffHour = splited[0].split(":")[0] - inHour;
        const diffMinute = splited[0].split(":")[1] - inMinute;

        people[carNumber].accumulateTime += diffHour * 60 + diffMinute;
        people[carNumber].inTime = 0;
      }
    } else {
      people[carNumber] = { inTime, accumulateTime: 0 };
    }
  }

  const peopleArray = Object.keys(people).sort();

  for (let i = 0; i < peopleArray.length; i++) {
    if (people[peopleArray[i]].inTime !== 0) {
      const inHour = people[peopleArray[i]].inTime.split(":")[0];
      const inMinute = people[peopleArray[i]].inTime.split(":")[1];

      const diffHour = 23 - inHour;
      const diffMinute = 59 - inMinute;
      people[peopleArray[i]].accumulateTime += diffHour * 60 + diffMinute;
    }

    if (people[peopleArray[i]].accumulateTime > fees[0]) {
      answer.push(
        fees[1] +
          Math.ceil(
            (people[peopleArray[i]].accumulateTime - fees[0]) / fees[2]
          ) *
            fees[3]
      );
    } else {
      answer.push(fees[1]);
    }
  }
  return answer;
}

